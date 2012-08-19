/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameboy;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author YeMaw
 */
public class LevelingHelper {
    
    public static final int PX = Screen.PX;
    public static final int GAME_WIDTH  = Screen.GAME_WIDTH;
    public static final int GAME_HEIGHT = Screen.GAME_HEIGHT;
    
    private static ArrayList<Point> wp = new ArrayList();//wall points
    
    public static ArrayList<Point> getWallPointsType1(int level){
        wp.removeAll(wp);
        
        switch(level){
            default:
            case 1: //no walls in level 1
            break;
                
            case 2:
                _LeftTopLshape();
                _RightTopLshape();
                _LeftBottomLshape();
                _RightBottomLshape();
            break;
                
            case 3:
                wallRectShapeFiller(PX, PX*2, 2, 2);
                wallRectShapeFiller(GAME_WIDTH-(PX*3), PX*2, 2, 2);
                wallRectShapeFiller(PX, GAME_HEIGHT-(PX*4), 2, 2);
                wallRectShapeFiller(GAME_WIDTH-(PX*3), GAME_HEIGHT-(PX*4), 2, 2);
            break;
                
            case 4:
                _LeftTopLshape();
                _RightTopLshape();
                _LeftBottomLshape();
                _RightBottomLshape();
                wallRectShapeFiller((GAME_WIDTH/2)-(PX), (GAME_HEIGHT/2)-(PX), 2, 2);
            break;
                
            case 5:
                _LeftTopLshape();
                _RightTopLshape();
                _LeftBottomLshape();
                _RightBottomLshape();
                wallRectShapeFiller((GAME_WIDTH/2)-(PX), (PX*6), 2, 2);
                wallRectShapeFiller((GAME_WIDTH/2)-(PX), GAME_HEIGHT-(PX*8), 2, 2);
            break;
                
            case 6:
                _LeftTopLshape();
                _RightTopLshape();
                _LeftBottomLshape();
                _RightBottomLshape();
                
                wallRectShapeFiller((GAME_WIDTH/2)-(PX), (PX*5), 2, 3);
                wallRectShapeFiller((GAME_WIDTH/2)-(PX), GAME_HEIGHT-(PX*8), 2, 3);
            break;    
                
            case 7:
                _LeftTopLshape();
                _RightTopLshape();
                _LeftBottomLshape();
                _RightBottomLshape();
                wallRectShapeFiller((GAME_WIDTH/2)-(PX*3), (GAME_HEIGHT/2)-(PX*2), 6, 2); //horizontal
                wallRectShapeFiller((GAME_WIDTH/2)-(PX)  , (GAME_HEIGHT/2)-(PX*4), 2, 6); //vertical
            break;
                
            case 8:
                _LeftTopLshape();
                _RightTopLshape();
                _LeftBottomLshape();
                _RightBottomLshape();
                wallRectShapeFiller(0, (PX*6), 4, 2);
                wallRectShapeFiller(0, (PX*8), 2, 2);
                wallRectShapeFiller(GAME_WIDTH-(PX*2), GAME_HEIGHT-(PX*10), 2, 2);
                wallRectShapeFiller(GAME_WIDTH-(PX*4), GAME_HEIGHT-(PX*8), 4, 2);
            break;
                
            case 9:
                int yl = (GAME_HEIGHT/PX)-6;
                wallRectShapeFiller((GAME_WIDTH/2)-(PX*2), (PX*3), 1, yl); //two verti lines
                wallRectShapeFiller((GAME_WIDTH/2)+(PX), (PX*3), 1, yl);
                
                wallRectShapeFiller((GAME_WIDTH/2)-(PX*4), (PX*3), 2, 2);
                wallRectShapeFiller((GAME_WIDTH/2)-(PX*4), (GAME_HEIGHT/2)-PX, 2, 2);
                wallRectShapeFiller((GAME_WIDTH/2)-(PX*4), GAME_HEIGHT-(PX*5), 2, 2);
                
                wallRectShapeFiller((GAME_WIDTH/2)+(PX*2), (PX*3), 2, 2);
                wallRectShapeFiller((GAME_WIDTH/2)+(PX*2), (GAME_HEIGHT/2)-PX, 2, 2);
                wallRectShapeFiller((GAME_WIDTH/2)+(PX*2), GAME_HEIGHT-(PX*5), 2, 2);
            break;
                
            case 10:
                int yl2 = (GAME_HEIGHT/PX)-6;
                wallRectShapeFiller(PX,(PX*3), 1, yl2); //two verti lines
                wallRectShapeFiller(GAME_WIDTH-(PX*2), (PX*3), 1, yl2);
                
                wallRectShapeFiller(PX*2, PX*3, 4, 2);
                wallRectShapeFiller(GAME_WIDTH-(PX*6), (GAME_HEIGHT/2)-PX, 4, 2);
                wallRectShapeFiller(PX*2, GAME_HEIGHT-(PX*5), 4, 2);
                
            break;
                
        }
        return wp;
    }
    
