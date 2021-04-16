package uniud.iot.lab.dataProvider.update.updater;

import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyStoppedExceptions;

public interface Updater {
    public void startUpdated() throws UpdaterAlreadyRunningException;
    public void stopUpdate() throws UpdaterAlreadyStoppedExceptions;
    public boolean isRunning();

}
