package uniud.iot.lab.dataProvider.update.updater;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.update.requester.Requester;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyStoppedExceptions;

public class DistanceUpdater extends Thread implements Updater{

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
    public void startUpdated() throws UpdaterAlreadyRunningException {
        this.isRunning = true;
        start();
    }

    @Override
    public void stopUpdate() throws UpdaterAlreadyStoppedExceptions {
        this.isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }



    public void run(){
        while (this.isRunning) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable running" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
