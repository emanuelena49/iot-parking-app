package uniud.iot.lab.dataProvider;

/**
 * A generic sensors data provider
 */
public interface SensorsDataProvider {

    /**
     * Check if this data provider is currently working.
     *
     * @return true if this data provider has currently data
     */
    boolean isAvailable();
}
