package uniud.iot.lab.dataProvider.update.video;

import android.graphics.Bitmap;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.nio.ByteBuffer;

import uniud.iot.lab.dataProvider.VideoProvider;

/**
 * A tool which, when stared, it listen for new video frames from a socket and
 * it updates a passed {@link uniud.iot.lab.dataProvider.VideoProvider}.
 */
public class ConcreteVideoFluxListener implements VideoFluxListener {

    // todo: add even web socket
    private BitmapConverter bitmapConverter;
    private VideoProvider providerToUpdate;
    private WebSocketClient client;
    private boolean initDone;
    private boolean isRunning;
    private URI uri;

    public ConcreteVideoFluxListener(VideoProvider providerToUpdate, URI uri) {

        this.providerToUpdate = providerToUpdate;
        isRunning = false;
        this.uri = uri;
    }

    /**
     * Start listening and start keeping a certain provider updated
     */
    @Override
    public void startListening() {

        ConcreteVideoFluxListener thisFluxListener = this;
        initDone = false;

        this.client = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                thisFluxListener.isRunning = true;
            }

            @Override
            public void onMessage(String message) {

                // If I need, run init procedure, else handle video frame.
                if(!thisFluxListener.initDone) {
                    thisFluxListener.handleInitMessage(message);
                } else {
                    //thisFluxListener.handleVideoFrame(message);
                }
            }

            @Override
            public void onMessage(ByteBuffer message) {
                if(!thisFluxListener.initDone) {
                    // thisFluxListener.handleInitMessage(message);
                } else {
                    thisFluxListener.handleVideoFrame(message);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                thisFluxListener.isRunning = false;
                Log.w("VIDEO_CLOSE", reason);
                Log.w("VIDEO_CLOSE", Boolean.toString(remote));
            }

            @Override
            public void onError(Exception ex) {
                Log.e("VIDEO_EXCEPTION", ex.getMessage());
            }
        };

        client.connect();
    }

    /**
     * Close the video connection
     */
    @Override
    public void closeConnection() { client.close(); }

    /**
     * Check if video flux process is running
     * @return true if it is running, else false
     */
    @Override
    public boolean isRunning() { return isRunning; }

    /**
     * Handle the init message and set up this to handle
     * the frame video flux.
     *
     * @param message
     */
    private void handleInitMessage(String message) {

        int width, height;
        String format;

        // parse config info from init message
        try {
            JSONObject obj = new JSONObject(message);

            width = obj.getInt("width");
            height = obj.getInt("heigth");
            format = obj.getString("format");

        } catch (Throwable tx) {
            Log.e("My App", "Could not parse malformed JSON: \"" + message + "\"");
            client.close();
            return;
        }

        // generate a byte[] -> Bitmap converter
        bitmapConverter = new BitmapConverter(width, height, format);

        // (ok, I completed init process)
        initDone = true;

        // send an okay message to start the video frame flux
        client.send("OK");
    }

    /**
     * Handle a single video frame. It will extract the image and pass
     * it to the {@link VideoProvider}.
     *
     * @param message the video frame as (bytes) string (as received from web socket)
     */
    private void handleVideoFrame(ByteBuffer message) {

        // extract image's raw bytes
        byte[] rawBytesFrame = message.array();

        // convert it as Bitmap
        Bitmap frame = bitmapConverter.getBitmapImage(rawBytesFrame);

        // update the provider
        providerToUpdate.setFrame(frame);
    }
}
