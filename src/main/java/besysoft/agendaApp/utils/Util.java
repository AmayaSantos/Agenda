package besysoft.agendaApp.utils;

import besysoft.agendaApp.exeptions.AppException;
import besysoft.agendaApp.exeptions.errores.ValidacionError;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Util {
    public static void valid(Object o){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> validate = validator.validate(o);
        if (!validate.isEmpty())
            crearErrorDeValidacion(validate);
    }

    private static void crearErrorDeValidacion(Set<ConstraintViolation<Object>> validate) {
        ValidacionError validacionError = ValidacionError.NOT_VALID;
        validacionError.setMenssage(
                validate.stream()
                        .map(ConstraintViolation::getMessage)
                        .map(s -> s+", ")
                        .collect(Collectors.joining())
        );

        throw new AppException(ValidacionError.NOT_VALID, HttpStatus.BAD_REQUEST);
    }
}
