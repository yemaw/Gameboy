/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author YeMaw
 */
public class Control implements KeyListener, ActionListener{

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        
        if((e.getKeyCode() == 37 || e.getKeyCode() == 65) && (Game.isGameRunning())){
            Gameboy.soundPlay("PX");
            Game.currentRunningGame.doLeft();
        }

        if((e.getKeyCode() == 38 || e.getKeyCode() == 87) && (Game.isGameRunning())){
            Gameboy.soundPlay("PX");
            Game.currentRunningGame.doUp();
        }

        if((e.getKeyCode() == 39 || e.getKeyCode() == 68) && (Game.isGameRunning())){
            Gameboy.soundPlay("PX");
            Game.currentRunningGame.doRight();
        }

        if((e.getKeyCode() == 40 || e.getKeyCode() == 83) && (Game.isGameRunning())){
            Gameboy.soundPlay("PX");
            Game.currentRunningGame.doDown();
        }

        if((e.getKeyCode() == 10) && (Game.currentRunningGame != null)){
            Game.gamePauseResume();
        }

        if((e.getKeyCode() == 32) && (Game.isGameRunning())){
            Gameboy.soundPlay("PX");
            Game.currentRunningGame.doShoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        
        /*
         * Menu Click Actions
         */
        if(event.equals(ScreenMenu.newGame)){
            Game.setMarks(0, 1, 1);
            Screen.showGameList();
        } else if(event.equals(ScreenMenu.exitGame)){
            Gameboy.gameboyOff();
        } else if(event.equals(ScreenMenu.controls)){
            String st = " left arrow - to go left"
                       +"\n right arrow - to go right"
                       +"\n up arrow - to go up"
                       +"\n down arrow - to go down"
                       +"\n enter - to start/pause"
                       +"\n space - to shoot";
            Gameboy.showMessageDialog(st);
        } else if(event.equals(ScreenMenu.about)){
            String st = "Version - 1.0 (beta)\n"+
                        "Developer - Ye Maw\n"+
                        "*** Version 1.0 (beta) ***\n" +
                        "- Snake Game Added.\n" +
                        "* Know Bugs *\n" +
                        "- [mac/windows] do not show animations on dead event by user pressed action. (always)\n" +
                        "- [ windows ] do not properly show status updates. (frequent)\n" +
                        "- [ windows ] sometime stop working after dead. (frequent)\n" +
                        "- [mac] shape of pixels sometime changed to native jbutton style. (rare)"
                        ;
            Gameboy.showMessageDialog(st);
        } else if(event.equals(ScreenList.startGame)){
            Gameboy.soundStop(); //stop if a sound is playing
            if(ScreenList.snakeRadio.isSelected()){
                Game.gameStart(new GameSnake());
            }
        }
        
    }
}
