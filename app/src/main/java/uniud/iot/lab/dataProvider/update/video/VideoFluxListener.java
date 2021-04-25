package uniud.iot.lab.dataProvider.update.video;

import uniud.iot.lab.dataProvider.VideoProvider;

/**
 * A tool which, when stared, it listen for new video frames from a socket and
 * it updates a passed {@link uniud.iot.lab.dataProvider.VideoProvider}.
 */
public class VideoFluxListener {

    // todo: add even web socket
    private BitmapConverter bitmapConverter;
    private VideoProvider providerToUpdate;

    public VideoFluxListener(BitmapConverter converter) {

        // todo: take as parameter even an initialized web socket
        this.bitmapConverter = converter;
    }

    /**
     * Start listening and start keeping a certain provider updated
     *
     * @param providerToUpdate the provider you want to keep updated
     */
    public void startListening(VideoProvider providerToUpdate) {

        this.providerToUpdate = providerToUpdate;
        // todo: implement the rest
    }

    public void closeConnection() {
        // todo: implement according to web socket mechanism
    }

    public boolean isListening() {
        // todo: implement according to web socket mechanism
        return false;
    }

    private void handleMessage() {
        // todo: implement according to web socket mechanism
    }
}
