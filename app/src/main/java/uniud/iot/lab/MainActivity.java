package uniud.iot.lab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniud.iot.lab.dataProvider.update.video.BitmapConverter;
import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.SensorsStatusProvider;
import uniud.iot.lab.dataProvider.VideoProvider;
import uniud.iot.lab.dataProvider.update.CreateDistanceUpdaterService;
import uniud.iot.lab.dataProvider.update.Updater;
import uniud.iot.lab.dataProvider.update.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.ui.DistancesAudioAlert;
import uniud.iot.lab.ui.GraphicalDistanceColumnCell;
import uniud.iot.lab.ui.GraphicalDistancesColumn;
import uniud.iot.lab.ui.GraphicalDistancesDisplay;
import uniud.iot.lab.ui.NoConnectionAlertDisplay;
import uniud.iot.lab.ui.NumericalDistancesDisplay;
import uniud.iot.lab.ui.StatusDisplay;
import uniud.iot.lab.ui.StatusLight;
import uniud.iot.lab.ui.VideoDisplay;
import uniud.iot.lab.utils.Activable;

public class MainActivity extends AppCompatActivity {

    // params of the graphical distance display
    int nCells = 6;
    Float minDistance = 0f;
    Float maxDistance = 400f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create my distance provider
        DistancesProvider distancesProvider = new DistancesProvider();

        // create an updater for distances
        Updater distancesUpdater =
                new CreateDistanceUpdaterService().createUpdater(distancesProvider);

        GraphicalDistancesDisplay graphicalDistancesDisplay =
                buildGraphicalDistanceDisplay(distancesProvider, nCells, minDistance, maxDistance);

        NumericalDistancesDisplay numericalDistancesDisplay =
                buildNumericalDistanceDisplay(distancesProvider);

        // todo: real audio alert
        DistancesAudioAlert audioAlert = new DistancesAudioAlert(distancesProvider);

        // init video provider
        VideoProvider videoProvider = new VideoProvider();

        // init video display
        VideoDisplay videoDisplay = initVideoDisplay(videoProvider);

        // init status provider
        SensorsStatusProvider statusProvider = new SensorsStatusProvider(distancesProvider, videoProvider);

        // init status display tools
        StatusDisplay statusIcon = initStatusLight(statusProvider);
        StatusDisplay noConnectionAlert = initNoConnectionAlert(statusProvider);

        // init the aside switches
        manageAsideSwitches(numericalDistancesDisplay, audioAlert);

