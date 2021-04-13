package uniud.iot.lab.ui;

import android.view.ViewDebug;
import android.widget.TextView;

import java.io.ObjectStreamException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.utils.Activable;

/**
 * A class used to manage the bottom distances numerical displays.
 */
public class NumericalDistanceDisplay implements Activable, DistanceDisplay, Observer {

    // the 3 boxes where I write the distances
    private TextView d1View, d2View, d3View;

    // the provider of the distances
    private DistancesProvider distancesProvider;

    /**
     * Init with the 3 boxes where I should display the distances
     *
     * @param distanceViews a map which associate the box to the distance name (ex. "right": ...)
     * @param distancesProvider the provider of the distances
     */
    public NumericalDistanceDisplay(Map<String, TextView> distanceViews, DistancesProvider distancesProvider) {

        try {
            this.d1View = distanceViews.get("right");
            this.d2View = distanceViews.get("center");
            this.d3View = distanceViews.get("left");
        } catch (NullPointerException e) {
            // todo: raise something
        }

        this.distancesProvider = distancesProvider;

        activate();
    }

    @Override
    public void renderDistances(Float d1, Float d2, Float d3) {

        String measureUnit = " cm";

        d1View.setText(d1.toString().concat(measureUnit));
        d2View.setText(d2.toString().concat(measureUnit));
        d3View.setText(d3.toString().concat(measureUnit));
    }

    @Override
    public void setError() {

        String noValue = "--.-- cm";

        d1View.setText(noValue);
        d2View.setText(noValue);
        d3View.setText(noValue);
    }

    @Override
    public void update(Observable o, Object arg) {

        if(distancesProvider.hasChanged()) {
            Map<String, Float> distances = distancesProvider.getDistances();

            if (distances == null) {

                // if I don't have distances I set an error message
                setError();
            } else {

                // if I have them, I render
                try {
                    renderDistances(
                            distances.get("right"),
                            distances.get("center"),
                            distances.get("left")
                    );
                } catch(NullPointerException e) {
                    setError();
                    // todo: raise something
                }
            }
        }
    }

    // bool param to implement Activable
    private boolean active;

    @Override
    public void activate() {
        active = true;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
