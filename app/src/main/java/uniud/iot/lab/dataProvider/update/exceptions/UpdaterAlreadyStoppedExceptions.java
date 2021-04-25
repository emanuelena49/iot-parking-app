package uniud.iot.lab.dataProvider.update.exceptions;

public class UpdaterAlreadyStoppedExceptions extends Exception{
    public UpdaterAlreadyStoppedExceptions(){
        super("Updater already stopped.");
    }

    public UpdaterAlreadyStoppedExceptions(String message){
        super(message);
    }
}
