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
public class Enemy extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    /**
     * Set the initial values to create the enemy
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     * @param direction <b>direction</b>  of the object
     * @param width <b>width<b> value of the object
     * @param height <b>height<b> value of the object
     * @param game <b>game<b> value of the game
     */
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
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

    /**
     * Set height
     * @param height to modify
     */
    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public void tick() {
        // moving enemy along the X coordinate
        this.setX(this.getX() - (int) (Math.random() * 3 + 3));
        
        // reset x position and y position if it goes out of the borders
        if (getX() <= -30) {
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
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
