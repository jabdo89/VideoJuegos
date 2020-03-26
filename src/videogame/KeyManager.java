/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author GenaroSañudo
 */
public class KeyManager implements KeyListener {
    
    public boolean up;      // flag to move up the player
    public boolean right;    // flag to move right the player
    public boolean left;    // flag to move left the player
    public boolean down;   // flag to move down the player
    public boolean p; //flag to pause
    public boolean load; //flag to load
    public boolean save; //flag to save
    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
         if (e.getKeyCode() == KeyEvent.VK_P){
            p = !p;
            }
         
         if (e.getKeyCode() == KeyEvent.VK_C){
            load = true;
            }
         
         if (e.getKeyCode() == KeyEvent.VK_G){
            save = true;
            }
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        right = keys[KeyEvent.VK_RIGHT];
        left = keys[KeyEvent.VK_LEFT];
        down = keys[KeyEvent.VK_DOWN];
    }
}
