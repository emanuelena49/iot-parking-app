package uniud.iot.lab.dataProvider.update.distance;

import java.net.URI;
import java.util.Map;
import java.util.Timer;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.SensorsDataProvider;
import uniud.iot.lab.dataProvider.update.CreateUpdaterService;
import uniud.iot.lab.dataProvider.update.Requester;
import uniud.iot.lab.dataProvider.update.Updater;

public class CreateDistanceUpdaterService implements CreateUpdaterService {

    @Override
    public Updater createUpdater(SensorsDataProvider dataProvider) {

        DistancesProvider finalDataProvider = (DistancesProvider) dataProvider;
        DistanceResponseHandler responseHandler = new DistanceResponseHandler() {
            @Override
            public void handleResponse(Map<String, Float> response) {

                finalDataProvider.setDistances(response);
            }
        };

        //Requester requester = new FakeDistanceRequester(URI.create("ws://0.0.0.0:5000"), responseHandler);
        Requester requester = new RealDistanceRequester(URI.create("ws://192.168.4.1:6000"), responseHandler);
                Timer timer = new Timer();

        return new DistanceUpdater((DistanceRequester) requester, timer, finalDataProvider);
    }
}
