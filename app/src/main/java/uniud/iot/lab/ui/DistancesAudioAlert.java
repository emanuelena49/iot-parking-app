package uniud.iot.lab.ui;

import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.utils.Activable;

/**
 * A tool to alert with an incremental "bip bip bip" when distance is
 * becoming too short.
 */
public class DistancesAudioAlert implements Activable, Observer {

    boolean active;

    public DistancesAudioAlert(DistancesProvider distanceProvider) {
        // todo: implement
    }

    @Override
    public void activate() { active = true; }

    @Override
    public void deactivate() { active = false; }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
