package uniud.iot.lab.ui;

import android.graphics.Color;
import android.view.View;

/**
 * A single cell of the distance graphical display
 */
public class GraphicalDistanceColumnCell {

    private int colorOn, colorOff, colorError;
    private View physicalCell;

    public GraphicalDistanceColumnCell(View physicalCell, int colorOn, int colorOff, int colorError) {

        this.physicalCell = physicalCell;
        this.colorOn = colorOn;
        this.colorOff = colorOff;
        this.colorError = colorError;

        setError();
    }

    public void setOn() {
        physicalCell.setBackgroundColor(colorOn);
    }

    public void setOff() {
        physicalCell.setBackgroundColor(colorOff);
    }

    public void setError() {
        physicalCell.setBackgroundColor(colorError);
    }
}
