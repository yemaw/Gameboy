/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YeMaw
 */
public class GameSnake extends Game implements Runnable{
    
    /* Snake */
    private int sLength;
    private final int START_SLENGTH=3, END_SLENGTH = 16;
    private PX sParts[];
    private int sLocaleX[], sLocaleY[];
    
    /* Food */
    private PX food;
    private int fLocaleX, fLocaleY;
    
    /* Leveling Walls*/
    ArrayList<Point> wallPoints = new ArrayList();//wall points
    
    /* Thread */
    public Thread t;

    /* Game Controls*/
    private Game.Direction currentDirection = Game.Direction.right;
    private boolean canGoLeft, canGoRight, canGoUp, canGoDown;
    
    public GameSnake(){
        t = new Thread(this);            
        t.start();
    }
    
    @Override
    void doStart() {
        reInitializeValues();
        createFood();
        createFirstSnake();
        gameRunning = false;
    }

    @Override
    void doStop() {
        gameRunning = false;
    }
    
    @Override
    void doLevelUp(){
        reInitializeValues();
        createFirstSnake();
        createFood();
        gameRunning = false;
    }

    @Override
    void doLeft() {
        if(canGoLeft){
            currentDirection = Game.Direction.left;
            canGoRight = false;
            canGoUp    = true;
            canGoDown  = true;
            moveSnake();
        } 
    }

    @Override
    void doRight() {
        if(canGoRight){
            currentDirection = Game.Direction.right;
            canGoLeft  = false;
            canGoUp    = true;
            canGoDown  = true;
            moveSnake();
        }
    }

    @Override
    void doUp() {
        if(canGoUp){
            currentDirection = Game.Direction.up;
            canGoLeft  = true;
            canGoRight = true;
            canGoDown  = false;
            moveSnake();
        }
    }

    @Override
    void doDown() {
        if(canGoDown){
            currentDirection = Game.Direction.down;
            canGoLeft  = true;
            canGoRight = true;
            canGoUp    = false;
            moveSnake();
        }
    }

    @Override
    void doShoot() {
        moveSnake();
    }

    //timmer function
    @Override
    public void run() {
        int milisecond = gameCalculateSpeed();
        try {
            if(gameRunning){
                moveSnake();
            }
            t.sleep(milisecond);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameSnake.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            run();
        }
    }
    
    /*
     * @desc setting default values to start a new game
     */
    private void reInitializeValues(){
        canGoLeft  = false;
        canGoRight = true;
        canGoUp    = true;
        canGoDown  = true;
        currentDirection = Game.Direction.right;
        sLength = START_SLENGTH;
        fLocaleX = -100;
        fLocaleY = -100;
        sParts  = new PX[END_SLENGTH];
        sLocaleX = new int[END_SLENGTH];
        sLocaleY = new int[END_SLENGTH];
        
        createWalls();
    }
    
    private void createFirstSnake(){
        //create the first snake at left top corner
        sLocaleX[0] = (PX*(START_SLENGTH))-PX;
        sLocaleY[0] = 0;
        
        //adding a body part start from head
        for (int i = 0; i < sLength; i++) {
            sParts[i] = new PX(sLocaleX[i], sLocaleY[i]);
            //for next body part
            sLocaleX[i + 1] = sLocaleX[i] - PX;
            sLocaleY[i + 1] = sLocaleY[i];
        }
        //sParts[0].startBlink();
    }

    /*
     * @desc get a random point on screen and create food
     */
    private void createFood(){
        Point p = new Point();
        boolean reCreate = false;
        //get a random point 
        p.x = fLocaleX = PX * r.nextInt((GAME_WIDTH-PX)/PX); 
        p.y = fLocaleY = PX * r.nextInt((GAME_HEIGHT-PX)/PX);
        ArrayList<PX> pxList = new ArrayList();
        pxList = Game.getAllPXonScreen();
        for (PX px : pxList) {
            if(p.equals(px.location)){
                reCreate = true;
                break;
            }
        }
        if(reCreate){
            createFood();
        } else {
            food = new PX(p);
            food.startBlink();
        }
        
    }
    
    
    private void createWalls(){
        //remove all walls first if exist
//        if(wallPoints.size()>0){
//            for (Point wallPoint : wallPoints) {
//                wallPoints.remove(wallPoint);
//            }
//        }
        //get new wall points
        wallPoints = LevelingHelper.getWallPointsType1(getLevel());
        
        for (Point wallPoint : wallPoints) {
            new PX(wallPoint);//no need to hold the px object. they don't move on screen, so.
        }
    }
    /*
     * @desc remove the food from screen
     */
    private void removeFood(){
        fLocaleX = -PX;
        fLocaleY = -PX;
        food.removePX();
    }
    
    private void moveSnake(){
        
        int oldHeadX = sLocaleX[0];
        int oldHeadY = sLocaleY[0];

        //move the head
        if(currentDirection.equals(Game.Direction.left)){
            sLocaleX[0] -= PX;
            sParts[0].movePX(sLocaleX[0], sLocaleY[0]);
        }
        if(currentDirection.equals(Game.Direction.right)){
            sLocaleX[0] += PX;
            sParts[0].movePX(sLocaleX[0], sLocaleY[0]);
        }
        if(currentDirection.equals(Game.Direction.up)){
            sLocaleY[0] -= PX;
            sParts[0].movePX(sLocaleX[0], sLocaleY[0]);
        }
        if(currentDirection.equals(Game.Direction.down)){
            sLocaleY[0] += PX;
            sParts[0].movePX(sLocaleX[0], sLocaleY[0]);
        }

        //move other parts
        int oldX, oldY, newX, newY;
        newX = oldHeadX;
        newY = oldHeadY;
        for (int i = 1; i < sLength; i++) {
            sParts[i].movePX(newX, newY);
            oldX = sLocaleX[i];
            oldY = sLocaleY[i];
            sLocaleX[i] = newX;
            sLocaleY[i] = newY;
            newX = oldX;
            newY = oldY;
        }

        //check moved head is on die position
        Point headPoint = new Point();
        headPoint.x = sLocaleX[0];
        headPoint.y = sLocaleY[0];
        //make game over if snake eats it's own body part
        for (int i = 1; i < sLength; i++) {
            if((sLocaleX[0] == sLocaleX[i]) && (sLocaleY[0] == sLocaleY[i])){
                gameCharDie(headPoint);
                break;
            }
        }

        //make game over if snake reached over borders
        if ((sLocaleX[0] >= GAME_WIDTH) ||
            (sLocaleX[0] < 0) ||
            (sLocaleY[0] >= GAME_HEIGHT) ||
            (sLocaleY[0] < 0)) {
                gameCharDie(headPoint);
        }
        
        //make game over if snake head hit to levelling walls
        for (Point wallPoint : wallPoints) {
            if(headPoint.equals(wallPoint)){
                gameCharDie(headPoint);
                break;
            }
        }
        
        //if snake head ate the food, give a growth
        if(fLocaleX == sLocaleX[0] && fLocaleY == sLocaleY[0]){
            growSnake();
            removeFood();
            createFood();
        }
        
    }
    
    private void growSnake(){
        Gameboy.soundPlay("PX2");
        sParts[sLength] = new PX(sLocaleX[0], sLocaleY[0]);
        gameScreenRefresh();
        sLength++;
        setScore(getScore()+100); //+10 to score
        
        //reach new level
        if(sLength >= END_SLENGTH){
            setLevel(getLevel()+1);//up one level
            setSpeed(getSpeed()+1);//speed also up
            
            if(getLevel() > 10){
                gameWon();
            }
            
            gameLevelUp(); //go next level
        }
    }
}
