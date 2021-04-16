package uniud.iot.lab.dataProvider.update.updater;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.update.requester.DistanceRequester;
import uniud.iot.lab.dataProvider.update.requester.FakeDistanceRequester;
import uniud.iot.lab.dataProvider.update.requester.Requester;
import uniud.iot.lab.dataProvider.update.requester.exceptions.AlreadyUsedDataException;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyStoppedExceptions;

public class DistanceUpdater extends Thread implements Updater{

    private DistanceRequester requester;
    private Timer timer;
    private boolean isRunning = false;
    private DistancesProvider distanceProvider;

    public DistanceUpdater(DistanceRequester requester, Timer timer, DistancesProvider distanceProvider){
        this.requester = requester;
        this.timer = timer;
        this.distanceProvider = distanceProvider;

    }


    @Override
    public void startUpdated() throws UpdaterAlreadyRunningException {
        if (!this.isRunning) {
            this.isRunning = true;
            start();
        }else{
            throw new UpdaterAlreadyRunningException();
        }
    }

    @Override
    public void stopUpdate() throws UpdaterAlreadyStoppedExceptions {
        if (this.isRunning) {
            this.isRunning = false;
        }else{
            throw new UpdaterAlreadyStoppedExceptions();
        }
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }



    public void run(){
        while (this.isRunning) {
            this.requester.request();
            Map<String,Float> response = null;
            try {
                response = this.requester.response();
            } catch (AlreadyUsedDataException e) {
                e.printStackTrace();
            }
            this.distanceProvider.setDistances(response);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
