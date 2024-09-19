package besysoft.agendaApp.exeptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppException extends RuntimeException {
    private HttpStatus httpStatus;
    private final AppError error;

    public AppException(AppError error, HttpStatus httpStatus) {
        super(error.getMessage());
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public AppException(AppError error) {
        super(error.getMessage());
        this.error = error;
    }


    public String getCode() {
        return error.getId();
    }
}
