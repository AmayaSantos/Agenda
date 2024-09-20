package besysoft.agendaApp.service.implement;

import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.dto.PersonFilterDto;
import besysoft.agendaApp.exeptions.AppException;
import besysoft.agendaApp.exeptions.errores.PersonError;
import besysoft.agendaApp.mappers.PersonMapper;
import besysoft.agendaApp.model.Person;
import besysoft.agendaApp.repository.PersonRepository;
import besysoft.agendaApp.repository.specifications.PersonSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements besysoft.agendaApp.service.PersonService {
    private PersonRepository personRepository;
    @Override
    public void create(PersonDto person) {
        personRepository.save(PersonMapper.toEntity(person));
    }

    @Override
    public Page<PersonDto> getAll(PersonFilterDto personFilter) {
        Pageable pageable = PageRequest.of(
                personFilter.getPage(), personFilter.getSize());
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
    }

    @Override
    public void update(PersonDto personDto) {

        Person person= findPerson(personDto.getId());
        person.updateBy(personDto);
        personRepository.save(person);
    }

    @Override
    public PersonDto get(Integer personId) {
        Person person= findPerson(personId);

        return PersonMapper.toDTO(person);
    }

    @Override
    public Person findPerson(Integer personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new AppException(PersonError.PERSON_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
