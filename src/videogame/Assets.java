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
    public static BufferedImage gameOver;
    public static BufferedImage player;// to store the gameOver image
    public static BufferedImage sprites;
    public static BufferedImage playerUp[];
    public static BufferedImage playerLeft[];
    public static BufferedImage playerDown[];
    public static BufferedImage playerRight[];
    public static BufferedImage playerStill[];// to store the player image
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
        
        sprites = ImageLoader.loadImage("/images/main1.png");
        SpreadSheet spritesheet = new SpreadSheet(sprites);
        playerUp = new BufferedImage[4];
        playerLeft = new BufferedImage[4];
        playerDown = new BufferedImage[4];
        playerRight = new BufferedImage[4];
        playerStill = new BufferedImage[4];
        
        for (int i =0; i < 4; i++){
            playerUp[i] = spritesheet.crop(i * 65, 205, 50, 50);
            playerLeft[i] = spritesheet.crop(i * 65, 75, 50, 50);
            playerDown[i] = spritesheet.crop(i * 65, 11, 50, 50);
            playerRight[i] = spritesheet.crop(i * 65, 140, 50, 50);
            playerStill[i] = spritesheet.crop(0, i * 65, 50, 60);
        }
        
        
        enemy = ImageLoader.loadImage("/images/luigi.png");
        helper = ImageLoader.loadImage("/images/mushroom.png");
        gameOver = ImageLoader.loadImage("/images/GameOver.jpg");
        bump = new SoundClip("/sounds/bump.wav");
        stomp = new SoundClip("/sounds/stomp.wav");
    }

}
