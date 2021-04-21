package uniud.iot.lab.ui;

import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.SensorsStatusProvider;

/**
 * A manager for the aside status indicator. It changes the box color according
 * to how status change.
 */
public class StatusLight implements StatusDisplay, Observer {

    SensorsStatusProvider statusProvider;
    TextView statusLight;
    int colorOkay, colorNotSoGood, colorNothingIsWorking;

    public StatusLight(SensorsStatusProvider statusProvider, TextView statusLight,
                       int colorOkay, int colorNotSoGood, int colorNothingIsWorking) {
        this.statusProvider = statusProvider;
        this.statusLight = statusLight;
        this.colorOkay = colorOkay;
        this.colorNotSoGood = colorNotSoGood;
        this.colorNothingIsWorking = colorNothingIsWorking;

        // register to statusProvider for updates
        this.statusProvider.addObserver(this);
    }
    @Override
    public void setEverythingOkayStatus() {
        statusLight.setBackgroundColor(colorOkay);
    }

    @Override
    public void setSomethingNotOkayStatus() {
        statusLight.setBackgroundColor(colorNotSoGood);
    }

    @Override
    public void setNotWorkingStatus() {
        statusLight.setBackgroundColor(colorNothingIsWorking);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (statusProvider.isEverythingAvailable()) {
            setEverythingOkayStatus();
        } else if (!statusProvider.isConnected()) {
            setNotWorkingStatus();
        } else {
            setSomethingNotOkayStatus();
        }
    }
}
