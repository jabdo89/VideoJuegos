/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author GenaroSañudo
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private LinkedList<Enemy> lista; // to store a random amount of enemies
    private LinkedList<Helper> listaHelp; // to store a random amount of helpers
    private int score; // To keep track of the game score
    private int lives; // To keep track of the lvies;
    private int cont; // To keep track of how many enemies hit player

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.score = 0;
        this.lives = (int) (Math.random() * 3 +3);
        running = false;
        keyManager = new KeyManager();

    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the player
     *
     * @return an <code>Player<code> value with the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        lista = new LinkedList<Enemy>();
        listaHelp = new LinkedList<Helper>();

        player = new Player((getWidth() / 2) - 50, (getHeight() / 2) - 50, 1, 100, 100,
                this);
        
        // Gets a random number between 8 and 10
        int azar = (int) (Math.random() * 3 + 8);
        
        // adds a random amount of enemies to the list
        for (int i = 0; i < azar; i++) {
            Enemy enemy = new Enemy((int) (Math.random() * getWidth()
                    + getWidth() + 100),
                    (((int) (Math.random() * getHeight()))),
                    1, 100, 100, this);
            lista.add(enemy);
        }

        // Gets a random number between 10 and 15
        azar = (int) (Math.random() * 6 + 10);

        // adds a random amount of helpers to the list
        for (int i = 0; i < azar; i++) {
            Helper helper = new Helper((int) (Math.random() * getWidth() + 100)
                    * (-1),
                    (((int) (Math.random() * getHeight()))),
                    1, 75, 75, this);
            listaHelp.add(helper);
        }

        display.getJframe().addKeyListener(keyManager);
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        // Checks if player still has lives
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }

        }
        stop();
    }

    /**
     * To get the KeyManager
     *
     * @return an <code>KeyManager<code> value with the keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    // Plays sound if enemy hits player
    public void enemyBeep() {
        Assets.bump.play();
    }

    // Plays sound if helper hits player
    public void helperBeeb() {
        Assets.stomp.play();
    }
    
    public void save(){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("game"));
            writer.println("" + lives + "/" + score);
            writer.println("" + player.getX() + "/" + player.getY());
            for (Enemy enemy : lista){
                writer.println("" + enemy.getX() + "/" + enemy.getY());
            }
            
            for (Helper helper : listaHelp){
                writer.println("" + helper.getX() + "/" + helper.getY());
            }
            
            writer.close();
        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }
    }
    
    public void load(){
        try {
            FileReader file = new FileReader("game");
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            lives = Integer.parseInt(datos[0]);
            score = Integer.parseInt(datos[1]);
            line = reader.readLine();
            datos = line.split("/");
            int x = Integer.parseInt(datos[0]);
            int y = Integer.parseInt(datos[1]);
            player.setX(x);
            player.setY(y);
            System.out.println(Integer.parseInt(datos[0]));
            for (Enemy enemy : lista){
                line = reader.readLine();
                datos = line.split("/");
                x = Integer.parseInt(datos[0]);
                y = Integer.parseInt(datos[1]);
                enemy.setX(x);
                enemy.setY(y);
            }
            
            for (Helper helper : listaHelp){
                line = reader.readLine();
                datos = line.split("/");
                helper.setX(Integer.parseInt(datos[0]));
                helper.setY(Integer.parseInt(datos[1]));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

    private void tick() {
         // ticks keyMangaer
         keyManager.tick();
         
        if (keyManager.load){
            keyManager.load = false;
            load();
        }
        
        if (keyManager.save){
            keyManager.save = false;
            save();
                    
        }
        if (lives > 0 && !keyManager.p) {

            // avancing player with colision
            player.tick();

            // for loop that ticks all enemies
            for (Enemy enemy : lista) {
                enemy.tick();
                // reseting enemies that colision with the player
                if ((player.collision(enemy))) {
                    
                    // resets the enemies X coordinate
                    enemy.setX((int) (Math.random() * getWidth()
                            + getWidth() + 100));
                    
                    // resets the enemies Y coordinate
                    enemy.setY((int) (Math.random() * getHeight()));
                    
                    // adds 1 to cont 
                    cont++;

                    // plays bump sound
                    enemyBeep();
                }

                // resets enemies that get to the edge of the screen
                if ((enemy.getX() <= -30)) {
                    // resets the X coordinate of the enemy
                    enemy.setX((int) (Math.random() * getWidth()
                            + getWidth() + 100));
                    // resets the Y coordinate of the enemy
                    enemy.setY((int) (Math.random() * getHeight()));
                }
                
                // If player gets hit 5 times lower its lives by 1
                if (cont == 5) {
                    lives--;
                    cont = 0;
                }
            }
            
            // for loop that ticks all halpers
            for (Helper helper : listaHelp) {
                // ticks helpers
                helper.tick();
                // reseting helpers with colision
                if ((player.collision(helper))) {
                    // resets the helper's X coordinate
                    helper.setX((int) (Math.random() * getWidth() + 100) * (-1));
                    // resets the helper's Y coordinate
                    helper.setY((int) (Math.random() * getHeight() - 100));

                    // plays stomp sound
                    helperBeeb();
                    
                    // adds 5 to the score
                    score += 5;
                }

                // resets helpers that get to the edge of the screen
                if (helper.getX() + 60 >= this.getWidth()) {
                    // resets the helper's X coordinate
                    helper.setX((int) (Math.random() * getWidth() + 100) * (-1));
                    // resets the helper's Y coordinate
                    helper.setY((int) (Math.random() * getHeight() - 100));
                }
            }
        }
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            // if the player is out of lives it renders the game over screen
            if (lives == 0) {
                g.drawImage(Assets.gameOver, 0, 0, width, height, null);
                bs.show();
                g.dispose();
            } else {
                // if not it renders everything else
                g.drawImage(Assets.background, 0, 0, width, height, null);
                player.render(g);
                // renders all enemies
                for (Enemy enemy : lista) {
                    enemy.render(g);
                }
                // renders all helpers
                for (Helper helper : listaHelp) {
                    helper.render(g);
                }
                g.setColor(Color.red);
                g.drawString("Lives: " + lives, 10, 15);
                g.drawString("Score: " + score, 90, 15);
                bs.show();
                g.dispose();
            }
        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
