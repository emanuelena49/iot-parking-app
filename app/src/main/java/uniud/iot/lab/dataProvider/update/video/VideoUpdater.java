package uniud.iot.lab.dataProvider.update.video;

import uniud.iot.lab.dataProvider.update.Updater;
import uniud.iot.lab.dataProvider.update.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.exceptions.UpdaterAlreadyStoppedExceptions;

/**
 * A tool to ensure {@link uniud.iot.lab.dataProvider.VideoProvider} is always updated
 * with the last video frame from the sensors camera.
 */
public class VideoUpdater extends Thread implements Updater {

    private VideoFluxListener fluxListener;

    public VideoUpdater(VideoFluxListener fluxListener) {
        this.fluxListener = fluxListener;
    }

    @Override
    public void startUpdate() throws UpdaterAlreadyRunningException {

        // init a video flux listener and start the process
        // todo: do all that async

        if (fluxListener.isRunning()) {
            throw new UpdaterAlreadyRunningException("The Video update process is already running");
        }
        start();
    }

    @Override
    public void stopUpdate() throws UpdaterAlreadyStoppedExceptions {

        if (!fluxListener.isRunning()) {
            throw new UpdaterAlreadyStoppedExceptions("The Video update process is already stopped");
        }
        fluxListener.closeConnection();
    }

    @Override
    public boolean isRunning() {
        return fluxListener.isRunning();
    }

    @Override
    public void run() {
        fluxListener.startListening();
    }
}
