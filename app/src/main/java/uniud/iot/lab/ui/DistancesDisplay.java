package uniud.iot.lab.ui;

import uniud.iot.lab.dataProvider.DistancesProvider;

/**
 * A generic manager for an UI element that should render the 3 distances
 */
public interface DistancesDisplay {

    /**
     * Render the distances
     *
     * @param d1
     * @param d2
     * @param d3
     */
    void renderDistances(Float d1, Float d2, Float d3);

    /**
     * Render an error message instead of the distances
     */
    void setError();
}
