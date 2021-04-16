package uniud.iot.lab.dataProvider.update.requester;

import java.util.Map;

import uniud.iot.lab.dataProvider.update.requester.exceptions.AlreadyUsedDataException;

public interface DistanceRequester extends Requester {
    public Map<String, Float> response() throws AlreadyUsedDataException;
}
