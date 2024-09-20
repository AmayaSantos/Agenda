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
@RequiredArgsConstructor
public class PersonController {

    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> create(PersonDto person) {
        personService.create(person);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<PersonDto>> getAll(PersonFilterDto pageFilter ) {
        return new ResponseEntity<>(personService.getAll(pageFilter), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> get(@PathVariable Integer personId) {
        return new ResponseEntity<>(personService.get(personId), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(@PathVariable Integer personId) {
        personService.delete(personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody PersonDto personDto) {
        personService.update( personDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
