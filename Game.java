/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author YeMaw
 */
public abstract class Game{
    
    /* Game Controlling */
    public enum Direction{up, right, down, left}
    
    public boolean gameRunning = false;
    public static Game currentRunningGame;

    /* Game Leveling and Scoring */
    private static int level=1, score=0, speed=1, charLeft=4; //charLeft is Total Avaiable Character

    /* Other Helpers */
    public static Random r = new Random();
    
    /* Just For Easy Access From Game */
    public static final int PX = Screen.PX;
    public static final int GAME_WIDTH = Screen.GAME_WIDTH;
    public static final int GAME_HEIGHT = Screen.GAME_HEIGHT;
    
    public Game(){}
    
    /*
     * These methos are to call from other objects to control the status of game.
     */
    public static void gameStart(Game g){
        Screen.showGameBoard();
        //force stop the old game if running
        if(currentRunningGame != null){
            currentRunningGame.doStop();
            gameStop();
        }
        gameScreenReset();
        //Play Song
        Gameboy.soundPlay("GameStart");
        Screen.gameSetStatusLable("");
        setCharLeft(4);
        currentRunningGame = g;
        currentRunningGame.doStart();
        
    }
   
    public static void gameStop(){
        setMarks(0, 1, 1);//reset the marks.
        if(currentRunningGame != null){
            currentRunningGame.gameRunning = false;
        }
        currentRunningGame = null;
    }
    
    public static void gamePauseResume(){
        if(currentRunningGame != null){
            currentRunningGame.gameRunning = currentRunningGame.gameRunning ? false : true;
            if(currentRunningGame.gameRunning){
                Screen.gameSetStatusLable("");
            } else {
                Screen.gameSetStatusLable("Pause");
            }
        }
    }
    
    public static boolean isGameRunning(){
        return ((currentRunningGame != null) && (currentRunningGame.gameRunning)) ? true : false;   
    }
    
    
    /*
     * Methods start from here are to call from child, the game.
     */
    
    /*
     * Helpers for Running Game
     */
    protected static void gameLevelUp(){
        gameScreenReset();
        currentRunningGame.gameRunning = false;
        Gameboy.soundPlay("GameStart");
        Screen.gameSetStatusLable("Level Up");
        Screen.gameScreenReset(); //show gameover animation
        Animation.AnimationStack = new AnimationSlideUpDown(30);
        synchronized (Animation.AnimationStack) {
            try {
                Animation.AnimationStack.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Screen.gameSetStatusLable("");
        currentRunningGame.doLevelUp();
    }
    
    protected static void gameCharDie(Point dieAt){
        currentRunningGame.gameRunning = false;
        Gameboy.soundPlay("Hited"); //play the hited sound
        
        charLeft--; //reduce one character
        Screen.gameSetCharLeft(charLeft);
        
        Animation.AnimationStack = new AnimationExplode(dieAt); //and show the exploded animation
        synchronized (Animation.AnimationStack) {
            try {
                Animation.AnimationStack.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if(charLeft == 0){//if no more chareacter left and the game.
            gameOver();
        } else {//else animate screen up and down 
            Screen.gameScreenReset();
            Animation.AnimationStack = new AnimationSlideUpDown(20);
            synchronized (Animation.AnimationStack) {
                try {
                    Animation.AnimationStack.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }//and game is restart
            Gameboy.soundPlay("GameStart");
            currentRunningGame.doStart();    
        }
    }
    
    public static void gameOver(){
        currentRunningGame.gameRunning = false;
        Gameboy.soundPlay("GameOver");
        currentRunningGame.doStop();
        setMarks(0, 1, 1);
        gameStop(); //force stop the game if game do not stop properly.
        //Screen.gameSetStatusLable("Game Over");
        Screen.gameScreenReset(); //show gameover animation
        Animation.AnimationStack = new AnimationSlideUpDown(140);
        synchronized (Animation.AnimationStack) {
            try {
                Animation.AnimationStack.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Screen.showGameList();
    }
    
    public static void gameWon(){
        currentRunningGame.gameRunning = false;
        currentRunningGame.doStop();
        gameStop(); //force stop the game if game do not stop properly.
        
        Gameboy.soundPlay("GameboyStart");
        Screen.gameSetStatusLable("You Won!");
        Screen.gameScreenReset(); //show gameover animation
        Animation.AnimationStack = new AnimationSlideUpDown(60); //and show the exploded animation
        synchronized (Animation.AnimationStack) {
            try {
                Animation.AnimationStack.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Screen.gameSetStatusLable("");
            }
        }
        setMarks(getScore(), 1, 1);//reset the marks.
        Screen.showGameList();
    }
    
    public static int gameCalculateSpeed() {
         return 600-(level*40); //an appropriate speed
    }
    
    public static void gameMarksRefresh(){
        Screen.gameMarksRefresh();
    }
    
    public static void gameScreenRefresh(){
        Screen.gameScreenRefresh();
    }
    
    public static void gameScreenReset(){
        Screen.gameScreenReset();
    }
    
    public static ArrayList<PX> getAllPXonScreen(){
        return Screen.getAllPXonScreen();
    }
    /* 
     * Setters 
     */
    public static void setLevel(int level) {
        Game.level = level;
        gameMarksRefresh();
    }

    public static void setScore(int score) {
        Game.score = score;
        gameMarksRefresh();
    }

    public static void setSpeed(int speed){
        Game.speed = speed;
        gameMarksRefresh();
    }
    
    //to set all marks from current playing game.
    public static void setMarks(int score, int level, int speed){
        Game.score = score;
        Game.level = level;
        Game.speed = speed;
        gameMarksRefresh();
    }
    
    public static void setCharLeft(int charLeft){
        Game.charLeft = charLeft;
        Screen.gameSetCharLeft(Game.charLeft);
    }
    
    /* 
     * Getters 
     */
    public static int getLevel() {
        return level;
    }

    public static int getScore() {
        return score;
    }

    public static int getSpeed() {
        return speed;
    }
    public static int getCharLeft(){
        return charLeft;
    }
    
    /*
     * To implement in child class
     */
    abstract void doStart();
    abstract void doStop();
    abstract void doLevelUp();
    abstract void doLeft();
    abstract void doRight();
    abstract void doUp();
    abstract void doDown();
    abstract void doShoot();
}
