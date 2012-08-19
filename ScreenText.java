/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author YeMaw
 */
public class ScreenText extends JPanel{

    private String text = "";
    private int sx, sy;
    
    public ScreenText(int sx, int sy) {
        this.sx = sx;
        this.sy = sy;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(text, sx, sy);
    }
    
    public void write(String text){
        this.text = text;
        repaint();
    }
    
}
