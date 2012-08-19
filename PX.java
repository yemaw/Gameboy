/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;

/**
 *
 * @author yemaw
 */
public class PX extends JButton implements Runnable{
    
    /* For Easy Access */
    public static final int   PX   = Screen.PX;
    public static final Color C_PX = Screen.C_PX;
    public boolean visible;
    public boolean blinking = false;
    
    /* To blink this PX */
    public static Thread t;
    
    /* Location of this object */
    public Point location;
    
    public void addPX(){
        Screen.addPX(this);
        Screen.gameScreenRefresh();
    }
    
    private void createPX(){
        location  = new Point();
        setEnabled(false);
        setOpaque(true); //need for mac os
        setBackground(C_PX);
        setSize(new Dimension(PX, PX));
    }
    
    public PX(Point location) {
        createPX();
        this.location = location;
        this.setLocation(location);    
        addPX();   
    }

    //#deprecate ths method
    public PX(int x, int y) {
        createPX();
        location.x = x;
        location.y = y;
        this.setLocation(location); 
        //##setBounds(x, y, PX, PX);
        addPX();   
    }
    
    public void visiblePX(boolean b){
        if(b){
            setLocation(location);
        } else {
            setLocation(new Point(-100,-100));
        }
    }
    
    public void movePX(Point location){
        this.location = location;
        this.setLocation(location); 
    }
    
    //#deprecate ths method
    public void movePX(int x, int y){
        this.location.x = x;
        this.location.y = y;
        this.setLocation(location); 
    }

    public void removePX(){
        if(blinking){
            stopBlink();
        }
        Screen.removePX(this);
    }
    
    public void startBlink(){
        this.blinking = true;
        t = new Thread(this);
        t.start();
    }
    
    public void stopBlink(){
        t.stop();
    }
    
    //blinking 
    @Override
    public void run() {
        try{
            if(this.visible){
                setLocation(-100,-100);
                visible = false;
                t.sleep(100);
            } else {
                setLocation(location);
                visible = true;
                t.sleep(500);
            }
        } catch(InterruptedException ie){
            ie.printStackTrace();
        } finally {
            run();
        }
    }
    
}
