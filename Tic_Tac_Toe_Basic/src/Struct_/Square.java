/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct_;
import java.awt.*;
/**
 *
 * @author Abd
 */
public class Square {
     public Point p;
    private Player_ player;

    public Square(int x, int y, Player_ player) {
        this.p = new Point(x,y);
        this.player = player;
    }

    public Square(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Player_ getPlayer() {
        return player;
    }

    public void setPlayer(Player_ player) {
        this.player = player;
    }
}
