package uniud.iot.lab.dataProvider.update;
import java.util.Observable;

import uniud.iot.lab.dataProvider.update.updater.Updater;


public interface CreateUpdaterService {

    /**
     * Create an updater.
     * @param dataProvider instance of DataProvider where to place the data updated.
     * @return instance of Updater.
     */
    public Updater createUpdater(Observable dataProvider);
}
