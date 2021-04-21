package uniud.iot.lab.dataProvider;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import java.util.Observable;

/**
 * A provider for the current video caught by the camera.
 */
public class VideoProvider extends Observable implements SensorsDataProvider {

    private Bitmap frame;

    public VideoProvider() { frame = null; }

    public Bitmap getFrame() {
        return frame;
    }

    public void setFrame(Bitmap frame) {
        this.frame = frame;

        // notify everyone (with an handler to exist from thread)
        setChanged();
        VideoProvider thisProvider = this;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                thisProvider.notifyObservers();
            }
        });
    }

    @Override
    public boolean isAvailable() {
        if (getFrame() != null) {
            return true;
        } else {
            return false;
        }
    }
}
