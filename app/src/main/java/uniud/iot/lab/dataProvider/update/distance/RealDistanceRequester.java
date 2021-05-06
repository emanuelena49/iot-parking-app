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
    private boolean isConnected;

    public RealDistanceRequester(URI uri, DistanceResponseHandler responseHandler) {

        this.responseHandler = responseHandler;

        RealDistanceRequester requester = this;

        this.isConnected = false;

        /*
        ArrayList<IProtocol> protocols = new ArrayList<IProtocol>();
        protocols.add(new Protocol("my-protocol"));

        //Uncomment below if you want to have a fallback
        //protocols.add(new Protocol(""));
        Draft_6455 my_draft = new Draft_6455(Collections.<IExtension>emptyList(), protocols);*/

        this.client = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {

                requester.isConnected = true;
            }

            @Override
            public void onMessage(String message) {

                requester.setResponse(message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.w("CLOSING", reason);
                Log.w("CLOSING", Boolean.toString(remote));
            }

            @Override
            public void onError(Exception ex) {
                Log.e("CLOSING", ex.getMessage());
            }
        };

        client.connect();
    }

    private void setResponse(String message) {
        try {
            JSONObject obj = new JSONObject(message);

            lastResponse = new HashMap<String, Float>();

            lastResponse.put("right", (float) obj.getInt("C"));
            lastResponse.put("left", (float) obj.getInt("A"));
            lastResponse.put("center", (float) obj.getInt("B"));

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

        if (isConnected) {
            client.send("");
        } else {
            Log.i("NOT_CONNECTED", "Not yet connected");
        }
    }
}
