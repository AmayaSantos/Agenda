package besysoft.agendaApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @PostMapping(path = "/hola", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saludar() {
        return new ResponseEntity<>("hola",HttpStatus.OK);
    }
}
