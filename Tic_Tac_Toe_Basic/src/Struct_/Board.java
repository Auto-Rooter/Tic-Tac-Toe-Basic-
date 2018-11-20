/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct_;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Abd
 */
public class Board {
    
    private Square[][] grid;
    private int dimension;
        

    public Board(int dimension) {
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;
        initGrid();
    }
    

    public void initGrid() {
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                grid[i][j] = new Square(i, j, Player_.PLAYER_EMPTY);
    }


    public GameState isGameOver() {
        if (hasWon(Player_.PLAYER_O))
            return GameState.O_WON;
        if (hasWon(Player_.PLAYER_X))
            return GameState.X_WON;
        if (available_square().isEmpty())
            return GameState.DRAW;
        return GameState.IN_PROGRESS;
    }
    

    boolean hasWon(Player_ player) {
        return checkLines(player) || checkColumns(player) || checkDiags(player);
    }
    

    boolean checkLines(Player_ player) {
        boolean goodLine = false;
        for (int line = 0; line < dimension && !goodLine; line++) {
            goodLine = true;
            for (int i = 0; i < dimension; i++) {
                if (grid[line][i].getPlayer() != player) {
                    goodLine = false;
                    break;
                }
            }
        }
        return goodLine;
    }
    
    

    boolean checkColumns(Player_ player) {
        boolean goodCol = false;
        for (int col = 0; col < dimension && !goodCol; col++) {
            goodCol = true;
            for (int i = 0; i < dimension; i++) {
                if (grid[i][col].getPlayer() != player) {
                    goodCol = false;
                    break;
                }
            }
        }
        return goodCol;
    }


    boolean checkDiags(Player_ player) {
        boolean goodDiag = true;
        for (int i = 0; i < dimension; i++) {
            if (grid[i][i].getPlayer() != player) {
                goodDiag = false;
                break;
            }
        }
        if (goodDiag) return true;

        goodDiag = true;
        for (int i = 0, j = dimension - 1; i < dimension; i++, j--) {
            goodDiag = true;
            if (grid[j][i].getPlayer() != player) {
                goodDiag = false;
                break;
            }
        }
        return goodDiag;
    }
    
    
    ArrayList<Square> available_square() {
        ArrayList<Square> squares = new ArrayList<Square>();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grid[i][j].getPlayer() == Player_.PLAYER_EMPTY)
                    squares.add(grid[i][j]);
            }
        }
        return squares;
    }
        
        

    void Core( Player_ player) {
        GameState gameState = isGameOver();
        for (Square n : available_square()) {
            if (player == Player_.PLAYER_O) {
                move(n.p, Player_.PLAYER_O);
                Core( Player_.PLAYER_X);
            } else if (player == Player_.PLAYER_X) {
                move(n.p, Player_.PLAYER_X);
                Core(Player_.PLAYER_O);
            }
            move(n.p, Player_.PLAYER_EMPTY);
        }

    }


    public Point Move_O() {
        Point O_GPS = null;
        Core(Player_.PLAYER_O);

        ArrayList<Point> bag = new ArrayList<>();
          for (Square n : available_square()) {
            if (n.getPlayer() == Player_.PLAYER_EMPTY) {
                bag.add(n.p); }
            }
        
        Random rn = new Random();
        int range =bag.size();
        int randomNum =  rn.nextInt(range) +0;
        O_GPS = bag.get(randomNum);
        move(O_GPS, Player_.PLAYER_O);
        return O_GPS;
    }

 
    
     public void move(Point p, Player_ player) {
        grid[p.x][p.y].setPlayer(player);
    }

     
     
    public void display() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grid[i][j].getPlayer() == Player_.PLAYER_X)
                    System.out.print("X\t");
                else if (grid[i][j].getPlayer() == Player_.PLAYER_O)
                    System.out.print("O\t");
                else
                    System.out.print(".\t");
            }
            System.out.println();
        }
    }

    public Square[][] getGrid() {
        return grid;
    }
    
}
