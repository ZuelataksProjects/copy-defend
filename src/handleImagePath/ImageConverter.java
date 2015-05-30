package handleImagePath;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageConverter {
		
	public static Image getConvertedImage(String path){
		ImageIcon imgI = new ImageIcon(path);
		Image image = imgI.getImage();
			
		return image;
	}	
}
