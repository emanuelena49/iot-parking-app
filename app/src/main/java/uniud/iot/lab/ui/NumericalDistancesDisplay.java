package uniud.iot.lab.ui;

import android.widget.TextView;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.utils.Activable;

/**
 * A class used to manage the bottom distances numerical displays.
 */
public class NumericalDistancesDisplay implements Activable, DistancesDisplay, Observer {

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
    public NumericalDistancesDisplay(Map<String, TextView> distanceViews, DistancesProvider distancesProvider) {

        try {
            this.d1View = distanceViews.get("right");
            this.d2View = distanceViews.get("center");
            this.d3View = distanceViews.get("left");
        } catch (NullPointerException e) {
            // todo: raise something
        }

        this.distancesProvider = distancesProvider;
        this.distancesProvider.addObserver(this);

        activate();
    }

    private static String distanceToString(Float value) {

        // round it and get it as string
        Integer roundedValue = Math.round(value);
        String strValue = roundedValue.toString();

        // append missing zeros (ex. "15" -> "015")
        // to obtain an uniform style
        for (int i=strValue.length(); i<3; i++) {
            strValue = "0".concat(strValue);
        }

        return strValue;
    }

    @Override
    public void renderDistances(Float d1, Float d2, Float d3) {

        d1View.setText(distanceToString(d1));
        d2View.setText(distanceToString(d2));
        d3View.setText(distanceToString(d3));
    }

    @Override
    public void setError() {

        String noValue = "---";

        d1View.setText(noValue);
        d2View.setText(noValue);
        d3View.setText(noValue);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (this.isActive()) {
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
    public void activate() { active = true; }

    @Override
    public void deactivate() {

        active = false;
        d1View.setText("");
        d2View.setText("");
        d3View.setText("");
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
