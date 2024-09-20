package besysoft.agendaApp.exeptions.errores;


import besysoft.agendaApp.exeptions.AppError;

public enum PersonError implements AppError {

    PERSON_NOT_FOUND("Persona no encontrada","4"),

    ;

    private final String message;
    private final String id;

    PersonError(String message, String id) {
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
