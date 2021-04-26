package uniud.iot.lab.dataProvider.update.video;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import uniud.iot.lab.dataProvider.VideoProvider;

/**
 * A tool which, when stared, it listen for new video frames from a socket and
 * it updates a passed {@link uniud.iot.lab.dataProvider.VideoProvider}.
 */
public class VideoFluxListener {

    // todo: add even web socket
    private BitmapConverter bitmapConverter;
    private VideoProvider providerToUpdate;
    private WebSocketClient client;
    private boolean initDone;

    public VideoFluxListener(VideoProvider providerToUpdate) {

        this.providerToUpdate = providerToUpdate;
    }

    /**
     * Start listening and start keeping a certain provider updated
     *
     * @param uri the uri where you expect to find the video
     *            frame streaming service
     */
    public void startListening() {

        URI uri;
        try {
            uri = new URI("ws://192.168.1.135:5000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        VideoFluxListener thisFluxListener = this;
        initDone = false;

        this.client = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {

                // If I need, run init procedure, else handle video frame.
                if(!thisFluxListener.initDone) {
                    thisFluxListener.handleInitMessage(message);
                } else {
                    thisFluxListener.handleVideoFrame(message);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };

        client.connect();
    }

    public void closeConnection() {
        // todo: implement according to web socket mechanism
    }

    public boolean isRunning() {
        // todo: implement according to web socket mechanism
        return false;
    }

    private void handleInitMessage(String message) {
        // todo: parse json, build the bitmap translator and send okay message

    }

    private void handleVideoFrame(String message) {
        // todo: implement bitmap parsing + update provider
    }
}
