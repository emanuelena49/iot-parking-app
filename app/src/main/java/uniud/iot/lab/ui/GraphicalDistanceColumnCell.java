package uniud.iot.lab.ui;

import android.graphics.Color;
import android.view.View;

/**
 * A single cell of the distance graphical display
 */
public class GraphicalDistanceColumnCell {

    private Color colorOn, colorOff, colorError;
    private View physicalCell;

    GraphicalDistanceColumnCell(View physicalCell, Color colorOn, Color colorOff, Color colorError) {

        this.physicalCell = physicalCell;
        this.colorOn = colorOn;
        this.colorOff = colorOff;
        this.colorError = colorError;
    }

    public void setOn() {
        physicalCell.setBackgroundColor(colorOn.hashCode());
    }

    public void setOff() {
        physicalCell.setBackgroundColor(colorOff.hashCode());
    }

    public void setError() {
        physicalCell.setBackgroundColor(colorError.hashCode());
    }
}
