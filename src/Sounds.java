
import java.lang.Thread;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


/** <titleabbrev>SimpleAudioPlayer</titleabbrev>
    <title>Playing an audio file (easy)</title>

    <formalpara><title>Purpose</title>
    <para>Plays a single audio file.</para></formalpara>

    <formalpara><title>Usage</title>
    <cmdsynopsis>
    <command>java SimpleAudioPlayer</command>
    <replaceable class="parameter">audiofile</replaceable>
    </cmdsynopsis>
    </formalpara>

    <formalpara><title>Parameters</title>
    <variablelist>
    <varlistentry>
    <term><option><replaceable class="parameter">audiofile</replaceable></option></term>
    <listitem><para>the name of the
    audio file that should be played</para></listitem>
    </varlistentry>
    </variablelist>
    </formalpara>

    <formalpara><title>Bugs, limitations</title>

    <para>Only PCM encoded files are supported. A-law, &mu;-law,
    ADPCM, ogg vorbis, mp3 and other compressed data formats are not
    supported. For playing these, see <olink targetdoc="AudioPlayer"
    targetptr="AudioPlayer">AudioPlayer</olink>.</para>

    </formalpara>

    <formalpara><title>Source code</title>
    <para>
    <ulink url="SimpleAudioPlayer.java.html">SimpleAudioPlayer.java</ulink>
    </para>
    </formalpara>

 */
public class Sounds implements Runnable
{
    private static final int EXTERNAL_BUFFER_SIZE = 128000;

    private Thread t;
    public volatile boolean playing = true;
    private String audioFile;
    private SourceDataLine line;
    private AudioInputStream audioInputStream;

    Sounds(String arg)
    {
        audioFile = arg;
        t = new Thread(this, arg + "_thread");
        t.start();
    }

    /*public void stopMusic()
    {
        playing = false;

        //        line.stop();
        //
        //        try
        //        {
        //            audioInputStream.close();
        //        }
        //        catch (IOException e)
        //        {
        //            e.printStackTrace();
        //        }

        try
        {
            t.join();
        }
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        //System.exit(0);
    }*/

    public void run()
    {

       try
       {
           audioInputStream = AudioSystem.getAudioInputStream(new File(audioFile));
       }
       catch (Exception e)
       {
           /*
         In case of an exception, we dump the exception
         including the stack trace to the console output.
         Then, we exit the program.
            */
           e.printStackTrace();
           System.exit(1);
       }

       AudioFormat audioFormat = audioInputStream.getFormat();
       System.out.println(audioFormat);
       
       DataLine.Info   info = new DataLine.Info(SourceDataLine.class,
               audioFormat);
       
        while(playing)
        {

            try
            {
                line = (SourceDataLine) AudioSystem.getLine(info);

                /*
              The line is there, but it is not yet ready to
              receive audio data. We have to open the line.
                 */
                line.open(audioFormat);
            }
            catch (LineUnavailableException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(1);
            }

            line.start();
            
            int nBytesRead = 1;
            byte[]  abData = new byte[EXTERNAL_BUFFER_SIZE];
            while ((nBytesRead != -1) && (playing))
            {
                try
                {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0)
                {
                    int nBytesWritten = line.write(abData, 0, nBytesRead);
                }
            }

            if (! playing)
            {
                //                line.stop();

                //                try
                //                {
                //                    audioInputStream.close();
                //                }
                //                catch (IOException e)
                //                {
                //                    e.printStackTrace();
                //                }  
            	//run();
            }

            //            if (! playing)
            //            {
            //                try
            //                {
            //                    audioInputStream.close();
            //                }
            //                catch (IOException e)
            //                {
            //                    e.printStackTrace();
            //                }
            //            }
            
           // line.drain();
            //line.stop();

        }

        /*
      Wait until all data are played.
      This is only necessary because of the bug noted below.
      (If we do not wait, we would interrupt the playback by
      prematurely closing the line and exiting the VM.)

      Thanks to Margie Fitch for bringing me on the right
      path to this solution.
         */
        //line.drain();
        //line.flush();
        //line.stop();
        /*try
        {
            audioInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        System.exit(0);
*/
    }
}



/*** SimpleAudioPlayer.java ***/