        // todo: remove
        // test display of raw bytearray image
        byte[] data = null;
        try {
            InputStream inputStream = getApplicationContext().getResources().openRawResource(R.raw.prova160x120bin);
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap image = new BitmapConverter(160, 120, "RGB_565").getBitmapImage(data);
        videoDisplay.displayFrame(image);

        // run the distance updater
        try {
            distancesUpdater.startUpdate();
        } catch (UpdaterAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    /**
     * Init the graphical distance display manager
     *
     * @param distancesProvider
     * @param nCells
     * @param minDistance
     * @param maxDistance
     * @return
     */
    private GraphicalDistancesDisplay buildGraphicalDistanceDisplay(
            DistancesProvider distancesProvider, int nCells,
            Float minDistance, Float maxDistance) {

        // build the 3 cols
        Map<String, GraphicalDistancesColumn> cols =
                new HashMap<String, GraphicalDistancesColumn>();

        // create colors
        List<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#B11414"));
        colors.add(Color.parseColor("#F3AE47"));
        colors.add(Color.parseColor("#53D007"));

        int colorOff = Color.LTGRAY;
        int colorError = Color.parseColor("#B11414");

        cols.put("left", buildGraphicalDistanceCol(
                findViewById(R.id.grahicalDistanceDisplayerLeft),
                nCells, minDistance, maxDistance, Gravity.RIGHT,
                colors, colorOff, colorError
        ));

        cols.put("center", buildGraphicalDistanceCol(
                findViewById(R.id.grahicalDistanceDisplayerCenter),
                nCells, minDistance, maxDistance, Gravity.CENTER,
                colors, colorOff, colorError
        ));

        cols.put("right", buildGraphicalDistanceCol(
                findViewById(R.id.grahicalDistanceDisplayerRight),
                nCells, minDistance, maxDistance, Gravity.LEFT,
                colors, colorOff, colorError
        ));

        return new GraphicalDistancesDisplay(cols, distancesProvider);
    }

    /**
     * Init a single column of the Graphical
     * @param column
     * @param nCells
     * @param minDistance
     * @param maxDistance
     * @return
     */
    private GraphicalDistancesColumn buildGraphicalDistanceCol(
            LinearLayout column, int nCells,
            Float minDistance, Float maxDistance,
            int gravity, List<Integer> colors,
            int colorOff, int colorError) {

        // list of cell I have to return
        List<GraphicalDistanceColumnCell> cells = new ArrayList<GraphicalDistanceColumnCell>();

        for(int i=0; i<nCells; i++) {
            TextView row = new TextView(this);

            // create element style
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );

            // manage margin (and "pyramid effect")
            int topBottomMargin = 15;

            int rightMargin = 5;
            int leftMargin = 5;
            int k = 30;

            switch (gravity){
                case Gravity.LEFT:
                    rightMargin += k*(nCells-i-1);
                    break;
                case Gravity.RIGHT:
                    leftMargin += k*(nCells-i-1);
                    break;
                default:
                    rightMargin += 15;
                    leftMargin += 15;
                    break;
            }

            param.setMargins(leftMargin, topBottomMargin, rightMargin, topBottomMargin);

            // set style
            row.setLayoutParams(param);

            // add row to UI
            column.addView(row);

            // manage color
            int colorIndex = i*colors.size()/nCells;
            int color = colors.get(colorIndex);

            // create the abstract class which will control it and add it to the cell list
            cells.add(new GraphicalDistanceColumnCell(row, color, colorOff, colorError));
        }

        return new GraphicalDistancesColumn(cells, minDistance, maxDistance);
    }

    /**
     * Init the distance label abstract manager
     *
     * @param distancesProvider
     * @return
     */
    private NumericalDistancesDisplay buildNumericalDistanceDisplay(
            DistancesProvider distancesProvider) {

        // get the 3 labels
        Map<String, TextView> labels =
                new HashMap<String, TextView>();

        labels.put("left", findViewById(R.id.distanceLabelLeft));
        labels.put("center", findViewById(R.id.distanceLabelCenter));
        labels.put("right", findViewById(R.id.distanceLabelRight));

        // build the object and return
        return new NumericalDistancesDisplay(labels, distancesProvider);
    }

    /**
     * Manage numerical distance display and audio alert activating and de-activating then
     * according to aside switches.
     *
     * @param numericalDistanceDisplay
     * @param audioAlert
     */
    private void manageAsideSwitches(Activable numericalDistanceDisplay, Activable audioAlert) {

        // manage numerical distance display
        Switch numericalDistanceSwitch = (Switch) findViewById(R.id.controlNumericalDistance);
        numericalDistanceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    numericalDistanceDisplay.activate();
                } else {
                    numericalDistanceDisplay.deactivate();
                }
            }
        });

        // manage audio alert
        Switch audioAlertSwitch = (Switch) findViewById(R.id.controlAudioAlert);
        audioAlertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    audioAlert.activate();
                } else {
                    audioAlert.deactivate();
                }
            }
        });
    }

    private VideoDisplay initVideoDisplay(VideoProvider provider) {

        ImageView videoDisplayImageView = (ImageView) findViewById(R.id.videoDisplay);

        Bitmap errorImage = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.camera_not_available
        );

        return new VideoDisplay(provider, videoDisplayImageView, errorImage);
    }

    private StatusDisplay initStatusLight(SensorsStatusProvider statusProvider) {

        // define colors
        int red = Color.parseColor("#B11414");
        int yellow = Color.parseColor("#F3AE47");
        int green = Color.parseColor("#53D007");

        // get text view used as light
        TextView statusIcon = (TextView) findViewById(R.id.statusIcon);

        return new StatusLight(statusProvider, statusIcon, green, yellow, red);
    }

    private StatusDisplay initNoConnectionAlert(SensorsStatusProvider statusProvider) {

        // prepare alert messages
        String message = "Something is not working, check if you are connected to sensors network",
                title = "Something is not working";

        // init alert manager
        return new NoConnectionAlertDisplay(
                statusProvider, this, getApplicationContext(),
                title, message);
    }
}