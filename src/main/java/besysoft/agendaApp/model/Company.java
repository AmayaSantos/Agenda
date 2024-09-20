package besysoft.agendaApp.model;

import besysoft.agendaApp.dto.CompanyDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresas")
public class Company {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    private String address;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "compania_Persona",
            joinColumns = @JoinColumn(name = "companyId"),
            inverseJoinColumns = @JoinColumn(name = "PersonId")
    )
    private Set<Person> contacts = new HashSet<>();

    public void addPerson(Person person) {
        contacts.add(person);
    }
    public boolean removePerson(Person person) {
        return contacts.remove(person);
    }

    public void updateBy(CompanyDto companyDto) {
        this.name= companyDto.getName();
        this.email= companyDto.getEmail();
        this.phoneNumber= companyDto.getPhoneNumber();
        this.city= companyDto.getCity();
        this.address= companyDto.getAddress();
    }
}
