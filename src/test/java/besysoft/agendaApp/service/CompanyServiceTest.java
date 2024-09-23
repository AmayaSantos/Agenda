package besysoft.agendaApp.service;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.CompanyFilterDto;
import besysoft.agendaApp.dto.PersonDto;
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
        CompanyDto companyToCreate= new CompanyDto(100000,"new","new","new","new","new",null);
        CompanyDto Company = companyService.create(companyToCreate);

        CompanyDto companySaved= companyService.get(Company.getId());
        assertEquals(companySaved.getId(),Company.getId());
    }


    @Test
    public void dontSelectIdInCreationOnCompany() {
        CompanyDto companyToCreate= new CompanyDto(100000,"othernew","othernew","othernew","othernew","othernew",null);

        CompanyDto Company = companyService.create(companyToCreate);
        CompanyDto companyCreated= companyService.get(Company.getId());

        assertNotEquals(companyToCreate.getId(),Company.getId());
    }

    @Test
    public void correctDataCompanyInCreation() {
        CompanyDto companyDto= new CompanyDto(null,"othernew2","othernew2","othernew2","othernew2","othernew2",new HashSet<>());

        CompanyDto companyToCreate = companyService.create(companyDto);

        //only diff in ids
        companyDto.setId(companyToCreate.getId());
        assertEquals(companyDto,companyToCreate);
    }

    @Test
    public void searchCompanyByCity() {
        CompanyDto companyToCreate= new CompanyDto(null,"recentCreated","recentCreated","recentCreated","recentCreated","recentCreated",null);
        CompanyDto companyCreated = companyService.create(companyToCreate);


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

    @Test
    public void AddContact() {

        CompanyDto companyToCreate = companyService.addContact(3,3);

        Boolean containsPersonWithId=companyToCreate.getContacts().stream().map(PersonDto::getId).anyMatch(integer -> integer.equals(3));

        assertTrue(containsPersonWithId);
    }

    @Test
    public void removeContact() {
        companyService.addContact(4,4);
        CompanyDto companyToCreate = companyService.removeContact(4,4);

        Boolean containsPersonWithId=companyToCreate.getContacts().stream().map(PersonDto::getId).anyMatch(integer -> integer.equals(3));

        assertFalse(containsPersonWithId);
    }
}
