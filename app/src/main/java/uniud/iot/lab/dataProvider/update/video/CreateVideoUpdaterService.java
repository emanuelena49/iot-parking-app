package uniud.iot.lab.dataProvider.update.video;

import android.content.Context;

import java.net.URI;

import uniud.iot.lab.dataProvider.SensorsDataProvider;
import uniud.iot.lab.dataProvider.VideoProvider;
import uniud.iot.lab.dataProvider.update.CreateUpdaterService;
import uniud.iot.lab.dataProvider.update.Updater;

public class CreateVideoUpdaterService implements CreateUpdaterService {

    private Context context;

    public CreateVideoUpdaterService(Context context) {
        this.context = context;
    }

    @Override
    public Updater createUpdater(SensorsDataProvider dataProvider) {

        URI uri = URI.create("ws://192.168.1.135:5000/");

        VideoFluxListener fluxListener =
                new FakeVideoFluxListener((VideoProvider) dataProvider, context, uri);

        return new VideoUpdater(fluxListener);
    }
}
