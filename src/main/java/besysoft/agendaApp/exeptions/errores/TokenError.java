package besysoft.agendaApp.exeptions.errores;


import besysoft.agendaApp.exeptions.AppError;

public enum TokenError implements AppError {

    TOKEN_INVALID("Token invalido","3")
    ;

    private final String message;
    private final String id;

    TokenError(String message, String id) {
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
