package besysoft.agendaApp.exeptions.errores;


import besysoft.agendaApp.exeptions.AppError;

public enum UserError implements AppError {

    USER_NOT_FOUNT("Usiario no encontrado","1"),
    USER_AlREADY_EXIST("Usiario ya existe","2")
    ;

    private final String message;
    private final String id;

    UserError(String message, String id) {
        this.message = message;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }
}
