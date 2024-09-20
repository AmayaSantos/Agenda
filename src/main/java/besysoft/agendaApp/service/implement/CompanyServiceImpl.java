package besysoft.agendaApp.service.implement;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.CompanyFilterDto;
import besysoft.agendaApp.exeptions.AppException;
import besysoft.agendaApp.exeptions.errores.CompanyError;
import besysoft.agendaApp.mappers.CompanyMapper;
import besysoft.agendaApp.model.Company;
import besysoft.agendaApp.model.Person;
import besysoft.agendaApp.repository.CompanyRepository;
import besysoft.agendaApp.service.CompanyService;
import besysoft.agendaApp.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private PersonService personService;
    @Override
    public CompanyDto create(CompanyDto company) {
       return CompanyMapper.toDTO(companyRepository.save(CompanyMapper.toEntity(company)));
    }

    @Override
    public Page<CompanyDto> getAll(CompanyFilterDto companyFilter) {
        Pageable pageable = PageRequest.of(
                companyFilter.getPage(), companyFilter.getSize());
        return companyRepository
                .findAll(pageable)
                .map(CompanyMapper::toDTO);
    }

    @Override
    public void delete(Integer companyId) {
        Company person= findCompany(companyId);
        companyRepository.delete(person);
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        Company company= findCompany(companyDto.getId());
        company.updateBy(companyDto);
        return CompanyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDto get(Integer companyId) {
        Company company= findCompany(companyId);
        return CompanyMapper.toDTO(company);
    }

    private Company findCompany(Integer companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new AppException(CompanyError.COMPANY_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public CompanyDto addContact(Integer companyId, Integer personId) {
        Company company = findCompany(companyId);
        if (foundContact(personId, company))
            throw new AppException(CompanyError.CONTACT_ALREADY_ADDED,HttpStatus.BAD_REQUEST);

        company.addPerson(personService.findPerson(personId));
        return CompanyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDto removeContact(Integer companyId, Integer personId) {
        Company company = findCompany(companyId);

        if (!foundContact(personId, company))
            throw new AppException(CompanyError.CONTACT_NOT_ADDED,HttpStatus.BAD_REQUEST);

        company.removePerson(personService.findPerson(personId));
        return CompanyMapper.toDTO(companyRepository.save(company));
    }

    private static boolean foundContact(Integer personId, Company company) {
        return company.getContacts().stream()
                .map(Person::getId)
                .anyMatch(id -> id.equals(personId));
    }
}
