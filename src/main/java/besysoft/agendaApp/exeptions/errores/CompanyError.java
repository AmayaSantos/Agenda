package besysoft.agendaApp.exeptions.errores;


import besysoft.agendaApp.exeptions.AppError;

public enum CompanyError implements AppError {

    COMPANY_NOT_FOUND("Empresa no encontrada","5"),
    CONTACT_ALREADY_ADDED("Contacto ya agregado", "6"),
    CONTACT_NOT_ADDED("Contacto no agregado", "6")
    ;

    private final String message;
    private final String id;

    CompanyError(String message, String id) {
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
