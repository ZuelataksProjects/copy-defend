import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;


public class MainGame extends JPanel implements MouseListener {
	boolean MainGameRun = true;
	int Score = 0;
	private Image Image;
	private String x;
	int imageX;
	int imageY;
	private boolean UpMove = false;
	private boolean DownMove = false;
	private JLabel counter = new JLabel();
	private JLabel picture = new JLabel(new ImageIcon("Archer.png"));	
	private Game game;
	public MainGame(Game game){
		run();

	}

	public void run(){

		//addMouseListener(this);
		
	
		/*picture.setBounds(886,360,200,200);
		picture.setFont(picture.getFont().deriveFont(18f));
		picture.setVisible(true);
		picture.setEnabled(true);
		this.add(picture);
		update(getGraphics());
		System.out.println("In Run3");
		*/
	    ImageIcon id = new ImageIcon("Archer.png");
        Image = id.getImage();
        imageX = 886;
		imageY = 360;
		System.out.println("Trig2");
		setVisible(true);
		setEnabled(true);
		update(getGraphics());
		repaint();
		System.out.println("In Run");

	}
	/**
	 * MAKE A JLABEL FOR THE CHARACTER MODELS
	 */
	public void paintComponent(Graphics g)
	    {
		 System.out.println("Trig");
	        // Calling the superclass method will essentially clear the panel
	        // so that you can now put what you want.
	        super.paintComponent(g);
	        g.drawImage(Image, imageX, imageY, null);

	    }
	 public void actionPerformed(ActionEvent e)
	    {
	            boolean collision = false;

	            // Determine the new x and y coordinates for the bouncy ball
	            //ballX += ballXVelocity;
	           // ballY += ballYVelocity;

	            // Look for collisions with the wall. Make sure to include the width of the
	            // ball when dealing with the right edge.
	           // if ((ballX >= (GameMain.FRAME_WIDTH - BALL_WIDTH)) || (ballX <= 0))
	           // {
	                // Flip the velocity to make the ball go in the opposite direction.
	           //     ballXVelocity = -ballXVelocity;
	            //    collision = true;
	           // }
	            // Make sure to include the height of the ball and of the title bar at the top of the frame when
	            // comparing to the lower edge.
	            //if ((ballY >= (GameMain.FRAME_HEIGHT - GameMain.TITLE_HEIGHT - BALL_HEIGHT)) || (ballY <= 0))
	            //{
	                //ballYVelocity = -ballYVelocity;
	                collision = true;
	           // }

	            // Play the sound effect of the ball hitting the wall if there was a collision.
	            if (collision)
	            {
	                //playSound();
	            }

	            // Move the rocket left if the left arrow is being pressed
	            if (DownMove)
	            {
	        	    ImageIcon id = new ImageIcon("Archer.png");
	                Image = id.getImage();
	                imageX = 886;
	        		imageY = 360;
	        		repaint();
	                imageY-=5;
	            }

	            // Move the rocket right if the right arrow is being pressed
	            if (UpMove)
	            {
	        	    ImageIcon id = new ImageIcon("Archer.png");
	                Image = id.getImage();
	                imageX = 886;
	        		imageY = 360;
	        		repaint();
	                imageY+=5;
	            }

	            // This will cause our paintComponent method to be called.
	            repaint();

	    }
	public JLabel getJLabel(){
		return counter;
	} 
	public void setLabelText(int number){
		this.Score = number;
		counter.setText(Integer.toString(number));
		
	}
	public void mousePressed(MouseEvent e) {
	setLabelText(10);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setLabelText(10);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setLabelText(10);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setLabelText(10);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setLabelText(10);
	}
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Check if the left or right arrows were pressed so that we
        // will start moving the rocket
        if (key == KeyEvent.VK_UP) {
            UpMove = true;
        }
        else if (key == KeyEvent.VK_DOWN) {
            DownMove = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key =  e.getKeyCode();

        // Check if the left or right arrows were released so that we
        // will stop moving the rocket
        if (key == KeyEvent.VK_UP) {
            UpMove = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            DownMove = false;
        }
    }
}