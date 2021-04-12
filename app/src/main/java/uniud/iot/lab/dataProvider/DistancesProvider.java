package uniud.iot.lab.dataProvider;
import java.util.Map;
import java.util.Observable;

/**
 *
 */
public class DistancesProvider extends Observable {

    private Map<String, Integer> distances;

    public DistancesProvider() {
        distances = null;
    }

    /**
     * Get the most recent distances measured by the sensors.
     *
     * @return the most recent distances
     */
    public Map<String, Integer> getDistances() {

        return distances;
    }

    public void setDistances(Map<String, Integer> distances) {

        this.distances = distances;

        // notify everyone
        setChanged();
        notifyObservers();
    }
}
