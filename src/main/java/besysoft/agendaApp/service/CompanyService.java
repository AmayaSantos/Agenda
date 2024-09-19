package besysoft.agendaApp.service;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.CompanyFilterDto;
import org.springframework.data.domain.Page;

public interface CompanyService {
    String create(CompanyDto person);

    String delete(Integer companyId);

    String update(CompanyDto CompanyDto);

    String get(Integer companyId);

    Page<CompanyDto> getAll(CompanyFilterDto pageFilter);

    String addContact(Integer companyId, Integer personId);

    String removeContact(Integer companyId, Integer personId);
}
