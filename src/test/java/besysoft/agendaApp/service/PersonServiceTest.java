package besysoft.agendaApp.service;

import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.dto.PersonFilterDto;
import besysoft.agendaApp.exeptions.AppException;
import besysoft.agendaApp.exeptions.errores.PersonError;
import besysoft.agendaApp.model.Person;
import besysoft.agendaApp.service.implement.PersonServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @Autowired
    private PersonServiceImpl personService;

    @Test
    public void findReadyExistPerson() {
        Person person = personService.findPerson(1);
        assertEquals(person.getName(), "userToFind");
    }

    @Test
    public void findReadyExistPersonInDto() {
        PersonDto person = personService.get(1);
        assertEquals(person.getName(), "userToFind");
    }

    @Test
    public void createPersonWithPersonDto() {
        PersonDto personToCreate= new PersonDto(100000,2323L,"new","new","new","new","new","new");

        PersonDto person = personService.create(personToCreate);
        PersonDto personSaved= personService.get(person.getId());

        assertEquals(personSaved.getId(),person.getId());
    }


    @Test
    public void dontSelectIdInCreationOfPerspn() {
        PersonDto personToCreate= new PersonDto(100000,2323L,"othernew","othernew","othernew","othernew","othernew","othernew");

        PersonDto person = personService.create(personToCreate);
        PersonDto personSaved= personService.get(person.getId());

        assertNotEquals(personToCreate.getId(),person.getId());
    }

    @Test
    public void corredDataPersonInCreation() {
        PersonDto personToCreate= new PersonDto(null,2323L,"othernew2","othernew2","othernew2","othernew2","othernew2","othernew2");

        PersonDto personCreated = personService.create(personToCreate);

        //only diff in ids
        personToCreate.setId(personCreated.getId());
        assertEquals(personToCreate,personCreated);
    }

    @Test
    public void seachPerson() {
        PersonFilterDto filtro= new PersonFilterDto();
        AppException exception = assertThrows(AppException.class, () ->
                personService.getAll(filtro));
        assertEquals(PersonError.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    public void dontFindDeletedPerson() {
        personService.get(2);

        personService.delete(2);

        AppException exception = assertThrows(AppException.class, () ->
                personService.findPerson(2));
        assertEquals(PersonError.PERSON_NOT_FOUND.getMessage(), exception.getMessage());
    }
}
