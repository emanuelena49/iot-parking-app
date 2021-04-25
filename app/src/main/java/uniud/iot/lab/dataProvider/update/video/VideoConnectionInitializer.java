package uniud.iot.lab.dataProvider.update.video;

/**
 * A tool to init. a connection for video an to build all necessary classes.
 */
public class VideoConnectionInitializer {

    public VideoFluxListener initConnection() {

        // todo: init all web socket stuff and parse image params
        // ...
        int frameWidth=0, frameHeight=0;
        String frameFormat="";

        // (given params) init the bitmap converter
        BitmapConverter converter = new BitmapConverter(frameWidth, frameHeight, frameFormat);

        // create and return a VideoFluxListener
        // todo: pass even webs ocket
        return new VideoFluxListener(converter);
    }
}
