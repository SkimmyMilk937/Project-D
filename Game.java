
import java.awt.*;
import java.util.*;
import org.w3c.dom.Element;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.sql.rowset.spi.XmlWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



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
    private boolean onTitle = true; //Detects whether or not the player is on the title screen
    private Rectangle startButton;
    private Rectangle deckButton;
    private boolean deckBuilder;
    private CardCatalog selectedCard;
    private CardCatalog[][] decks = new CardCatalog[40][40];
    private CardCatalog[] currentDeck = new CardCatalog[40];
    private int selectedCardIndex = 0;
    Deck deck = new Deck("myDeck"); // temporary deck testing object
    public Preferences preferences = Preferences.userNodeForPackage(Game.class); //user Prefrences

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    //Frames for fps count
    public int frames = 0;

    public Game() {

        monitorHeight = gd.getDisplayMode().getHeight();
        monitorWidth = gd.getDisplayMode().getWidth();
        startButton = new Rectangle(monitorWidth / 2 - 300, monitorHeight / 2 - 75, 300, 75); //For Title Screen
        deckButton = new Rectangle(monitorWidth / 2 - 300, monitorHeight / 2 + 75, 300, 75);
        
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
        
        //detect main menu buttons
        if(onTitle && mouseInput.isLeftClicked() && mouseInput.getMouseX() > startButton.x && mouseInput.getMouseX() < startButton.x + startButton.width && mouseInput.getMouseY() > startButton.y && mouseInput.getMouseY() < startButton.y + startButton.height) {
        	System.out.println("Pressed Start");
        	onTitle = false;
        }
        if(onTitle && mouseInput.isLeftClicked() && mouseInput.getMouseX() > deckButton.x && mouseInput.getMouseX() < deckButton.x + deckButton.width && mouseInput.getMouseY() > deckButton.y && mouseInput.getMouseY() < deckButton.y + deckButton.height) {
        	System.out.println("Deck Builder");
        	onTitle = false;
        	deckBuilder = true;
        }
        
        //Deck builder menu
        if(deckBuilder) {
        	
        	//Selecting Cards
        	int currentRow = 150;
        	int currentCol = 600;
        	
        	for(CardCatalog card : CardCatalog.values()) {
        		try {
					BufferedImage image = ImageIO.read(card.image);
					
					if(mouseInput.isLeftClicked() && mouseInput.getMouseX() > currentCol && mouseInput.getMouseX() < currentCol + image.getWidth() && mouseInput.getMouseY() > currentRow && mouseInput.getMouseY() < currentRow + image.getHeight()) {
						selectedCard = card;
						System.out.println("Selected card: " + card.name());
					}
					else if(selectedCard == card && !mouseInput.isLeftClicked() && mouseInput.getMouseX() > currentCol && mouseInput.getMouseX() < currentCol + image.getWidth() && mouseInput.getMouseY() > currentRow && mouseInput.getMouseY() < currentRow + image.getHeight()) {
						currentDeck[selectedCardIndex] = card;
                        deck.addCard(card); //temporary placemnt of add method for deck contrcution
                        
						selectedCardIndex++;
						if(selectedCardIndex == 40) {
							selectedCardIndex = 0;
						}
					}
					
					currentCol += image.getWidth() + 40;
					if(currentCol >= monitorWidth - 600) {
						currentRow += image.getHeight() + 40;
						currentCol = 600;
						if(currentRow >= monitorHeight - 200) {
							break; //Add scrolling
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}

        	}
        	
        	if(!mouseInput.isLeftClicked()) {
        		selectedCard = null;
        	}
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
                    
                    //Title Screen + Main Menu
                    if(onTitle) {
                    	//Title Text
                    	g.setFont(new Font("Serif", Font.BOLD, 80));
                    	g.setColor(Color.WHITE);
                    	g.drawString("Project Duel", monitorWidth / 4 + 100, monitorHeight / 4); //Rearrange this to be in the center
                    	
                    	//Add a Start Button
                    	g.setFont(new Font("Arial", Font.BOLD, 48));
                    	g.setColor(Color.LIGHT_GRAY);
                    	g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
                    	g.setColor(Color.WHITE);
                    	g.drawString("Start Game", startButton.x + 20, startButton.y + 35);
                    	
                    	//Add a Select Deck Button
                    	g.setFont(new Font("Arial", Font.BOLD, 48));
                    	g.setColor(Color.LIGHT_GRAY);
                    	g.fillRect(deckButton.x, deckButton.y, deckButton.width, deckButton.height);
                    	g.setColor(Color.WHITE);
                    	g.drawString("Select Deck", deckButton.x + 20, deckButton.y + 35);
                    	
                    }
                    
                    if(deckBuilder) {
                    	int currentRow = 150;
                    	int currentCol = 600;
                    	g.setFont(new Font("Serif", Font.BOLD, 80));
                    	g.setColor(Color.WHITE);
                    	g.drawString("Select Cards:", 800, 100); //Rearrange this to be in the center
                    	g.drawString("Current Deck:", 0, 100);
                    	for(CardCatalog card : CardCatalog.values()) {
                    		try {
								BufferedImage image = ImageIO.read(card.image);
								
								g.drawImage(image, currentCol, currentRow, image.getWidth(), image.getHeight(), canvas);
								currentCol += image.getWidth() + 40;
								if(currentCol >= monitorWidth - 600) {
									currentRow += image.getHeight() + 40;
									currentCol = 600;
									if(currentRow >= monitorHeight - 200) {
										break; //Add scrolling
									}
								}
								
							} catch (IOException e) {
								e.printStackTrace();
							}

                    	}
                    	
                    	currentRow = 150;
                    	currentCol = 40;
                    	
                    	for(CardCatalog card : currentDeck) {
                    		try {
								if(card != null) {
									BufferedImage image = ImageIO.read(card.image);
								
									g.drawImage(image, currentCol, currentRow, image.getWidth(), image.getHeight(), canvas);
									currentCol += image.getWidth() + 40;
									if(currentCol >= 450) {
										currentRow += image.getHeight() + 40;
										currentCol = 40;
										if(currentRow >= monitorHeight - 200) {
											break; //Add scrolling
										}
									}
								}
                    			
								
							} catch (IOException e) {
								e.printStackTrace();
							}
                    	}
                    }
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
