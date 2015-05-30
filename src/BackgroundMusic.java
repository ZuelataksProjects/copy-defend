
import java.lang.Thread;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundMusic implements LineListener {
	
    @SuppressWarnings("unused")
	private String audioFileName;
    private Clip audioClip;
    private AudioFormat audioFormat;
    private AudioInputStream audioStream;
    private File audioFile;
    private DataLine.Info info;
 
    public volatile boolean playing = true;

    /**
     * Constructor of the BackgroundMusic object
     * @param audioFileName
     */
    public BackgroundMusic(String audioFileName) {
        this.audioFileName = audioFileName;
        play(audioFileName);
        }
 
    /**
     * Plays the music, need the Filename to work (Caution Loop!)
     * The methode update is the Listener for this
     * @param audioFileName
     */
    public void play (String audioFileName) {
    	audioFile = new File(audioFileName);
     
        try {
        	audioStream = AudioSystem.getAudioInputStream(audioFile);     
            audioFormat = audioStream.getFormat();    
            info = new DataLine.Info(Clip.class, audioFormat);     
            audioClip = (Clip) AudioSystem.getLine(info);    
            audioClip.addLineListener(this);   
            audioClip.open(audioStream);   
            audioClip.loop(111);
        }
                           

            catch (UnsupportedAudioFileException ex) {
                System.out.println("The specified audio file is not supported.");
                ex.printStackTrace();
            } 
            catch (LineUnavailableException ex) {
                System.out.println("Audio line for playing back is unavailable.");
                ex.printStackTrace();
            } 
            catch (IOException ex) {
                System.out.println("Error playing the audio file.");
                ex.printStackTrace();
            }
        }
    
    public void stopMusic(){
    	audioClip.close();
    }
       
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
        
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");             
        } 
        else if (type == LineEvent.Type.STOP) {
            
            System.out.println("Playback completed.");       
        }		
	}
}