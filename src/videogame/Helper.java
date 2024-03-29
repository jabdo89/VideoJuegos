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
public class Helper extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    private Anmation animationUp;
    
    public Helper(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        
        this.animationUp = new Anmation(Assets.helperOne,100);
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
        this.animationUp.tick();
        // moving helper in the X axis
        this.setX(this.getX() + (int) (Math.random() * 3 + 1));
        

        // reset x position and y position if it goes out of the border
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
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
        g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