    public static void wallRectShapeFiller(int x, int y, int xLength, int yLength){
        int oX = x;
        int oY = y;
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                wp.add(new Point(x,y));
                x += PX;
            }
            x = oX;
            y += PX;
        }
    }
    
    private static void _LeftTopLshape(){
        wp.add(new Point(PX,PX*2));
        wp.add(new Point(PX*2,PX*2));
        wp.add(new Point(PX,PX*3));
    }
    
    private static void _RightTopLshape(){
        wp.add(new Point(GAME_WIDTH-(PX*3),PX*2));
        wp.add(new Point(GAME_WIDTH-(PX*2),PX*2));
        wp.add(new Point(GAME_WIDTH-(PX*2),PX*3));
    }
    
    private static void _LeftBottomLshape(){
        wp.add(new Point(PX,GAME_HEIGHT-(PX*3)));
        wp.add(new Point(PX*2,GAME_HEIGHT-(PX*3)));
        wp.add(new Point(PX,GAME_HEIGHT-(PX*4)));
    }
    
    private static void _RightBottomLshape(){
        wp.add(new Point(GAME_WIDTH-(PX*3),GAME_HEIGHT-(PX*3)));
        wp.add(new Point(GAME_WIDTH-(PX*2),GAME_HEIGHT-(PX*3)));
        wp.add(new Point(GAME_WIDTH-(PX*2),GAME_HEIGHT-(PX*4)));
    }
    
//    
//    private static void _LeftTopSquarePX(){
//        _LeftTopLPX();
//        wp.add(new Point(PX*2,PX*3));
//    }
//    
//    private static void _RightTopSquarePX(){
//        _RightTopLPX();
//        wp.add(new Point(GAME_WIDTH-(PX*3),PX*3));
//    }
//    
//    private static void _LeftBottomSquarePX(){
//        _LeftBottomLPX();
//        wp.add(new Point(PX*2,GAME_HEIGHT-(PX*4)));
//    }
//    
//    private static void _RightBottomSquarePX(){
//        _RightBottomLPX();
//        wp.add(new Point(GAME_WIDTH-(PX*3),GAME_HEIGHT-(PX*4)));
//    }
//    
//    
//    //positioning can be wrong if game screen width is changed
//    private static void _MiddleMiddleSquare(){
//        int x = (GAME_WIDTH/2)-PX;
//        int y = (GAME_HEIGHT/2)-PX;
//        squareFiller(x,y,2,2);
//    }
//    
//    private static void _MiddleMiddleRectHorizontal(){
//        int x = (GAME_WIDTH/2)-PX*2;
//        int y = (GAME_HEIGHT/2)-PX;
//        wp.add(new Point(x,y));
//        wp.add(new Point(x+PX,y));
//        wp.add(new Point(x+(PX*2),y));
//        wp.add(new Point(x,y));
//        wp.add(new Point(x+PX,y+PX));
//        wp.add(new Point(x+(PX*2),y+(PX*2)));
//    }
//    
//    private static void _MiddleMiddleRectVertical(){
//        int x = (GAME_WIDTH/2)-PX;
//        int y = (GAME_HEIGHT/2)-PX*2;
//        wp.add(new Point(x,y));
//        wp.add(new Point(x+PX,y));
//        wp.add(new Point(x,y+PX));
//        wp.add(new Point(x+PX,y+PX));
//        wp.add(new Point(x,y+(PX*2)));
//        wp.add(new Point(x+PX,y+(PX*2)));
//    }
//    
//    private static void _MiddleTopmiddleRectHorizontalPX5(){
//        int x = (GAME_WIDTH/2)-(PX*2);
//        int y = PX*5;
//        wp.add(new Point(x,y));
//        wp.add(new Point(x+PX,y));
//        wp.add(new Point(x+(PX*2),y));
//        wp.add(new Point(x+(PX*3),y));
//        wp.add(new Point(x,y+(PX*2)));
//        wp.add(new Point(x+PX,y+(PX*2)));
//        wp.add(new Point(x+(PX*2),y+(PX*2)));
//        wp.add(new Point(x+(PX*3),y+(PX*2)));
//    }
//    
//    private static void _MiddleBottommiddleRectHorizontalPX5(){
//        int x = (GAME_WIDTH/2)-(PX*2);
//        int y = PX*5;
//        wp.add(new Point(x,y));
//        wp.add(new Point(x+PX,y));
//        wp.add(new Point(x+(PX*2),y));
//        wp.add(new Point(x+(PX*3),y));
//        wp.add(new Point(x,y+(PX*2)));
//        wp.add(new Point(x+PX,y+(PX*2)));
//        wp.add(new Point(x+(PX*2),y+(PX*2)));
//        wp.add(new Point(x+(PX*3),y+(PX*2)));
//    }
//    
}
