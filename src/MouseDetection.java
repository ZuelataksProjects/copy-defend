import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class MouseDetection extends MouseAdapter {
	final private int START_LOW_X = 52;
	final private int START_HIGH_X = 246;
	final private int START_LOW_Y = 359;
	final private int START_HIGH_Y = 426;
	
	final private int MENU_LOW_X = 105;
	final private int MENU_HIGH_X = 297;
	final private int MENU_LOW_Y = 638;
	final private int MENU_HIGH_Y = 728;

	final private int CONTROLS_LOW_X = 720;
	final private int CONTROLS_HIGH_X = 1016;
	final private int CONTROLS_LOW_Y = 359;
	final private int CONTROLS_HIGH_Y = 415;
	
	private Game game;
	
	public MouseDetection(Game game){
		this.game = game;
	}
	public void mousePressed(MouseEvent e) {
		    
		//check CONTROLS button
		//int thing = e.getX();
		//int thing2 = e.getY();
		//JOptionPane.showMessageDialog(null, thing + " " + thing2 );
		if(Game.IsOnTitleScreen){
			if(e.getX() > (CONTROLS_LOW_X) && e.getX() < (CONTROLS_HIGH_X) && e.getY() > (CONTROLS_LOW_Y) && e.getY() < (CONTROLS_HIGH_Y))
			{
				//System.out.println("Control pressed");
				game.controlsFlag = true;
			}
			//check start button
			else if(e.getX() > (START_LOW_X) && e.getX() < (START_HIGH_X) && e.getY() > (START_LOW_Y) && e.getY() < (START_HIGH_Y))
			{
				//System.out.println("Start pressed");
				game.gameFlag = true;
			}
		}
		else if(Game.IsOnControlScreen){
			if(e.getX() > (MENU_LOW_X) && e.getX() < (MENU_HIGH_X) && e.getY() > (MENU_LOW_Y) && e.getY() < (MENU_HIGH_Y))
			{
				//System.out.println("Control pressed");
				game.menuFlag = true;
			}
		}
		else if(Game.IsOnStartScreen){
			
		}
	}
}

