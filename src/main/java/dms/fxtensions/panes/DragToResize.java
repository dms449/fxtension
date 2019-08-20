package dms.fxtensions.panes;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Make any pane resizable with drag
 */
public class DragToResize {
    private static final int RESIZE_MARGIN = 5;

    public Pane resizable;

    public boolean dragging = false;

    private double y;

    private boolean initMinHeight;


    /**
     * Constructor
     * @param pane
     */
    public DragToResize(Pane pane) {
        resizable = pane;
    }

    /**
     * TODO
     * @param pane
     */
    public static void apply(Pane pane){

        DragToResize resizable = new DragToResize(pane);

        pane.setOnMousePressed(resizable::mousePressed);
        pane.setOnMouseReleased(resizable::mouseReleased);
        pane.setOnMouseDragged(resizable::mouseDragged);
        pane.setOnMouseMoved(resizable::mouseOver);
    }

    protected void mouseReleased(MouseEvent event) {
        dragging = false;
        resizable.setCursor(Cursor.DEFAULT);
    }

    protected void mouseOver(MouseEvent event) {
        if(isInDraggableZoneX(event) && !isInDraggableZoneY(event)) {
            resizable.setCursor(Cursor.E_RESIZE);
        } else if (!isInDraggableZoneX(event) && isInDraggableZoneY(event)){
            resizable.setCursor(Cursor.S_RESIZE);
        } else if (isInDraggableZoneY(event) && isInDraggableZoneX(event)){
            resizable.setCursor(Cursor.SE_RESIZE);
        }
        else {
            resizable.setCursor(Cursor.DEFAULT);
        }
    }

    protected boolean isInDraggableZoneX(MouseEvent event) {
        return event.getX() < RESIZE_MARGIN ||
                event.getX() > (resizable.getWidth() - RESIZE_MARGIN);
    }

    protected boolean isInDraggableZoneY(MouseEvent event) {
        return event.getY() < RESIZE_MARGIN ||
                event.getY() > (resizable.getHeight() - RESIZE_MARGIN);
    }

    protected void mouseDragged(MouseEvent event) {
        if(!dragging) {
            return;
        }

        double mousey = event.getY();

        double newHeight = resizable.getMinHeight() + (mousey - y);

        resizable.setMinHeight(newHeight);

        y = mousey;
    }

    protected void mousePressed(MouseEvent event) {

        // ignore clicks outside of the draggable margin
        if(!isInDraggableZoneX(event) && ! isInDraggableZoneY(event)) {
            return;
        }

        dragging = true;

        // make sure that the minimum height is set to the current height once,
        // setting a min height that is smaller than the current height will
        // have no effect
        if (!initMinHeight) {
            resizable.setMinHeight(resizable.getHeight());
            initMinHeight = true;
        }

        y = event.getY();
    }

}
