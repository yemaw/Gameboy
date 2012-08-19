/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author YeMaw
 */
public class Screen extends JPanel{
    
    /* Containers by hierarchy*/
    public  static JPanel gbScreenContainer,gbScreenContainer2,
                              gbScreenLeftContainer,
                                  gbList,gbGame,
                              gbScreenRightContainer,
                                  gbScore,
                                    rightRow1,rightRow2,rightRow3,rightRow4,
                          gbButtonsContainer;

    private static ScreenText scoreLabel, levelLabel, speedLabel, statusLabel; //labels at right area
    
    private static ArrayList<PX> allPX = new ArrayList(); //All PX that put on the Screen are store in this ArrayList
    private static PXr[] pxr; //use for character left status in right area
    
    /* Sizes */
    public static final int GAME_WIDTH = 150, GAME_HEIGHT = 300;    
    public static final int PX = 15;
    
    /* Decoration and Color*/
    public static final Color C_GB = new Color(210,210,210);
    public static final Color C_SCREEN = new Color(230,230,230);
    public static final Color C_PX = new Color(130, 130, 130);

    public Screen(){
//hint if use image as backgound        
//        try {
//            bgIMG = ImageIO.read(new File("background.png"));
//        } catch (IOException ex) {
//            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
//        }
      
        setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 10));
        setLayout(null);
        setBackground(C_GB);
        
            gbScreenContainer  = new JPanel();
            gbScreenContainer.setLayout(null);
            gbScreenContainer.setBounds(40, 30, 300, GAME_HEIGHT+60);
            TitledBorder tb = BorderFactory.createTitledBorder("Gameboy");
            tb.setTitleJustification(TitledBorder.CENTER);
            gbScreenContainer.setBorder(tb);
            gbScreenContainer.setBackground(C_GB);
            
            gbScreenContainer2 = new JPanel();
            gbScreenContainer2.setLayout(null);
            gbScreenContainer2.setBounds(10,20,280,GAME_HEIGHT+30);
            gbScreenContainer2.setBorder(BorderFactory.createLoweredBevelBorder());
            gbScreenContainer2.setBackground(C_SCREEN);
            
                gbScreenLeftContainer = new JPanel();
                gbScreenLeftContainer.setLayout(null);
                gbScreenLeftContainer.setBounds(15, 15, GAME_WIDTH+2, GAME_HEIGHT+2);//+2 is for border
                gbScreenLeftContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                gbScreenLeftContainer.setBackground(C_SCREEN);
                    
                    gbGame  = new JPanel();
                    gbGame.setEnabled(false);
                    gbGame.setBorder(null);
                    gbGame.setBounds(1,1,GAME_WIDTH,GAME_HEIGHT);
                    gbGame.setBackground(C_SCREEN);
                    
                    gbList = new ScreenList();
                    gbList.setBackground(C_SCREEN);
                    showGameList();
                    
                gbScreenRightContainer = new JPanel();
                gbScreenRightContainer.setLayout(null);
                gbScreenRightContainer.setBounds(GAME_WIDTH+15, 16, 105, GAME_HEIGHT);
                gbScreenRightContainer.setBackground(C_SCREEN);
                
                    gbScore = new JPanel();
                    gbScore.setLayout(null);
                    gbScore.setBounds(0,0,105,GAME_HEIGHT);
                    gbScore.setBackground(C_SCREEN);
                    
                        rightRow1 = new JPanel();
                        rightRow1.setLayout(new GridLayout(0,1));
                        rightRow1.setBorder(BorderFactory.createTitledBorder("Hi-Score"));
                        rightRow1.setBounds(PX,0,90,60);
                        rightRow1.setBackground(C_SCREEN);
                        
                        rightRow2 = new JPanel();
                        rightRow2.setLayout(null);
                        rightRow2.setBounds(PX,65,90,75);
                        rightRow2.setBorder(BorderFactory.createEtchedBorder());
                        rightRow2.setBackground(C_SCREEN);
                        
                        rightRow3 = new JPanel();
                        rightRow3.setLayout(new GridLayout(0,2));
                        rightRow3.setBounds(PX,160,90,60);
                        rightRow3.setBackground(C_SCREEN);
                        
                        rightRow4 = new JPanel();
                        rightRow4.setLayout(new GridLayout(0,1));
                        rightRow4.setBounds(PX,240,90,60);
                        rightRow4.setBackground(C_SCREEN);
                        
                        scoreLabel = new ScreenText(25,20);
                        levelLabel = new ScreenText(15,20);
                        speedLabel = new ScreenText(15,20);
                        statusLabel = new ScreenText(10,20);
                        
                        scoreLabel.setBackground(C_SCREEN);
                        levelLabel.setBackground(C_SCREEN);
                        speedLabel.setBackground(C_SCREEN);
                        statusLabel.setBackground(C_SCREEN);
                        
                            
            gbButtonsContainer = new ScreenButtons();
            gbButtonsContainer.setBounds(30,400,320,200);
            gbButtonsContainer.setBackground(C_GB);
            //gbButtonsContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            
        rightRow1.add(scoreLabel);
        
        //preparing charLeft area
        int n = 0;
        Point p = new Point(PX,PX);
        pxr = new PXr[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pxr[n] = new PXr(new Point(p));
                pxr[n].addPX();
                pxr[n].visiblePX(false);
                p.x += PX;
                n++;
            }
            p.x = 0;
            p.y += PX;
        }
        
        rightRow3.add(new JLabel("Level"));
        rightRow3.add(new JLabel("Speed"));
        rightRow3.add(levelLabel);
        rightRow3.add(speedLabel);
        rightRow4.add(statusLabel);
        
        gbScore.add(rightRow1);
        gbScore.add(rightRow2);
        gbScore.add(rightRow3);
        gbScore.add(rightRow4);
        
        gbScreenRightContainer.add(gbScore);
        
        gbScreenContainer2.add(gbScreenLeftContainer);
        gbScreenContainer2.add(gbScreenRightContainer);
        gbScreenContainer.add(gbScreenContainer2);
        
        add(gbScreenContainer);
        add(gbButtonsContainer);
        
    }
    
    public static void addPX(PX px) {
        allPX.add(px);
        gbGame.add(px);
    }
    
    
    public static void removePX(PX px) {
        allPX.remove(px);
        gbGame.remove(px);
    }
    
    public static ArrayList<PX> getAllPXonScreen(){
        return allPX;
    }
    
    public static void addPXr(PX pxr){
        rightRow2.add(pxr);
    }
    
    //repaint to primary game screen
    public static void gameScreenRefresh(){
        gbGame.repaint();
    }
    
    //remove all px and refresh the game screen.
    public static  void gameScreenReset() {
        if(allPX.size()>0){
            for (PX px : allPX) {
                gbGame.remove(px);
            }
        }
        allPX.removeAll(allPX); //#fuzzy, is this should be like this?
        //gbGame.removeAll(); //#trace,is this need any more?
        gameScreenRefresh();
    }
   
    //re-setting up the marks in right area
    public static void gameMarksRefresh(){
        scoreLabel.write(Integer.toString(Game.getScore()));
        levelLabel.write(Integer.toString(Game.getLevel()));
        speedLabel.write(Integer.toString(Game.getSpeed()));
        
    }
    
    //to get px(s) in a specific location. use to remove specifice px(s) from screen
    public static ArrayList<PX> getPXAtLocation(Point p){
        ArrayList<PX> lPX = new ArrayList<PX>();
        if(allPX.size()>0){
            for (PX px : allPX) {
                if(px.location.equals(p)){
                    lPX.add(px);
                }
            }
        }
        return lPX;
    }
    
    //to show how many game character left in right area
    public static void gameSetCharLeft(int charLeft) {
        for (PX px : pxr) {
            px.visiblePX(false);
        }
        for (int i = 0; i < charLeft; i++) {
            pxr[i].visiblePX(true);
        }
    }
    
    //set a status to right area
    public static void gameSetStatusLable(String st){
        statusLabel.write(st);
    }
    
    //switching to game list screen from showing other screens
    public static void showGameList() {
        if(Game.currentRunningGame != null){
            Game.currentRunningGame.gameRunning = false;
            Game.currentRunningGame.doStop();
            Game.gameStop();
        }
        gbScreenLeftContainer.removeAll();
        gbScreenLeftContainer.add(gbList);
        gbScreenLeftContainer.repaint();
    }
    
    //switching to game screen from showing other screens
    public static void showGameBoard() {
        gbScreenLeftContainer.removeAll();
        gbScreenLeftContainer.add(gbGame);
    }
    
}
