package uniud.iot.lab.dataProvider.update.distance;

import uniud.iot.lab.dataProvider.update.Requester;
import uniud.iot.lab.dataProvider.update.exceptions.AlreadyUsedDataException;

public interface DistanceRequester extends Requester {
    /**
     * Returns data coming from a request.
     * @return data in Map format.
     * @throws AlreadyUsedDataException If data has been already used. In order to not have this exception always before call response, call a request.
     */
    // public Map<String, Float> response() throws AlreadyUsedDataException;
}
