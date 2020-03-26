/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author GenaroSañudo
 */
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    private Anmation animationUp;
    private Anmation animationDown;
    private Anmation animationRight;
    private Anmation animationLeft;
    private Anmation animationStill;
    private Anmation currAni;
    
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        
        this.animationUp = new Anmation(Assets.playerUp,100);
        this.animationDown = new Anmation(Assets.playerDown,100);
        this.animationRight = new Anmation(Assets.playerRight,100);
        this.animationLeft = new Anmation(Assets.playerLeft,100);
        this.animationStill = new Anmation(Assets.playerStill,100);
        this.currAni = this.animationStill;
    }

    /**
     * Get value of direction
     * @return direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Get value of width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get value of height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set direction
     * @param direction to modify
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Set width
     * @param width to modify
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setCurr(Anmation curr) {
        this.currAni = curr;
    }
    /**
     * Set height
     * @param height to modify
     */
    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public void tick() {
        this.currAni.tick();
        // moving player depending on flags
        if (game.getKeyManager().up) {
            setCurr(animationUp);
           setY(getY() - 1);
        }
        else if (game.getKeyManager().right) {
            setCurr(animationRight);
           setX(getX() + 1);
        }
        else if (game.getKeyManager().down) {
            setCurr(animationDown);
           setY(getY() + 1);
        }
        else if (game.getKeyManager().left) {
            setCurr(animationLeft);
           setX(getX() - 1);
        }else{
           setCurr(animationStill);
        }
        // reset x position and y position if it goes out of the border
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currAni.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
