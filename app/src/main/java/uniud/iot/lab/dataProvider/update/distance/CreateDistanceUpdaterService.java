package uniud.iot.lab.dataProvider.update.distance;

import java.util.Observable;
import java.util.Timer;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.update.CreateUpdaterService;
import uniud.iot.lab.dataProvider.update.Requester;
import uniud.iot.lab.dataProvider.update.RequesterFactory;
import uniud.iot.lab.dataProvider.update.Updater;

public class CreateDistanceUpdaterService implements CreateUpdaterService {

    @Override
    public Updater createUpdater(Observable dataProvider) {
        // TODO: import configurations from a config file.

        // TODO: configurable update time interval (in ms)
        Requester requester = RequesterFactory.getFakeDistanceRequester("0.0.0.0", 0);
        Timer timer = new Timer();

        return new DistanceUpdater((DistanceRequester) requester, timer, (DistancesProvider) dataProvider);
    }
}
