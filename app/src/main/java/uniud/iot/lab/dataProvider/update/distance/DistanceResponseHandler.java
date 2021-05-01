package uniud.iot.lab.dataProvider.update.distance;

import java.util.Map;

public interface DistanceResponseHandler {

    void handleResponse(Map<String, Float> response);
}
