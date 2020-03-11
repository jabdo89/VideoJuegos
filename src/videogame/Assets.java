/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author GenaroSañudo
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage gameOver;      // to store the gameOver image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage enemy;      // to store the enemy image
    public static BufferedImage helper;      // to store the helper image
    public static SoundClip bump;      // to store the bump sound
    public static SoundClip stomp;      // to store the stomp sound

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/mario.png");
        enemy = ImageLoader.loadImage("/images/luigi.png");
        helper = ImageLoader.loadImage("/images/mushroom.png");
        gameOver = ImageLoader.loadImage("/images/GameOver.jpg");
        bump = new SoundClip("/sounds/bump.wav");
        stomp = new SoundClip("/sounds/stomp.wav");
    }

}
