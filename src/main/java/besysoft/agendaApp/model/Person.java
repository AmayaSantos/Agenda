package besysoft.agendaApp.model;

import besysoft.agendaApp.dto.PersonDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perosonas")
public class Person {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long dni;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String city;
    private String address;

    public void updateBy(PersonDto person) {
        this.dni= person.getDni();
        this.name= person.getName();
        this.surname= person.getSurname();
        this.email= person.getEmail();
        this.phoneNumber= person.getPhoneNumber();
        this.city= person.getCity();
        this.address= person.getAddress();
    }
}
