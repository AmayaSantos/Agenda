package besysoft.agendaApp.service;

import besysoft.agendaApp.dto.PersonFilterDto;
import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.model.Person;
import org.springframework.data.domain.Page;

public interface PersonService {

    void create(PersonDto person);

    Page<PersonDto> getAll(PersonFilterDto pageFilter);

    void delete(Integer personId);

    void update(PersonDto personDto);

    PersonDto get(Integer personId);

    Person findPerson(Integer personId);
}
