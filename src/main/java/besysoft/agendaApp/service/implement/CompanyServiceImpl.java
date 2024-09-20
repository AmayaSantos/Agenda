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
import besysoft.agendaApp.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final static Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);
    private final CompanyRepository companyRepository;
    private final PersonService personService;

    public CompanyServiceImpl(CompanyRepository companyRepository, PersonService personService) {
        this.companyRepository = companyRepository;
        this.personService = personService;
    }

    @Override
    public CompanyDto create(CompanyDto company) {
        Company save = companyRepository.save(CompanyMapper.toEntity(company));
        LOG.info("Empresa creada con id :{}", save.getId());
        return CompanyMapper.toDTO(save);
    }

    @Override
    public Page<CompanyDto> getAll(CompanyFilterDto companyFilter) {
        Util.valid(companyFilter);
        Pageable pageable = PageRequest.of(
                companyFilter.getPage(), companyFilter.getSize());
        LOG.info("Se realizo una busqueda de Empresas");
        return companyRepository
                .findAll(pageable)
                .map(CompanyMapper::toDTO);
    }

    @Override
    public void delete(Integer companyId) {
        Company company= findCompany(companyId);
        companyRepository.delete(company);
        LOG.info("Se elimino una Empresa con Id :{}", company.getId());
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        Company company= findCompany(companyDto.getId());
        company.updateBy(companyDto);
        Company save = companyRepository.save(company);
        LOG.info("Se actualizo los datos una Empresa con Id :{}", company.getId());
        return CompanyMapper.toDTO(save);
    }

    @Override
    public CompanyDto get(Integer companyId) {
        Company company= findCompany(companyId);
        return CompanyMapper.toDTO(company);
    }

    private Company findCompany(Integer companyId) {
        LOG.info("Se busco una Empresas con Id :{}", companyId);
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new AppException(CompanyError.COMPANY_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public CompanyDto addContact(Integer companyId, Integer personId) {
        Company company = findCompany(companyId);
        if (foundContact(personId, company))
            throw new AppException(CompanyError.CONTACT_ALREADY_ADDED,HttpStatus.BAD_REQUEST);

        company.addPerson(personService.findPerson(personId));
        Company save = companyRepository.save(company);

        LOG.info("La Empresa con id {}", company.getId()," agendo a la Pesona con id {}", personId);

        return CompanyMapper.toDTO(save);
    }

    @Override
    public CompanyDto removeContact(Integer companyId, Integer personId) {
        Company company = findCompany(companyId);

        if (!foundContact(personId, company))
            throw new AppException(CompanyError.CONTACT_NOT_ADDED,HttpStatus.BAD_REQUEST);

        company.removePerson(personService.findPerson(personId));
        Company save = companyRepository.save(company);

        LOG.info("La Empresa con id {}", company.getId()," desagendo a la Pesona con id {}", personId);
        return CompanyMapper.toDTO(save);
    }

    private static boolean foundContact(Integer personId, Company company) {
        return company.getContacts().stream()
                .map(Person::getId)
                .anyMatch(id -> id.equals(personId));
    }
}
