
package videogame;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.net.URL;
                                                        

/**
 *
 * @author GenaroSañudo
 */
public class SoundClip { 
                                                       
        private AudioInputStream sample;
        private Clip clip; 
        private boolean looping = false;
        private int repeat = 0;
        private String filename = "";
        
        public SoundClip() { 
                                                       
                try {  
                                                      
                      clip = AudioSystem.getClip();
                }catch (LineUnavailableException e) {  
                                                      
                      System.out.println("Error en " + e.toString());
                }
        }
        

        public SoundClip(String filename) { 
                                                       
                this();
                load(filename);
        }

        /**
         * Set looping
         * @param looping to modify
         */
        public void setLooping(boolean looping) {

                this.looping = looping; 
        }           
          
        /**
         * Set repeat
         * @param repeat to modify
         */
        public void setRepeat(int repeat) {

                this.repeat = repeat;
        }  
            
        /**
         * Set filename
         * @param filename to modify
         */
        public void setFilename(String filename) {

                this.filename = filename; 
        }
//Los métodos de acceso son usados para obtener los valores del objeto SoundClip.

        /**
         * Get clip value
         * @return clip
         */
        public Clip getClip() { 
                                                       
                return clip; 
        }
         
        /**
         * Get looping value
         * @return looping
         */
        public boolean getLooping() { 
                                                       
                return looping;
        }
            
        /**
         * Get repeat value
         * @return repeat
         */
        public int getRepeat() {
                                                        
                return repeat; 
        }
           
        /**
         * Get filename value
         * @return filename
         */
        public String getFilename() { 
                                                       
                return filename;
        }
            
       
        private URL getURL(String filename) {
                                                       
                URL url = null;
                try {
                                                     
                      url = this.getClass().getResource(filename);
                }catch (Exception e) {
                                                       
                      System.out.println("Error en " + e.toString());
                }
                return url;
        }

        /**
         * Check if its loaded
         * @return boolean 
         */
        public boolean isLoaded() {
                                                        
                return (boolean)(sample != null);
        }

        public boolean load(String audiofile) { 
                                                       
                try {

                      setFilename(audiofile);
                      sample = AudioSystem.getAudioInputStream(getURL(filename)); 
                      clip.open(sample); 
                      return true;
                } catch (IOException e) { 
                                                       
                      System.out.println("Error en " + e.toString());
                      return false;
                }catch (UnsupportedAudioFileException e) {
                                                        
                      System.out.println("Error en " + e.toString());
                      return false;
                }catch (LineUnavailableException e) {
                                                      
                      System.out.println("Error en " + e.toString());
                      return false;
                }
        }

        public void play() { 
                                                       
                if (!isLoaded()) 
                    return;
                                                        
                clip.setFramePosition(0);
                                                     
                if (looping) 
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                else 
                    clip.loop(repeat);
        }

        public void stop() { 
                                                       
                clip.stop();
        }
}
