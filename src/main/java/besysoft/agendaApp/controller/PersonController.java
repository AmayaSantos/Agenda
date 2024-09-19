package besysoft.agendaApp.controller;

import besysoft.agendaApp.dto.PersonFilterDto;
import besysoft.agendaApp.dto.PersonDto;
import besysoft.agendaApp.service.PersonService;
import lombok.RequiredArgsConstructor;
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

    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> create(PersonDto person) {
        return new ResponseEntity<>(personService.create(person), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PersonDto>> getAll(PersonFilterDto pageFilter ) {
        return new ResponseEntity<>(personService.getAll(pageFilter), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<String> get(@PathVariable Integer personId) {
        return new ResponseEntity<>(personService.get(personId), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<String> delete(@PathVariable Integer personId) {
        return new ResponseEntity<>(personService.delete(personId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.update( personDto), HttpStatus.OK);
    }
}
