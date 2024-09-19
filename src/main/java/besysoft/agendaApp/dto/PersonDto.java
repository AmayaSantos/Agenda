package besysoft.agendaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Integer id;
    private Long dni;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String city;
    private String address;
}
