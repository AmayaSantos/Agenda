package besysoft.agendaApp.controller;

import besysoft.agendaApp.dto.PersonFilterDto;
import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.service.PersonService;
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
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(summary = "agrega Persona")
    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto person) {
        return new ResponseEntity<>(personService.create(person), HttpStatus.CREATED);
    }

    @Operation(summary = "busca Personas")
    @GetMapping
    public ResponseEntity<Page<PersonDto>> getAll(PersonFilterDto pageFilter ) {
        return new ResponseEntity<>(personService.getAll(pageFilter), HttpStatus.OK);
    }

    @Operation(summary = "busca Persona por id")
    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> get(@PathVariable Integer personId) {
        return new ResponseEntity<>(personService.get(personId), HttpStatus.OK);
    }

    @Operation(summary = "borra Persona por id")
    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(@PathVariable Integer personId) {
        personService.delete(personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "actualiza Persona")
    @PutMapping
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.update( personDto), HttpStatus.ACCEPTED);
    }
}
