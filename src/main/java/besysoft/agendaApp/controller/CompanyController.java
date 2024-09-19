package besysoft.agendaApp.controller;

import besysoft.agendaApp.dto.CompanyDto;
import besysoft.agendaApp.dto.CompanyFilterDto;
import besysoft.agendaApp.service.CompanyService;
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
    
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> create(CompanyDto company) {
        return new ResponseEntity<>(companyService.create(company), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getAll(CompanyFilterDto pageFilter ) {
        return new ResponseEntity<>(companyService.getAll(pageFilter), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<String> get(@PathVariable Integer personId) {
        return new ResponseEntity<>(companyService.get(personId), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<String> delete(@PathVariable Integer personId) {
        return new ResponseEntity<>(companyService.delete(personId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody CompanyDto CompanyDto) {
        return new ResponseEntity<>(companyService.update( CompanyDto), HttpStatus.OK);
    }

    @PostMapping("/{companyId}/contact/{personId}")
    public ResponseEntity<String> addPerson(@PathVariable Integer personId ,@PathVariable Integer companyId ) {
        return new ResponseEntity<>(companyService.addContact(companyId,personId), HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}/contact/{personId}")
    public ResponseEntity<String> removePerson(@PathVariable Integer personId, @PathVariable Integer companyId  ) {
        return new ResponseEntity<>(companyService.removeContact(companyId,personId), HttpStatus.OK);
    }
}
