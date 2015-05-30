import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainGame2 extends JPanel {

	boolean MainGameRun = true;
	private JLabel counter;
	int Score = 0;
	private Game game;
	public MainGame2(Game game){
		run();
	
	}

	public void run(){
		
		
			counter = new JLabel(Integer.toString(Score));
			counter.setBounds(605, 11, 40, 40);
			counter.setVisible(true);
			this.add(counter);
		
		
		
		
		
	}
	public JLabel getJLabel(){
		return counter;
	}
	
}