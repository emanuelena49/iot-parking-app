package uniud.iot.lab.ui.status;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.SensorsStatusProvider;
import uniud.iot.lab.ui.status.StatusDisplay;

public class NoConnectionAlertDisplay implements StatusDisplay, Observer {

    SensorsStatusProvider statusProvider;
    AlertDialog alertDialog;
    boolean alertDialogShown;


    public NoConnectionAlertDisplay(SensorsStatusProvider statusProvider,
                                    Activity currentActivity, Context currentContext,
                                    String alertTitle, String alertMessage) {
        this.statusProvider = statusProvider;

        // build alert dialog
        alertDialog = new AlertDialog.Builder(currentActivity).create();
        alertDialog.setTitle(alertTitle);
        alertDialog.setMessage(alertMessage);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogShown = false;

        // register to status provider
        this.statusProvider.addObserver(this);

        this.update(this.statusProvider, null);
    }


    @Override
    public void update(Observable o, Object arg) {

        if (statusProvider.isConnected()) {
            setEverythingOkayStatus();
        } else {
            setNotWorkingStatus();
        }
    }

    @Override
    public void setEverythingOkayStatus() {

        // hide eventual alert dialog
        if (alertDialogShown) {
            // (reset alert dialog flag)
            alertDialogShown = false;

            // hide it
            alertDialog.dismiss();
        }
    }

    @Override
    public void setSomethingNotOkayStatus() {
        setEverythingOkayStatus();
    }

    @Override
    public void setNotWorkingStatus() {

        // show alert
        // (use this flag to not show alert too many times)
        if (!alertDialogShown) {
            alertDialogShown = true;
            alertDialog.show();
        }
    }
}
