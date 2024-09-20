package besysoft.agendaApp.mappers;

import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {


    public static PersonDto toDTO(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .dni(person.getDni())
                .city(person.getCity())
                .email(person.getEmail())
                .name(person.getName())
                .surname(person.getSurname())
                .address(person.getAddress())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }

    public static Person toEntity(PersonDto person) {
        return Person.builder()
                .dni(person.getDni())
                .city(person.getCity())
                .email(person.getEmail())
                .name(person.getName())
                .surname(person.getSurname())
                .address(person.getAddress())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }
}
