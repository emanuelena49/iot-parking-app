package uniud.iot.lab.dataProvider.update.video;

import uniud.iot.lab.dataProvider.VideoProvider;
import uniud.iot.lab.dataProvider.update.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.exceptions.UpdaterAlreadyStoppedExceptions;
import uniud.iot.lab.dataProvider.update.Updater;

/**
 * A tool to ensure {@link uniud.iot.lab.dataProvider.VideoProvider} is always updated
 * with the last video frame from the sensors camera.
 */
public class VideoUpdater implements Updater {

    private VideoProvider providerToKeepUpdated;
    private VideoFluxListener fluxListener;

    /**
     *
     * @param providerToKeepUpdated the provider I want to keep updated
     */
    public VideoUpdater(VideoProvider providerToKeepUpdated) {
        this.providerToKeepUpdated = providerToKeepUpdated;
    }

    @Override
    public void startUpdate() throws UpdaterAlreadyRunningException {

        // init a video flux listener and start the process
        // todo: do all that async
        VideoConnectionInitializer initializer = new VideoConnectionInitializer();
        fluxListener = initializer.initConnection();
        fluxListener.startListening(providerToKeepUpdated);
    }

    @Override
    public void stopUpdate() throws UpdaterAlreadyStoppedExceptions {
        fluxListener.closeConnection();
    }

    @Override
    public boolean isRunning() {
        return fluxListener.isListening();
    }
}
