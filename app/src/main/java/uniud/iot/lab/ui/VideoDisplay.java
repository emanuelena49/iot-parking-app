package uniud.iot.lab.ui;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.VideoProvider;

/**
 * A tool to display video frames on the screen every time {@link VideoProvider} updates.
 */
public class VideoDisplay implements Observer {

    private VideoProvider videoProvider;
    private ImageView display;
    
    public VideoDisplay(VideoProvider videoProvider, ImageView display) {
        this.videoProvider = videoProvider;
        this.display = display;
    }

    /**
     * Display a single frame
     *
     * @param frame the frame as bitmap image
     */
    public void displayFrame(Bitmap frame) {
        display.setImageBitmap(frame);
    }

    /**
     * Display and error frame (do that if video is no available)
     */
    public void setError() {
        // todo: display an error message
    }

    @Override
    public void update(Observable o, Object arg) {
        // todo: write the update code
    }
}
