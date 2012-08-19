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
public class AnimationExplode extends Animation implements Runnable{

    private Thread t;
    private int milisecond;
    private static Point P,p;
    
    public AnimationExplode(Point p) {
        this.P    = p;
        AnimationCharDie();
    }
    
    private void AnimationCharDie(){
        this.milisecond = 30;
        
        t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        //RePositioning if exploring points can reach out of bound
        if(P.x > GAME_WIDTH-(PX*3)){
            P.x = GAME_WIDTH-(PX*3);
        }
        if(P.x < PX*2){
            P.x= PX*2;
        }
        if(P.y > GAME_HEIGHT-(PX*3)){
            P.y = GAME_HEIGHT-(PX*3);
        }
        if(P.y < PX*2){
            P.y = PX*2;
        }
        
        P.x = (P.x)-(PX*2); //getting the left top corner point for explosion
        P.y = (P.y)-(PX*2);
        
        //generating points for exploding area.
        p = new Point(P); //tempory point holding var
        int n = 0;
        Point[] ep = new Point[25];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ep[n] = new Point(p);
                p.x = p.x+PX;
                n++;
            }
            p.x = P.x; //reset x coordinate from original P.x
            p.y = p.y+PX; //increase y coordinate
        }//got exploding points, ep
        
        //remove everyting first from exploding area
        ArrayList<PX> lPX = new ArrayList<PX>();
        for (int i = 0; i < ep.length; i++) {
            lPX = Screen.getPXAtLocation(ep[i]);
            if(lPX.size()>0){
                for (PX px : lPX) {
                    px.removePX();
                }
            }
        }
        Screen.gameScreenRefresh();
        
        //start animating
        PX[] px = new PX[25];
        for (int i = 0; i < ep.length; i++) {
            px[i] = new PX(ep[i]);
            px[i].visiblePX(false);
        }
        
        for (int i = 0; i < 3; i++) {
            px[12].visiblePX(true);
            Screen.gameScreenRefresh();
                sleep(100);
            px[6].visiblePX(true);
            px[8].visiblePX(true);
            px[16].visiblePX(true);
            px[18].visiblePX(true);
            Screen.gameScreenRefresh();
                sleep(100);
            px[1].visiblePX(true);
            px[3].visiblePX(true);
            px[5].visiblePX(true);
            px[9].visiblePX(true);
            px[15].visiblePX(true);
            px[19].visiblePX(true);
            px[21].visiblePX(true);
            px[23].visiblePX(true);
            Screen.gameScreenRefresh();
                sleep(100);
            px[1].visiblePX(false);
            px[3].visiblePX(false);
            px[5].visiblePX(false);
            px[9].visiblePX(false);
            px[15].visiblePX(false);
            px[19].visiblePX(false);
            px[21].visiblePX(false);
            px[23].visiblePX(false);
            Screen.gameScreenRefresh();
                sleep(100);
            px[0].visiblePX(true);
            px[2].visiblePX(true);
            px[4].visiblePX(true);
            px[10].visiblePX(true);
            px[14].visiblePX(true);
            px[20].visiblePX(true);
            px[22].visiblePX(true);
            px[24].visiblePX(true);
            Screen.gameScreenRefresh();
                sleep(100);
            px[0].visiblePX(false);
            px[2].visiblePX(false);
            px[4].visiblePX(false);
            px[10].visiblePX(false);
            px[12].visiblePX(false);
            px[14].visiblePX(false);
            px[20].visiblePX(false);
            px[22].visiblePX(false);
            px[24].visiblePX(false);

            px[1].visiblePX(true);
            px[3].visiblePX(true);
            px[5].visiblePX(true);
            px[9].visiblePX(true);
            px[15].visiblePX(true);
            px[19].visiblePX(true);
            px[21].visiblePX(true);
            px[23].visiblePX(true);
            Screen.gameScreenRefresh();
                sleep(100);
            px[6].visiblePX(false);
            px[8].visiblePX(false);
            px[16].visiblePX(false);
            px[18].visiblePX(false);
            Screen.gameScreenRefresh();
                sleep(100);
        }
        for (int i = 0; i < px.length; i++) {
            px[i].removePX();
        }
        Screen.gameScreenRefresh();
        
        //notify that this animation is complete.
        if(Animation.AnimationStack!=null){
            synchronized (Animation.AnimationStack) {
                Animation.AnimationStack.notify();
            }
        }
        
    }
    
    private void sleep(int milisecond){
        try {
            t.sleep(milisecond);
        } catch (InterruptedException ex) {
            Logger.getLogger(AnimationExplode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
