package besysoft.agendaApp.service;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.CompanyFilterDto;
import besysoft.agendaApp.exeptions.AppException;
import besysoft.agendaApp.exeptions.errores.CompanyError;
import besysoft.agendaApp.service.implement.CompanyServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CompanyServiceTest {

    @Autowired
    private CompanyServiceImpl companyService;

    @Test
    public void findReadyExistCompany() {
        CompanyDto company = companyService.get(1);
        assertEquals(company.getName(), "companyToFind");
    }

    @Test
    public void createCompanyWithCompanyDto() {
        CompanyDto personToCreate= new CompanyDto(100000,"new","new","new","new","new",null);
        CompanyDto Company = companyService.create(personToCreate);

        CompanyDto personSaved= companyService.get(Company.getId());
        assertEquals(personSaved.getId(),Company.getId());
    }


    @Test
    public void dontSelectIdInCreationOnCompany() {
        CompanyDto personToCreate= new CompanyDto(100000,"othernew","othernew","othernew","othernew","othernew",null);

        CompanyDto Company = companyService.create(personToCreate);
        CompanyDto personSaved= companyService.get(Company.getId());

        assertNotEquals(personToCreate.getId(),Company.getId());
    }

    @Test
    public void correctDataCompanyInCreation() {
        CompanyDto companyDto= new CompanyDto(null,"othernew2","othernew2","othernew2","othernew2","othernew2",new HashSet<>());

        CompanyDto personCreated = companyService.create(companyDto);

        //only diff in ids
        companyDto.setId(personCreated.getId());
        assertEquals(companyDto,personCreated);
    }

    @Test
    public void searchCompanyByCity() {
        CompanyDto personToCreate= new CompanyDto(null,"recentCreated","recentCreated","recentCreated","recentCreated","recentCreated",null);
        CompanyDto personCreated = companyService.create(personToCreate);


        CompanyFilterDto filter= new CompanyFilterDto();
        filter.setPage(0);
        filter.setSize(1000);

        Page<CompanyDto> companyDtos= companyService.getAll(filter);

        assertFalse(companyDtos.isEmpty());
    }

    @Test
    public void seachCompanyFail() {
        CompanyFilterDto filtro= new CompanyFilterDto();

        AppException exception = assertThrows(AppException.class, () ->
                companyService.getAll(filtro));

        assertEquals("must not be null, must not be null, ", exception.getMessage());
    }

    @Test
    public void dontFindDeletedCompany() {
        companyService.get(2);

        companyService.delete(2);

        AppException exception = assertThrows(AppException.class, () ->
                companyService.get(2));
        assertEquals(CompanyError.COMPANY_NOT_FOUND.getMessage(), exception.getMessage());
    }
}
