/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author YeMaw
 */
public class Gameboy {
    
    /* Primary Objects */
    private static Control Control;
    private static JFrame Frame;
    private static ScreenMenu Menu;
    private static Screen Screen;
    
    /* For Audio System */
    private static URL url;
    private static Clip clip;
    
    public static void main(String[] args) {
        gameboyOn();
    }
    
    public static void gameboyOn(){
        //getting screen parameter
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        
        //init primary objects
        Frame   = new JFrame();
        Control = new Control();
        Menu    = new ScreenMenu();
        Screen  = new Screen();
         
        //setting up JFrame
        Frame.setPreferredSize(new Dimension(380, 680));
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(false);
        Frame.setLocation((dim.width/2)-(400/2), 50);
        Frame.setBackground(Screen.C_SCREEN);
        
        Frame.setJMenuBar(Menu); //Menu
        Frame.add(Screen);   //The whole container
        Frame.addKeyListener(Control); //Primary Control Listiner 
        //AWTUtilities.setWindowOpacity(Frame,0.7f); 
        
        Frame.pack();
        Frame.setVisible(true);
        
        Game.setMarks(0, 1, 1);
        soundPlay("GameboyStart");
    }
    
    public static void gameboyOff(){
        System.exit(0);
    }
    
    public static void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(Frame, msg);
    }
    
    public static void soundPlay(String soundFileName){
        try {
            // Open an audio input stream.
            url = Screen.getClass().getResource("resources/"+soundFileName+".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
         } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (LineUnavailableException e) {
            e.printStackTrace();
         }
    }
    
    public static void soundStop(){
        if(clip.isRunning()){
            clip.stop();
        }
    }
    
    public static Control getControl(){
        return Control;
    }
    
}
 