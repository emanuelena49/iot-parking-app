package uniud.iot.lab.dataProvider.update.updater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.update.Updater;
import uniud.iot.lab.dataProvider.update.distance.DistanceUpdater;
import uniud.iot.lab.dataProvider.update.distance.DistanceRequester;
import uniud.iot.lab.dataProvider.update.distance.FakeDistanceRequester;
import uniud.iot.lab.dataProvider.update.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.dataProvider.update.exceptions.UpdaterAlreadyStoppedExceptions;


public class DistanceUpdaterTest {
    private DistanceRequester requester;
    private Timer timer;
    private DistancesProvider distancesProvider;
    private Updater distanceUpdater;

    @Before
    public void setUp(){
        this.requester = new FakeDistanceRequester("0.0.0.0", 0);
        this.timer = new Timer();
        this.distancesProvider = new DistancesProvider();
        this.distanceUpdater = new DistanceUpdater(this.requester, this.timer, this.distancesProvider);
    }



    @Test
    public void runTest(){
        //Tests if the thread starts.
        try {
            this.distanceUpdater.startUpdate();
            TimeUnit.SECONDS.sleep(1);
            Assert.assertTrue(this.distanceUpdater.isRunning());
        } catch (UpdaterAlreadyRunningException | InterruptedException exp){
            Assert.fail("Exception" + exp);
        }


    }
    
    @Test(expected = UpdaterAlreadyRunningException.class)
    public void multipleRun() throws UpdaterAlreadyRunningException{
        this.distanceUpdater.startUpdate();
        this.distanceUpdater.startUpdate();
    }

    @Test
    public void stopTest(){
        try{
            this.distanceUpdater.startUpdate();
            this.distanceUpdater.stopUpdate();
            Assert.assertFalse(this.distanceUpdater.isRunning());
        }
        catch (UpdaterAlreadyRunningException | UpdaterAlreadyStoppedExceptions exp){
            Assert.fail("Exception" + exp);
        }
    }

    @Test(expected = UpdaterAlreadyStoppedExceptions.class)
    public void multipleStop() throws UpdaterAlreadyStoppedExceptions{
        this.distanceUpdater.stopUpdate();
    }


    }


