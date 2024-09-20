package besysoft.agendaApp.exeptions.errores;

import besysoft.agendaApp.exeptions.AppError;


public enum ValidacionError implements AppError {

    NOT_VALID("NOT_VALID", "");

    private  String menssage;

    ValidacionError(String name, String menssage) {
        this.menssage = menssage;
    }


    @Override
    public String getMessage() {
        return menssage;
    }

    @Override
    public String getId() {
        return "7";
    }

    public void setMenssage(String collect) {
        this.menssage=collect;
    }
}