package uniud.iot.lab.dataProvider;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Map;
import java.util.Observable;

/**
 * A provider for the distances read by sensors.
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

        // notify everyone (with an handler to exist from thread)
        setChanged();
        DistancesProvider thisProvider = this;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                thisProvider.notifyObservers();
            }
        });
    }
}
