/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author YeMaw
 */
public class ScreenMenu extends JMenuBar{
    
    public static JMenu game, help;
    public static JMenuItem newGame, exitGame, controls, about;
    
    public ScreenMenu() {
        
        /** menu hierarchy **/
        game = new JMenu("Game");
            newGame = new JMenuItem("New Game");
            exitGame  = new JMenuItem("Exit Game");
        help = new JMenu("Help");
            controls  = new JMenuItem("Controls");
            about     = new JMenuItem("About");
        
        
        /** adding listeners **/
        newGame.addActionListener(Gameboy.getControl());
        exitGame.addActionListener(Gameboy.getControl());
        about.addActionListener(Gameboy.getControl());
        controls.addActionListener(Gameboy.getControl());
        /** Final adding Menu **/
        game.add(newGame);
        game.addSeparator();
        game.add(exitGame);
        
        help.add(controls);
        help.add(about);
        
        add(game);
        add(help);
    }
    
}
