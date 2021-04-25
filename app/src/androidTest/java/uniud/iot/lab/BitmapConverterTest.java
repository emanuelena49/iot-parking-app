package uniud.iot.lab;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import uniud.iot.lab.dataProvider.update.video.BitmapConverter;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BitmapConverterTest {

    Context appContext;
    BitmapConverter converter;

    @Before
    public void useAppContext() {
        // Context of the app under test.
        this.appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        this.converter = new BitmapConverter(160, 120, "RGB_565");
    }

    @Test
    public void testImageConversion() {

        byte[] data = null;

        try {
            InputStream inputStream = this.appContext.getResources().openRawResource(R.raw.prova160x120bin);
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap image = this.converter.getBitmapImage(data);
    }

}
