import handleImagePath.ImageConverter;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener, KeyListener
{	// This is needed to get rid of the JPanel warning
	private static final long serialVersionUID = 1L;

	public boolean controlsFlag = false;
	public boolean gameFlag = false;
	public boolean menuFlag = false;
	public boolean GamePlaying = true;
	public boolean MusicPlaying = true;
	public boolean MainGame = false;

	public static ImageIcon id;
	public static boolean IsOnTitleScreen = false;
	public static boolean IsOnControlScreen = false;
	public static boolean IsOnStartScreen = false;

	public MainGame start;

	private JLabel counter = new JLabel();
	private String counterText;
	private int Score = 0;

	private boolean UpMove = false;
	private boolean DownMove = false;
	private boolean active;

	private Image knightImage; //Knight
	private Image archerImage; //Archer
	private Image CompProject; //Game Background

	private int imageX; //Archer X Position
	private int imageY; //Archer Y Position
	private int imageX2; //Knight X Position
	private int imageY2; //Knight Y Position

	@SuppressWarnings("unused")
	private int BackgroundX; //Super JFrame WIDTH
	@SuppressWarnings("unused")
	private int BackgroundY; //Super JFrame HEIGHT

	@SuppressWarnings("unused")
	private BackgroundMusic sap; // Declare a player for the background music
	Main main;



	public Game(Main main){
		this.main = main;
		CompProject = ImageConverter.getConvertedImage("CompProject.png");
		BackgroundX = (main.getFrameHeight());
		BackgroundY = (main.getFrameWidth());  
	}

	public void playGame(){

		active = true;
		IsOnTitleScreen = true;

		// These things need to be done as part of dealing with 
		// keyboard input. 'Focus' means that your application is
		// receiving the input and not some other application.
		addKeyListener(this);
		setFocusable(true);		
		requestFocus();
		setFocusTraversalKeysEnabled(false);
		addMouseListener(new MouseDetection(this));
		//Only resize
		main.setSize(BackgroundY + 1,BackgroundX + 1);
		main.setSize(BackgroundY, BackgroundX);

		// Start the background music
		sap = new BackgroundMusic("Music.wav");

		while(GamePlaying){
			System.out.print(controlsFlag);
			if(controlsFlag){
				IsOnTitleScreen = false;
				controlsFlag = false;
				IsOnControlScreen = true;
				CompProject = ImageConverter.getConvertedImage("Controls.png");
				System.out.println("Control screen loaded");
				repaint();
			}
			if(gameFlag){
				IsOnTitleScreen = false;
				gameFlag = false;
				IsOnStartScreen = true;
				CompProject = ImageConverter.getConvertedImage("GameBackground.png");
				System.out.println("Game Background screen loaded");
				MainGame();
				repaint();

			}
			if(menuFlag){
				IsOnControlScreen = false;
				menuFlag = false;
				IsOnTitleScreen = true;
				CompProject = ImageConverter.getConvertedImage("CompProject.png");
				System.out.println("Titlescreen loaded");
				repaint();				
			}			
		}
	}

	public void stopGame(){
		active = false;
	}

	public void MainGame(){
		MainGame = true;

		//Counter
		counterText = "Score : " + Integer.toString(Score);
		counter.setText(counterText);
		counter.setBounds(400, 11, 360, 60);
		counter.setFont(counter.getFont().deriveFont(55f));
		//System.out.println(counter.getSize());
		this.add(counter);

		//Images
		CompProject = ImageConverter.getConvertedImage("GameBackground.png");

		archerImage = ImageConverter.getConvertedImage("Archer.png");
		imageX = 886;
		imageY = 360;

		knightImage = ImageConverter.getConvertedImage("Knight.png");
		imageX2 = 400;
		imageY2 = 400;

		//paintComponent method
		repaint();
		//System.out.println("In Run");
	}

	// This is the method that is called when the JPanel repaint method is called.
	public void paintComponent(Graphics g)
	{
		if(MainGame){
			super.paintComponent(g); // This places an on the screen at the desired position. so that you can now put what you want.

			g.drawImage(CompProject, 0, 0, null); // This places an on the screen at the desired position.
			g.drawImage(archerImage, imageX, imageY, null); // This places an on the screen at the desired position.
			g.drawImage(knightImage, imageX2, imageY2, null); // This places an on the screen at the desired position.
		}
		else{
			super.paintComponent(g); // Calling the superclass method will essentially clear the panel so that you can now put what you want.
			g.drawImage(CompProject, imageX, imageY, null); // This places an on the screen at the desired position. so that you can now put what you want.
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// Check if the left or right arrows were pressed so that we
		// will start moving the rocket
		if (key == KeyEvent.VK_UP) {
			//UpMove = true;
			if(imageY > 25 && imageY+25 > 25){
			imageY-=25;
			repaint();
			}
			else{
				
			}
			//System.out.println(UpMove);
		}
		else if (key == KeyEvent.VK_DOWN) {
			//DownMove = true;   
			if(imageY < 730 && imageY + 25 < 730){
			imageY+=25;
			repaint();
			}
			else{
				
			}
			//System.out.println(DownMove);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		// Check if the left or right arrows were released so that we
		// will stop moving the rockets
		if (key == KeyEvent.VK_UP) {
			//UpMove = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			//DownMove = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		// We don't need this because we use 'pressed' and 'released'
		// to track the keys.
	}

	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

}