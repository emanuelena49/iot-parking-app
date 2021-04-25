package uniud.iot.lab.ui.distance;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.DistancesProvider;

public class GraphicalDistancesDisplay implements Observer, DistancesDisplay {

    // the provider of the distances
    private DistancesProvider distancesProvider;

    // the 3 columns
    private GraphicalDistancesColumn d1Column, d2Column, d3Column;

    /**
     * Init passing the 3 columns.
     *
     * @param columns
     * @param distancesProvider
     */
    public GraphicalDistancesDisplay(
            Map<String, GraphicalDistancesColumn> columns,
            DistancesProvider distancesProvider) {

        try {
            this.d1Column = columns.get("right");
            this.d2Column = columns.get("center");
            this.d3Column = columns.get("left");
        } catch (NullPointerException e) {
            // todo: raise something
        }

        this.distancesProvider = distancesProvider;
        distancesProvider.addObserver(this);
    }

    @Override
    public void renderDistances(Float d1, Float d2, Float d3) {
        this.d1Column.renderDistance(d1);
        this.d2Column.renderDistance(d2);
        this.d3Column.renderDistance(d3);
    }

    @Override
    public void setError() {
        this.d1Column.setError();
        this.d2Column.setError();
        this.d3Column.setError();
    }

    @Override
    public void update(Observable o, Object arg) {

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
