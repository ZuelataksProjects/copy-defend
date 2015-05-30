import javax.swing.JFrame;
import java.awt.Container;

public class Main extends JFrame
{
 private static final long serialVersionUID = 1L;
 
 private final int FRAME_WIDTH = 1040;
    private final int FRAME_HEIGHT = 804;

    // This is the height of the title bar at the top of the frame
    public final static int TITLE_HEIGHT = 20;

    // Declare the Game
    private Game game;
 
    
    /**
     * Constructor of Main which creates the Game object
     */
    public Main(){
      
        this.setTitle("Defend the Keep");           
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // this.setLayout(null);
      
                 
        game = new Game(this);   
        this.add(game);
        this.setVisible(true);
        game.playGame();
      
       
 
        
    }
    
    public static void main(String[] args) throws InterruptedException 
    {
     new Main();
    }
    
    public int getFrameHeight(){
     return FRAME_HEIGHT;
    }
    public int getFrameWidth(){
     return FRAME_WIDTH;
    }
    
}