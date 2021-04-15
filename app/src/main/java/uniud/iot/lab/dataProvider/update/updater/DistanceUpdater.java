package uniud.iot.lab.dataProvider.update.updater;

import java.util.Timer;

import uniud.iot.lab.dataProvider.update.requester.Requester;

public class DistanceUpdater implements Updater{

    private Requester requester;
    private Timer timer;
    private boolean isRunning = false;

    public DistanceUpdater(Requester requester, Timer timer ){
        this.requester = requester;
        this.timer = timer;

    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }
}
