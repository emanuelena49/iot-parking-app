package uniud.iot.lab.dataProvider.update.video;

import android.content.Context;

import java.util.Observable;

import uniud.iot.lab.dataProvider.VideoProvider;
import uniud.iot.lab.dataProvider.update.CreateUpdaterService;
import uniud.iot.lab.dataProvider.update.Updater;

public class CreateVideoUpdaterService implements CreateUpdaterService {

    private Context context;

    public CreateVideoUpdaterService(Context context) {
        this.context = context;
    }

    @Override
    public Updater createUpdater(Observable dataProvider) {

        VideoFluxListener fluxListener =
                new FakeVideoFluxListener((VideoProvider) dataProvider, context);

        return new VideoUpdater(fluxListener);
    }
}
