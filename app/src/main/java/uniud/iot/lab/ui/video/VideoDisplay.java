package uniud.iot.lab.ui.video;

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
    private Bitmap errorImage;

    public VideoDisplay(VideoProvider videoProvider, ImageView display, Bitmap errorImage) {
        this.videoProvider = videoProvider;
        this.display = display;
        this.errorImage = errorImage;

        displayError();
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
    public void displayError() {
        display.setImageBitmap(errorImage);
    }

    @Override
    public void update(Observable o, Object arg) {

        Bitmap frame = videoProvider.getFrame();

        if (frame != null) {
            displayFrame(frame);
        } else {
            displayError();
        }
    }
}
