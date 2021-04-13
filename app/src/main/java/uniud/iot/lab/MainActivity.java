package uniud.iot.lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniud.iot.lab.dataProvider.DistancesProvider;
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
        distances.put("left", 1f);
        distances.put("center", 200f);
        distances.put("right", 399f);

        distancesProvider.setDistances(distances);
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


        cols.put("left", buildGraphicalDistanceCol(
                findViewById(R.id.grahicalDistanceDisplayerLeft),
                nCells, minDistance, maxDistance));

        cols.put("center", buildGraphicalDistanceCol(
                findViewById(R.id.grahicalDistanceDisplayerCenter),
                nCells, minDistance, maxDistance));

        cols.put("right", buildGraphicalDistanceCol(
                findViewById(R.id.grahicalDistanceDisplayerRight),
                nCells, minDistance, maxDistance));

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
            Float minDistance, Float maxDistance) {

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        param.setMargins(2, 2, 2, 2);


        // list of cell I have to return
        List<GraphicalDistanceColumnCell> cells = new ArrayList<GraphicalDistanceColumnCell>();

        for(int i=0; i<nCells; i++) {
            TextView row = new TextView(this);

            // set style
            row.setLayoutParams(param);

            // add row to UI
            column.addView(row);

            // create the abstract class which will control it and add it to the cell list
            cells.add(new GraphicalDistanceColumnCell(row, Color.RED, Color.LTGRAY, Color.RED));
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