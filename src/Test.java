import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Image;

// Bouncy ball is a JPanel and implements ActionListener for timer events
public class Test extends JPanel implements ActionListener, KeyListener
{
    // This is needed to get rid of the JPanel warning
    private static final long serialVersionUID = 1L;

    // Here are some constants used in the drawing and movement
    // of the game objects.
    private final static int BALL_WIDTH = 10;
    private final static int BALL_HEIGHT = 10;
    private final static int BALL_VELOCITY = 15;
    private final static int ROCKET_WIDTH = 58;
    private final static int ROCKET_HEIGHT = 59;

    // This is for the WAV file that plays the bounce sound effect
    private File bouceSoundFile;

    // Declare a player for the background music
    private SimpleAudioPlayer sap;

    // Need a timer to do animation
    private Timer tm;

    // These track the x and y coordinates of our bouncy ball
    private int ballX = 0;
    private int ballY = 0;

    // These track the x and y coordinates of our rocket ship
    private int rocketX = (GameMain.FRAME_WIDTH - ROCKET_WIDTH) / 2;
    private int rocketY = (GameMain.FRAME_HEIGHT - ROCKET_HEIGHT) / 2;

    // Keep track of which key is pressed
    private boolean leftMove = false;
    private boolean rightMove = false;

    // Track the x and y velocity of our bouncy ball
    private int ballXVelocity = BALL_VELOCITY;
    private int ballYVelocity = BALL_VELOCITY;

    // Our rocketship is a PNG image taken from the internet.
    // It is stored as an Image.
    private Image rocket;

    private boolean active;

    public void playGame()
    {
        active = true;

        // Create the Image from the file.
        ImageIcon id = new ImageIcon("rocket.png");
        rocket = id.getImage();

        // Open the sound effect file.
        bouceSoundFile = new File("paddleHitSound.wav");

        // These things need to be done as part of dealing with 
        // keyboard input. 'Focus' means that your application is
        // receiving the input and not some other application.
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocus();

        // Start the background music
        sap = new SimpleAudioPlayer("classical1.wav");

        // Specify 33 milliseconds to get approx. 30fps
        tm = new Timer(33, this);
        tm.start();
    }

    public void stopGame()
    {
        active = false;
        //tm.stop();
    }

    // This is the method that is called when the JPanel repaint method is called.
    public void paintComponent(Graphics g)
    {
        // Calling the superclass method will essentially clear the panel
        // so that you can now put what you want.
        super.paintComponent(g);

        // This places the rocket image on the screen at the desired
        // position.
        g.drawImage(rocket, rocketX, rocketY, this);

        // Need to cast the graphics object to a Graphics2D object
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for the objects that we want to draw
        g2d.setColor(Color.gray);

        // Draw a square on the screen at the desired location
        g2d.fillRect(ballX, ballY, BALL_WIDTH, BALL_HEIGHT);
    }

    private void playSound()
    {
        // It seems like there should be a better way to do this. However,
        // when I try to make these things as member variables and preload the 
        // clip there is an annoying popping sound when the clip is reset to the 
        // beginning.
        Clip bounceSound;
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(bouceSoundFile);
            bounceSound = AudioSystem.getClip();
            bounceSound.open(audioInputStream);
            bounceSound.start();
        } catch(Exception ex) {
            System.out.println("Error with bounce sound initialization!!!");
            ex.printStackTrace();
        }
    }

    // This is required for the ActionListener interface. This
    // method is called each time the Timer expires.
    // This is where you make all of the calculations for where
    // your game objects move.
    public void actionPerformed(ActionEvent e)
    {
        if (! active)
        {
            sap.stopMusic();
            tm.stop();
        }
        else
        {
            boolean collision = false;

            // Determine the new x and y coordinates for the bouncy ball
            ballX += ballXVelocity;
            ballY += ballYVelocity;

            // Look for collisions with the wall. Make sure to include the width of the
            // ball when dealing with the right edge.
            if ((ballX >= (GameMain.FRAME_WIDTH - BALL_WIDTH)) || (ballX <= 0))
            {
                // Flip the velocity to make the ball go in the opposite direction.
                ballXVelocity = -ballXVelocity;
                collision = true;
            }
            // Make sure to include the height of the ball and of the title bar at the top of the frame when
            // comparing to the lower edge.
            if ((ballY >= (GameMain.FRAME_HEIGHT - GameMain.TITLE_HEIGHT - BALL_HEIGHT)) || (ballY <= 0))
            {
                ballYVelocity = -ballYVelocity;
                collision = true;
            }

            // Play the sound effect of the ball hitting the wall if there was a collision.
            if (collision)
            {
                playSound();
            }

            // Move the rocket left if the left arrow is being pressed
            if (leftMove)
            {
                rocketX-=5;
            }

            // Move the rocket right if the right arrow is being pressed
            if (rightMove)
            {
                rocketX+=5;
            }

            // This will cause our paintComponent method to be called.
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Check if the left or right arrows were pressed so that we
        // will start moving the rocket
        if (key == KeyEvent.VK_LEFT) {
            leftMove = true;
        }
        else if (key == KeyEvent.VK_RIGHT) {
            rightMove = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Check if the left or right arrows were released so that we
        // will stop moving the rocket
        if (key == KeyEvent.VK_LEFT) {
            leftMove = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightMove = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // We don't need this because we use 'pressed' and 'released'
        // to track the keys.
    }
}