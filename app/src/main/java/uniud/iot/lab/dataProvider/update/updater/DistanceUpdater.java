package uniud.iot.lab.dataProvider.update.updater;

import java.util.Timer;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.update.requester.Requester;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyStoppedExceptions;

public class DistanceUpdater implements Updater{

    private Requester requester;
    private Timer timer;
    private boolean isRunning = false;
    private DistancesProvider distanceProvider;

    public DistanceUpdater(Requester requester, Timer timer, DistancesProvider distanceProvider){
        this.requester = requester;
        this.timer = timer;
        this.distanceProvider = distanceProvider;

    }

    @Override
    public void run() throws UpdaterAlreadyRunningException {


    }

    @Override
    public void stop() throws UpdaterAlreadyStoppedExceptions {

    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }
}
