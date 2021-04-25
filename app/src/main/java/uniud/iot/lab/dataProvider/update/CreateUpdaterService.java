package uniud.iot.lab.dataProvider.update;
import java.util.Observable;


public interface CreateUpdaterService {

    /**
     * Create an updater.
     * @param dataProvider instance of DataProvider where to place the data updated.
     * @return instance of Updater.
     */
    Updater createUpdater(Observable dataProvider);
}
