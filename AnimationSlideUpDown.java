/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Point;

/**
 *
 * @author YeMaw
 */
public class AnimationSlideUpDown extends Animation implements Runnable{
    
    private Thread t;
    private int milisecond;
    private PX[] px;
    private Point p;
    
    public AnimationSlideUpDown(int milisecond) {
        this.milisecond = milisecond;
        t = new Thread(this);
        t.start();
    }
    
    
    @Override
    public void run(){
        int numCols = GAME_WIDTH/PX;
        int numRows = GAME_HEIGHT/PX;
        
        px = new PX[numCols * numRows];
        p  = new Point(0,GAME_HEIGHT-(PX*1));
        
        int n = 0;
        
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                px[n] = new PX(p.x, p.y);
                
                p.x += PX;
                n++;
            }
            p.x = 0;
            p.y -= PX;
            
            Screen.gameScreenRefresh();
            try{
                t.sleep(milisecond);
            } catch (InterruptedException ie){}
        }
        
        n--;//bcoz i added 1 more in previous the last loop.
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                px[n].removePX();
                n--;
            }
            Screen.gameScreenRefresh();
            try{
                t.sleep(milisecond);
            } catch (InterruptedException ie){}
        }

        //notify that this animation is complete
        if(Animation.AnimationStack!=null){
            synchronized (Animation.AnimationStack) {
                Animation.AnimationStack.notify();
            }
        }
    }
    
}
