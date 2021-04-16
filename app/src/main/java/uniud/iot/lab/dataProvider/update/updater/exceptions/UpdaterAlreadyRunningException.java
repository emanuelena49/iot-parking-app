package uniud.iot.lab.dataProvider.update.updater.exceptions;

public class UpdaterAlreadyRunningException extends Exception{

    public UpdaterAlreadyRunningException(){
        super("The updater is already running!");
    }

    public UpdaterAlreadyRunningException(String message){
        super(message);
    }
}
