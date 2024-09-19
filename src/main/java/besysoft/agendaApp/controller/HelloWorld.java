package besysoft.agendaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saludador")
public class HelloWorld {
    @GetMapping(path = "/saludar")
    public ResponseEntity<String> saludar() {

        return new ResponseEntity<>("hola assss",HttpStatus.OK);
    }
}
