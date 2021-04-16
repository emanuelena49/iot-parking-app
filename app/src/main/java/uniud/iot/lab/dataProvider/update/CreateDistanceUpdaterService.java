package uniud.iot.lab.dataProvider.update;

import java.util.Observable;
import java.util.Timer;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.update.requester.DistanceRequester;
import uniud.iot.lab.dataProvider.update.requester.Requester;
import uniud.iot.lab.dataProvider.update.requester.RequesterFactory;
import uniud.iot.lab.dataProvider.update.updater.DistanceUpdater;
import uniud.iot.lab.dataProvider.update.updater.Updater;

public class CreateDistanceUpdaterService implements CreateUpdaterService {

    @Override
    public Updater createUpdater(Observable dataProvider) {
        //TODO: import configurations from a config file.
        Requester requester = RequesterFactory.getFakeDistanceRequester("0.0.0.0", 0);
        Timer timer = new Timer();

        return new DistanceUpdater((DistanceRequester) requester, timer, (DistancesProvider) dataProvider);
    }
}
