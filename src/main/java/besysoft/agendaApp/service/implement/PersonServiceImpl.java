package besysoft.agendaApp.service.implement;

import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.dto.PersonFilterDto;
import besysoft.agendaApp.exeptions.AppException;
import besysoft.agendaApp.exeptions.errores.PersonError;
import besysoft.agendaApp.mappers.PersonMapper;
import besysoft.agendaApp.model.Person;
import besysoft.agendaApp.repository.PersonRepository;
import besysoft.agendaApp.repository.specifications.PersonSpecification;
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
public class PersonServiceImpl implements PersonService {

    private final static Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDto create(PersonDto person) {
        Person save = personRepository.save(PersonMapper.toEntity(person));
        LOG.info("Persona creada con id :{}", save.getId());
        return PersonMapper.toDTO(save);
    }

    @Override
    public Page<PersonDto> getAll(PersonFilterDto personFilter) {
        Util.valid(personFilter);

        Pageable pageable = PageRequest.of(
                personFilter.getPage(), personFilter.getSize());
        LOG.info("Se realizo una busqueda de Personas");
        return personRepository
                .findAll(PersonSpecification.fullName(personFilter.getFullName())
                        .and(PersonSpecification.inCities(personFilter.getCitiesNames())),
                        pageable)
                .map(PersonMapper::toDTO);
    }

    @Override
    public void delete(Integer personId) {
        Person person= findPerson(personId);
        personRepository.delete(person);
        LOG.info("Sea Borrado la Persona con id :{}", person.getId());
    }

    @Override
    public PersonDto update(PersonDto personDto) {

        Person person= findPerson(personDto.getId());
        person.updateBy(personDto);
        personRepository.save(person);
        LOG.info("Sea actualizado la Persona con id :{}", person.getId());
        return PersonMapper.toDTO(person);
    }

    @Override
    public PersonDto get(Integer personId) {
        Person person= findPerson(personId);

        return PersonMapper.toDTO(person);
    }

    @Override
    public Person findPerson(Integer personId) {
        LOG.info("Sea buscado la Persona con id :{}", personId);
        return personRepository.findById(personId)
                .orElseThrow(() -> new AppException(PersonError.PERSON_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

}
