package besysoft.agendaApp.service;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.CompanyFilterDto;
import org.springframework.data.domain.Page;

public interface CompanyService {
    CompanyDto create(CompanyDto companyDto);

    void delete(Integer companyId);

    CompanyDto update(CompanyDto CompanyDto);

    CompanyDto get(Integer companyId);

    Page<CompanyDto> getAll(CompanyFilterDto pageFilter);

    CompanyDto addContact(Integer companyId, Integer personId);

    CompanyDto removeContact(Integer companyId, Integer personId);
}
