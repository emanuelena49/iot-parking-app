package uniud.iot.lab.dataProvider;

import java.util.Observable;
import java.util.Observer;

/**
 * A tool to know the status of
 */
public class SensorsStatusProvider extends Observable implements Observer {

    private boolean distanceAvailable, videoAvailable;
    private DistancesProvider distancesProvider;
    private VideoProvider videoProvider;

    public SensorsStatusProvider(DistancesProvider distancesProvider, VideoProvider videoProvider) {
        this.distancesProvider = distancesProvider;
        this.videoProvider = videoProvider;

        this.distancesProvider.addObserver(this);
        this.videoProvider.addObserver(this);
    }

    /**
     * Check if distance data are available
     *
     * @return
     */
    public boolean isDistanceAvailable() {
        return distanceAvailable;
    }

    /**
     * Check if video streaming is available
     *
     * @return
     */
    public boolean isVideoAvailable() {
        return videoAvailable;
    }

    /**
     * Check quickly if everything is working properly
     *
     * @return
     */
    public boolean isEverythingAvailable() {
        return isDistanceAvailable() && isVideoAvailable();
    }

    /**
     * Check if I am connected to sensors. (I assume to be connected if
     * at least one of the two streams is working properly)
     *
     * @return
     */
    public boolean isConnected() {
        return isDistanceAvailable() || isVideoAvailable();
    }

    @Override
    public void update(Observable o, Object arg) {

        // save old states (just to check if something changed)
        boolean precedentDistState = isDistanceAvailable(),
                precedentVideoState = isVideoAvailable();


        // get new video state
        if (videoProvider.hasChanged()) {
            videoAvailable = videoProvider.isAvailable();
        }

        // get new distance state
        if (distancesProvider.hasChanged()) {
            distanceAvailable = distancesProvider.isAvailable();
        }

        // if something changed in video/distance availability, I notify my observers
        if (precedentDistState != isDistanceAvailable() || precedentVideoState != isVideoAvailable()) {
            setChanged();
            notifyObservers();
        }
    }
}
