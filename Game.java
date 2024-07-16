import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.prefs.Preferences;

import javax.swing.JFrame;


/* figure out how to rearrange size of windows && canvas

for windows resize funtion line 151
 */
public class Game extends JFrame implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private MouseInput mouseInput;
    private KeyInput keyInput;
    private int monitorWidth, monitorHeight;

    public Preferences preferences = Preferences.userNodeForPackage(Game.class); //user Prefrences

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    //Frames for fps count
    public int frames = 0;

    public Game() {

        monitorHeight = gd.getDisplayMode().getHeight();
        monitorWidth = gd.getDisplayMode().getWidth();

        //Initialize Window Border
        setTitle("Project Duel");
        windowResize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        


        //Initialize Background (currently black)
        canvas = new Canvas();
        canvas.setSize(monitorWidth, monitorHeight);
        canvas.setBackground(Color.BLACK);
        canvas.setIgnoreRepaint(true);
        add(canvas);

        canvas.createBufferStrategy(3);
        bufferStrategy = canvas.getBufferStrategy();
        
        //Initialize Input
        keyInput = new KeyInput(canvas);
        mouseInput = new MouseInput(canvas);

    }


    
    //Initializes the game loop
    public synchronized void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Stops the game loop
    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //The game loop
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();


        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void update() {
        // Update game state

        if(keyInput.isF11Clicked()) {
            if(preferences.get("windowSetting", "full").equals("full"))
                preferences.put("windowSetting", "small");
                
            else
                preferences.put("windowSetting", "full");
            windowResize();
            keyInput.resetf11();
        }

        if(mouseInput.isLeftClicked()) {
            System.out.println("Left Click");
        }
    }

    //Rendering graphics
    private void render() {
      do {
            do {
                Graphics g = bufferStrategy.getDrawGraphics();
                try {
                    g.clearRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.WHITE);
                    //Mouse position
                    g.drawString("Mouse Position: (" + mouseInput.getMouseX() + ", " + mouseInput.getMouseY() + ")", 20, 20);
                    //FPS counter
                    g.drawString("FPS: " + frames, 20, 40);
                } finally {
                    //get rid of graphics to get new frame
                    g.dispose();
                }
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    public void windowResize(){
        if(preferences.get("windowSetting", "full").equals("full")){
            this.dispose();
            this.setVisible(false);
            this.setUndecorated(true);
            this.setResizable(false);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            preferences.put("windowSetting", "full");
            this.setVisible(true);
            
        }
        else if(preferences.get("windowSetting", "full").equals("small")){
            this.dispose();
            this.setVisible(false);
            this.setUndecorated(false);
            this.setResizable(true);
            this.setSize(800, 800);
            preferences.put("windowSetting", "small");
            this.setVisible(true);
        }
    }

    public String getWindowSetting(){
        return preferences.get("windowSetting", "full");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}