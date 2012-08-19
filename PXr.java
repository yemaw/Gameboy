package gameboy;


import gameboy.PX;
import gameboy.Screen;
import java.awt.Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YeMaw
 */
public class PXr extends PX{
    
    public PXr(Point p){
        super(p);
    }
    
    //override the parent's method from preventing adding to real playing game screen and allPX list.
    //add to right screen
    @Override
    public void addPX(){
        Screen.addPXr(this);
    }
}
