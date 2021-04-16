package uniud.iot.lab.dataProvider;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Map;
import java.util.Observable;

/**
 *
 */
public class DistancesProvider extends Observable {

    private Map<String, Float> distances;

    public DistancesProvider() {
        distances = null;
    }

    /**
     * Get the most recent distances measured by the sensors.
     *
     * @return the most recent distances
     */
    public Map<String, Float> getDistances() {

        return distances;
    }

    public void setDistances(Map<String, Float> distances) {

        this.distances = distances;

        // notify everyone
        setChanged();

        DistancesProvider thisProvider = this;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                thisProvider.notifyObservers();
            }
        });
        /*
        new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message message) {
                // This is where you do your work in the UI thread.
                // Your worker tells you in the message what to do.


            }
        };*/
    }
}
