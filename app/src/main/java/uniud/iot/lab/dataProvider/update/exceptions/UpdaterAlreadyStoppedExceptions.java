package uniud.iot.lab.dataProvider.update.updater.exceptions;

import uniud.iot.lab.dataProvider.update.updater.Updater;

public class UpdaterAlreadyStoppedExceptions extends Exception{
    public UpdaterAlreadyStoppedExceptions(){
        super("Updater already stopped.");
    }

    public UpdaterAlreadyStoppedExceptions(String message){
        super(message);
    }
}
