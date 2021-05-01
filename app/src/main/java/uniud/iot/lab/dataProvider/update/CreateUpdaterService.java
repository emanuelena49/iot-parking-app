package uniud.iot.lab.dataProvider.update;
import uniud.iot.lab.dataProvider.SensorsDataProvider;


public interface CreateUpdaterService {

    /**
     * Create an updater.
     * @param dataProvider instance of DataProvider where to place the data updated.
     * @return instance of Updater.
     */
    Updater createUpdater(SensorsDataProvider dataProvider);
}
