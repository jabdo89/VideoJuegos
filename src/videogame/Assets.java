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
    public static BufferedImage helperImg;
    public static BufferedImage playerUp[]; //Array of images for player moving up
    public static BufferedImage playerLeft[]; //Array of images for player moving left
    public static BufferedImage playerDown[]; //Array of images for player moving down
    public static BufferedImage playerRight[];//Array of images for player moving right
    public static BufferedImage enemyOne[];
    public static BufferedImage helperOne[];//Array of images for enemy moving
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
        
        //Create a new SpreadSheet for the Player Animation
        sprites = ImageLoader.loadImage("/images/main1.png");
        SpreadSheet spritesheet = new SpreadSheet(sprites);
        
        // Indicate that there are 4 images per animation
        playerUp = new BufferedImage[4];
        playerLeft = new BufferedImage[4];
        playerDown = new BufferedImage[4];
        playerRight = new BufferedImage[4];
        playerStill = new BufferedImage[4];
        
        // A for loop to change to the 4 diferent images per animation
        for (int i =0; i < 4; i++){
            playerUp[i] = spritesheet.crop(i * 65, 205, 50, 50);
            playerLeft[i] = spritesheet.crop(i * 65, 75, 50, 50);
            playerDown[i] = spritesheet.crop(i * 65, 11, 50, 50);
            playerRight[i] = spritesheet.crop(i * 65, 140, 50, 50);
            playerStill[i] = spritesheet.crop(0, i * 65, 50, 60);
        }
        
        // Create a SpreadSheet for the enemy animation
        sprites = ImageLoader.loadImage("/images/main3.png");
        SpreadSheet spritesheetEnemy = new SpreadSheet(sprites);
        
        // Indicate that there are 4 images per animation
        enemyOne = new BufferedImage[4];
        
        // A for loop to change to the 4 diferent images per animation
        for (int i =0; i < 4; i++){
            enemyOne[i] = spritesheetEnemy.crop(i * 50, 100, 50, 50);
        }
        
                // Create a SpreadSheet for the enemy animation
        helperImg = ImageLoader.loadImage("/images/main4.png");
        SpreadSheet spritesheetHelper = new SpreadSheet(helperImg);
        
        // Indicate that there are 4 images per animation
        helperOne = new BufferedImage[6];
        
        // A for loop to change to the 4 diferent images per animation
        for (int i =0; i < 6; i++){
            helperOne[i] = spritesheetHelper.crop(i*120, 600, 137, 137);
        }
        
        enemy = ImageLoader.loadImage("/images/luigi.png");
        helper = ImageLoader.loadImage("/images/mushroom.png");
        gameOver = ImageLoader.loadImage("/images/GameOver.jpg");
        bump = new SoundClip("/sounds/bump.wav");
        stomp = new SoundClip("/sounds/stomp.wav");
    }

}
