package uniud.iot.lab.ui;

import java.util.Iterator;
import java.util.List;

/**
 * A manager for a single column
 */
public class GraphicalDistancesColumn {

    private List<GraphicalDistanceColumnCell> cells;
    private Float minDistance, maxDistance;

    private static final int N_CELLS = 6;

    /**
     *
     * @param cells all the cells, ordered from the "closest" to the "most far" from the vehicle
     *
     * @param minDistance the minimum distance I will get
     * @param maxDistance the maximum distance I will get
     */
    public GraphicalDistancesColumn(
            List<GraphicalDistanceColumnCell> cells,
            Float minDistance, Float maxDistance) {

        if (this.cells.size() != N_CELLS) {
            // todo: raise something
        }
        this.cells = cells;

        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    /**
     * Render a single distance in the column cells
     *
     * @param distance
     */
    public void renderDistance(Float distance) {

        // todo: check if this algorithm is okay
        int howManyCellsSetOff = Math.round (
                (distance - minDistance)/(maxDistance-minDistance) * N_CELLS
        );

        int i = 0;
        Iterator<GraphicalDistanceColumnCell> iterator = cells.iterator();

        while (iterator.hasNext()) {

            // get the single cell
            GraphicalDistanceColumnCell cell = iterator.next();

            // decide if I have to turn it on or off
            if(i<howManyCellsSetOff) {
                cell.setOff();
            } else {
                cell.setOn();
            }

            i++;
        }
    }

    /**
     * Set an error in the column cells.
     */
    public void setError() {

        Iterator<GraphicalDistanceColumnCell> iterator = cells.iterator();

        while (iterator.hasNext()) {

            // get the single cell
            GraphicalDistanceColumnCell cell = iterator.next();

            // set error in every cell
            cell.setError();
        }

    }
}
