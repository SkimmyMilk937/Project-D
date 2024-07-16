import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    
    //private static int key;
    private boolean f11;

    public KeyInput(Canvas canvas){
        canvas.addKeyListener(this);
    }

    public char getKeyboardChar(KeyEvent e){
        return e.getKeyChar();
    }

    public boolean isF11Clicked() {
        return f11;
    }
    
    public void resetf11(){
        f11 = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 122){
            f11 = true;
        }
        System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}


