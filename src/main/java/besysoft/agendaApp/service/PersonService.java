package besysoft.agendaApp.service;

import besysoft.agendaApp.dto.PersonFilterDto;
import besysoft.agendaApp.dto.PersonDto;
import org.springframework.data.domain.Page;

public interface PersonService {

    String create(PersonDto person);

    Page<PersonDto> getAll(PersonFilterDto pageFilter);

    String delete(Integer personId);

    String update(PersonDto personDto);

    String get(Integer personId);
}
