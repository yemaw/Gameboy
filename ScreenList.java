/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author YeMaw
 */
public class ScreenList extends JPanel{

    public static JRadioButton snakeRadio, tankRadio;
    public static JButton startGame,resumeGame;
    
    private static final int PX = Screen.PX;
    private static final int GAME_WIDTH  = Screen.GAME_WIDTH;
    private static final int GAME_HEIGHT = Screen.GAME_HEIGHT;
    public  static final Color C_SCREEN = Screen.C_SCREEN;
    
    public ScreenList() {
        setLayout(null);
        setBounds(1, 1, Screen.GAME_WIDTH, Screen.GAME_HEIGHT);
        
        JLabel chooseGame = new JLabel("Choose Game");
        chooseGame.setFont(new Font("Arial", Font.ITALIC, 18));
        chooseGame.setBounds(PX, PX, GAME_WIDTH-(PX*2), 2*PX);
        add(chooseGame);
        
        JPanel snakePanel = new JPanel(null);
        snakePanel.setBounds(PX,3*PX,GAME_WIDTH-(2*PX), 2*PX);
        snakePanel.setBackground(C_SCREEN);
        snakeRadio = new JRadioButton();
        snakeRadio.setBounds(0,0,2*PX,2*PX);
        snakeRadio.setFocusable(false);
        JLabel snakeLabel = new JLabel("Snake");
        snakeLabel.setBounds(2*PX,0,6*PX,2*PX);
        snakePanel.add(snakeRadio);
        snakePanel.add(snakeLabel);
        add(snakePanel);
        
        JPanel tankPanel = new JPanel(null);
        tankPanel.setBackground(C_SCREEN);
        tankPanel.setBounds(PX,5*PX,GAME_WIDTH-(2*PX), 2*PX);
        tankRadio = new JRadioButton();
        tankRadio.setBounds(0,0,2*PX,2*PX);
        tankRadio.setFocusable(false);
        tankRadio.setBackground(C_SCREEN);
        JLabel tankLabel = new JLabel("Tank");
        tankLabel.setBounds(2*PX,0,6*PX,2*PX);
        tankPanel.add(tankRadio);
        tankPanel.add(tankLabel);
        //add(tankPanel);
        
        ButtonGroup gameRadioList = new ButtonGroup();
        gameRadioList.add(snakeRadio);
        gameRadioList.add(tankRadio);
        
        JPanel buttonsPanel = new JPanel(new GridLayout(2,1));
        buttonsPanel.setBounds(PX,GAME_HEIGHT-(4*PX),GAME_WIDTH-(2*PX),4*PX);
        buttonsPanel.setBackground(C_SCREEN);
        startGame = new JButton("Start Game");
        startGame.setFocusable(false);
        startGame.addActionListener(Gameboy.getControl());
        buttonsPanel.add(startGame);
//        if(Game.currentRunningGame!=null){
//            resumeGame = new JButton("Resume Game");
//            resumeGame.setFocusable(false);
//            resumeGame.addActionListener(Gameboy.Control);
//            buttonsPanel.add(resumeGame);
//        }
        add(buttonsPanel);
        
    }
    
    
}
