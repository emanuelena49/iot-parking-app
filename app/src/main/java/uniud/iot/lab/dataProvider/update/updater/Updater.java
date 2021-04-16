package uniud.iot.lab.dataProvider.update.updater;

import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyStoppedExceptions;

public interface Updater {
    /**
     * Starts a Thread that make the update.
     * @throws UpdaterAlreadyRunningException If the Thread is already running.
     */
    public void startUpdated() throws UpdaterAlreadyRunningException;

    /**
     * Stops the Thread that make the update.
     * @throws UpdaterAlreadyStoppedExceptions If the Thread is already stopped.
     */
    public void stopUpdate() throws UpdaterAlreadyStoppedExceptions;

    /**
     * Checks if the Thread that make the update is running.
     * @return true if is running, false if not.
     */
    public boolean isRunning();

}
