package besysoft.agendaApp.controller;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.CompanyFilterDto;
import besysoft.agendaApp.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "agrega Empresa")
    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto company) {
        return new ResponseEntity<>(companyService.create(company), HttpStatus.OK);
    }

    @Operation(summary = "busca Empresas")
    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getAll(CompanyFilterDto pageFilter ) {
        return new ResponseEntity<>(companyService.getAll(pageFilter), HttpStatus.OK);
    }

    @Operation(summary = "busca Empresa por id")
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDto> get(@PathVariable(required = true) Integer companyId) {
        return new ResponseEntity<>(companyService.get(companyId), HttpStatus.OK);
    }

    @Operation(summary = "elimina Empresa por id")
    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> delete(@PathVariable Integer companyId) {
        companyService.delete(companyId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Operation(summary = "actualiza Empresa")
    @PutMapping
    public ResponseEntity<CompanyDto> update(@RequestBody CompanyDto CompanyDto) {
        return new ResponseEntity<>(companyService.update( CompanyDto), HttpStatus.OK);
    }

    @PostMapping("/{companyId}/contact/{personId}")
    public ResponseEntity<CompanyDto> addPerson(@PathVariable Integer personId ,@PathVariable Integer companyId ) {
        return new ResponseEntity<>(companyService.addContact(companyId,personId), HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}/contact/{personId}")
    public ResponseEntity<CompanyDto> removePerson(@PathVariable Integer personId, @PathVariable Integer companyId  ) {
        return new ResponseEntity<>(companyService.removeContact(companyId,personId), HttpStatus.OK);
    }
}
