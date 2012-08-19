/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author YeMaw
 */
public class ScreenButtons extends JPanel{
    
    public static JButton startGame,onOff;
    
    public ScreenButtons(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String st1 = "          left arrow - to go left";
        String st2 = "          right arrow - to go right";
        String st3 = "          up arrow - to go up";
        String st4 = "          down arrow - to go down";
        String st5 = "          enter - to start/pause";
        String st6 = "          space - to shoot";
        
        JLabel lb1 = new JLabel(st1);
        JLabel lb2 = new JLabel(st2);
        JLabel lb3 = new JLabel(st3);
        JLabel lb4 = new JLabel(st4);
        JLabel lb5 = new JLabel(st5);
        JLabel lb6 = new JLabel(st6);
        
        add(lb5);
        add(lb6);
        add(lb1);
        add(lb2);
        add(lb3);
        add(lb4);

    }
}
