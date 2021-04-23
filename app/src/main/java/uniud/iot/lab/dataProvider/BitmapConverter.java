package uniud.iot.lab.dataProvider;

import android.graphics.Bitmap;
import android.icu.text.BidiRun;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * A tool to parse a Bitmap image from a raw bit stream
 */
public class BitmapConverter {

    // the height and the width of the image in pixel
    private int width, height;

    private String format;

    /**
     *
     * @param width the expected width of the images
     * @param height the expected width of the images
     * @param format the format bit arrays will be encoded
     */
    public BitmapConverter(int width, int height, String format) {

        this.width = width;
        this.height = height;
        this.format = format;
    }

    public Bitmap getBitmapImage(byte[] rawImage) {

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        ByteBuffer buffer = ByteBuffer.wrap(rawImage);
        bmp.copyPixelsFromBuffer(buffer);

        return bmp;
    }
}
