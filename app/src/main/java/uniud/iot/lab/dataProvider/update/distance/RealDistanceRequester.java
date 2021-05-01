package uniud.iot.lab.dataProvider.update.distance;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RealDistanceRequester implements DistanceRequester {

    private WebSocketClient client;
    private Map<String, Float> lastResponse;
    private DistanceResponseHandler responseHandler;

    public RealDistanceRequester(URI uri, DistanceResponseHandler responseHandler) {

        this.responseHandler = responseHandler;

        RealDistanceRequester requester = this;

        this.client = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {
                requester.setResponse(message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
    }

    private void setResponse(String message) {
        try {
            JSONObject obj = new JSONObject(message);

            lastResponse = new HashMap<String, Float>();

            lastResponse.put("right", (float) obj.getInt("right"));
            lastResponse.put("left", (float) obj.getInt("left"));
            lastResponse.put("center", (float) obj.getInt("center"));

            responseHandler.handleResponse(lastResponse);

        } catch (Throwable tx) {
            Log.e("My App", "Could not parse malformed JSON: \"" + message + "\"");
            return;
        }
    }

    /*
    @Override
    public Map<String, Float> response() throws AlreadyUsedDataException {
        return lastResponse;
    }*/

    @Override
    public void request() {
        client.send("");
    }
}
