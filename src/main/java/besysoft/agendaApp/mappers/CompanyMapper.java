package besysoft.agendaApp.mappers;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.model.Company;
import besysoft.agendaApp.model.Person;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {


    public static CompanyDto toDTO(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .city(company.getCity())
                .email(company.getEmail())
                .name(company.getName())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .contacts(getContacts(company))
                .build();
    }

    private static Set<PersonDto> getContacts(Company company) {
        return company.getContacts().stream().map(PersonMapper::toDTO).collect(Collectors.toSet());
    }

    public static Company toEntity(CompanyDto company) {
        return Company.builder()
                .id(company.getId())
                .city(company.getCity())
                .email(company.getEmail())
                .name(company.getName())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .build();
    }
}
