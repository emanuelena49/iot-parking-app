package uniud.iot.lab.dataProvider.update.video;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.IOException;
import java.io.InputStream;

import uniud.iot.lab.R;
import uniud.iot.lab.dataProvider.VideoProvider;

public class FakeVideoFluxListener implements VideoFluxListener {

    private boolean isRunning;
    private BitmapConverter converter;
    private Context applicationContext;
    private BitmapConverter bitmapConverter;
    private VideoProvider provider;

    public FakeVideoFluxListener(VideoProvider provider, Context applicationContext) {
        isRunning = false;
        this.applicationContext = applicationContext;
        this.provider = provider;
    }

    @Override
    public void startListening() {
        isRunning = true;

        // init converter
        bitmapConverter = new BitmapConverter(160, 120, "RGB_565");

        // simulate 1 frame parsing
        byte[] data = null;
        try {
            InputStream inputStream = applicationContext.getResources().openRawResource(R.raw.prova160x120bin);
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap image = bitmapConverter.getBitmapImage(data);

        // simulate just 1 frame update
        provider.setFrame(image);
    }

    @Override
    public void closeConnection() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
