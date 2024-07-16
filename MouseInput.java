import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    //Mouse position
    private int mouseX;
    private int mouseY;

    //Mouse input
    private boolean leftClicked;

    public MouseInput(Canvas canvas) {
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    /*
    * @return An array of the mouse's position as an ordered
    * coordinate pair
    */
    public int[] getMousePos(){
        return new int[]{mouseX, mouseY};
    }

    /*
    * @return Whether or not the mouse was clicked
    */
    public boolean isLeftClicked() {
      return leftClicked;
    }

    /*
    * Updates the mouse's position to the cursor's position
    */
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    /*
    * Detects left clicks
    */
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = true;
        }
    }

    /*
    * Detects when left click is released
    */
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = false;
        }
    }

}

