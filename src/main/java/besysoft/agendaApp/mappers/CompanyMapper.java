package besysoft.agendaApp.mappers;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.model.Company;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
        return isNull(company.getContacts())? new HashSet() :
                company.getContacts().stream().map(PersonMapper::toDTO).collect(Collectors.toSet());
    }

    public static Company toEntity(CompanyDto company) {
        return Company.builder()
                .city(company.getCity())
                .email(company.getEmail())
                .name(company.getName())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .build();
    }
}
