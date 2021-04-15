package uniud.iot.lab.dataProvider.update;
import java.util.Observable;

import uniud.iot.lab.dataProvider.update.updater.Updater;


public interface UpdaterService {
    public Updater createUpdater(Observable dataProvider);
}
