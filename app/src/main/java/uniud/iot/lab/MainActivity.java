package uniud.iot.lab;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniud.iot.lab.dataProvider.DistancesProvider;
import uniud.iot.lab.dataProvider.update.CreateDistanceUpdaterService;
import uniud.iot.lab.dataProvider.update.updater.Updater;
import uniud.iot.lab.dataProvider.update.updater.exceptions.UpdaterAlreadyRunningException;
import uniud.iot.lab.ui.GraphicalDistanceColumnCell;
import uniud.iot.lab.ui.GraphicalDistancesColumn;
import uniud.iot.lab.ui.GraphicalDistancesDisplay;
import uniud.iot.lab.ui.NumericalDistancesDisplay;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create my distance provider
        DistancesProvider distancesProvider = new DistancesProvider();

        // create an updater for distances
        Updater distancesUpdater =
                new CreateDistanceUpdaterService().createUpdater(distancesProvider);

        // params of the graphical distance display
        int nCells = 6;
        Float minDistance = 0f;
        Float maxDistance = 400f;

        GraphicalDistancesDisplay graphicalDistancesDisplay =
                buildGraphicalDistanceDisplay(distancesProvider, nCells, minDistance, maxDistance);

        NumericalDistancesDisplay numericalDistancesDisplay =
                buildNumericalDistanceDisplay(distancesProvider);

        // init sample distances
        Map<String, Float> distances = new HashMap<String, Float>();
        distances.put("left", 400f);
        distances.put("center", 350f);
        distances.put("right", 50f);

        distancesProvider.setDistances(distances);

        try {
            distancesUpdater.startUpdated();
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
}