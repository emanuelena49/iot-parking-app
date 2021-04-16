package uniud.iot.lab.dataProvider.update;
import java.util.Observable;

import uniud.iot.lab.dataProvider.update.updater.Updater;


public interface CreateUpdaterService {
    public Updater createUpdater(Observable dataProvider);
}
