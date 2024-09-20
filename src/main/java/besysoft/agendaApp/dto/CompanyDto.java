package besysoft.agendaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
    private String address;

    private Set<PersonDto> contacts;
}
