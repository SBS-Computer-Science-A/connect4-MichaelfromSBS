import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
public class Board {//object class of board that contains a 2D array of Cell
    static int row;
    static int col;
    int count = 0;
    int index = 0;
    boolean playing = true;
    Cell[][] grid;//2D array of Cell object
    int y_distance = (800-100)/ConnectFour.rows;
    int x_distance = (1000-70)/ConnectFour.cols;
    public Board(int n, int m){//constructor of Board that create grid with n*m size
        row = n;
        col = m;
        grid = new Cell[row][col];
        for(int i = 0;i<row;i++){
            for(int j =0;j<col;j++){
                grid[i][j] = new Cell();
            }
        }
    }
    public boolean selectcell(int n, int m, int playerNum){//method that fill cell with player, if filled return false
        if(grid[n][m].getEmpty()){
            grid[n][m].setPlayerNum(playerNum);
            grid[n][m].setEmpty(false);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean traditionalSelectCell(int columns, int playerNum){//method that fill cell with player, in traditional version
        for(int i = ConnectFour.rows-1;i>=0;i--){
            if(grid[i][columns].getEmpty()){
                grid[i][columns].setPlayerNum(playerNum);
                grid[i][columns].setEmpty(false);
                ConnectFour.traditionalRow = i;
                return true;
            }
        }
        return false;
    }
    public int fourInARow(){//return player number that has four in row, -1 otherwise
        if(verticalSearch()!=-1){
            return verticalSearch();
        }
        if(horizontalSearch()!=-1){
            return horizontalSearch();
        }
        if(diagonalSearch()!=-1){
            return diagonalSearch();
        }
        return -1;
    }
    public int verticalSearch(){//search method of vertical 4 in row, return -1 if not found
        for(int i = 0 ;i<col;i++){
            for(int j = 0;j<row-3;j++){
                if(grid[j][i].getPlayerNum()!=-1&&grid[j][i].getPlayerNum()==grid[j+1][i].getPlayerNum()&&grid[j+1][i].getPlayerNum()==grid[j+2][i].getPlayerNum()&&grid[j+2][i].getPlayerNum()==grid[j+3][j].getPlayerNum()){
                    return grid[j][i].getPlayerNum();
                }
            }
        }
        return -1;
    }
    public int horizontalSearch(){//search method of horizontal 4 in row, return -1 if not found
        for(int i = 0;i<row;i++){
            for(int j = 0;j<col-3;j++){
                if(grid[i][j].getPlayerNum()!=-1&&grid[i][j].getPlayerNum()==grid[i][j+1].getPlayerNum()&&grid[i][j+1].getPlayerNum()==grid[i][j+2].getPlayerNum()&&grid[i][j+2].getPlayerNum()==grid[i][j+3].getPlayerNum()){
                    return grid[i][j].getPlayerNum();
                }
            }
        }
        return -1;
    }
    public int diagonalSearch(){//search method of diagonal 4 in row, return -1 if not found
        if(row<4||col<4){
            return -1;
        }
        for(int i = 0; i < row-3; i++){
            for(int j = 0; j<col-3; j++) {
                if (grid[i][j].getPlayerNum()!=-1&& grid[i][j].getPlayerNum() == grid[i + 1][j + 1].getPlayerNum() && grid[i + 1][j + 1].getPlayerNum() == grid[i + 2][j + 2].getPlayerNum() && grid[i + 2][j + 2].getPlayerNum() == grid[i + 3][j + 3].getPlayerNum()) {
                    return grid[i][j].getPlayerNum();
                }
            }
        }
        for(int i = 0;i<row-3;i++){
            for(int j = col-1;j>=3;j--){
                if(grid[i][j].getPlayerNum()!=-1&& grid[i][j].getPlayerNum()==grid[i+1][j-1].getPlayerNum()&&grid[i+1][j-1].getPlayerNum()==grid[i+2][j-2].getPlayerNum()&&grid[i+2][j-2].getPlayerNum()==grid[i+3][j-3].getPlayerNum()){
                    return grid[i][j].getPlayerNum();
                }
            }
        }
        return -1;
    }
    public void draw(Graphics g){//drawing methods of constructor to draw grid
        int y = ConnectFour.row_index;
        int x = ConnectFour.col_index;
        g.setColor(Color.BLACK);
            for (int i = 0; i < ConnectFour.rows; i++) {
                g.drawLine(20, 50 + i * y_distance, 950, 50 + i * y_distance);
            }
            g.drawLine(20,750,950,750);
            for (int i = 0; i < ConnectFour.cols; i++) {
                g.drawLine(20 + i * x_distance, 50, 20 + i * x_distance, 750);
            }
            g.drawLine(950,50,950,750);

        if(!ConnectFour.drawing){
            return;
        }

        ConnectFour.game.count=0;
        if(ConnectFour.virtual&&ConnectFour.temp==1){//move by virtual opponent
            if(ConnectFour.game.count<ConnectFour.rows*ConnectFour.cols){
                for(int column = 0;column<ConnectFour.cols;column++){
                    boolean found = false;
                    for(int i = ConnectFour.rows-1;i>=0;i--){
                        if(selectcell(i,column,2)){
                            ConnectFour.circles[index][0]=20+5+column*x_distance;
                            ConnectFour.circles[index][1]=50+5+i*y_distance;
                            ConnectFour.drawing = true;
                            found = true;
                            break;
                        }
                    }
                    if(found){
                        break;
                    }
                }
            }
        }
        if(!ConnectFour.drawing){
            return;
        }
        if(!ConnectFour.virtual||ConnectFour.temp!=1){//move by player
            ConnectFour.circles[index][0] = 20+5+x*x_distance;
            ConnectFour.circles[index][1] = 50+5+y*y_distance;
        }

        for(int i = 0;i<=index;i++){
            convertStringToColor(ConnectFour.colors[(ConnectFour.start+i)%ConnectFour.player_number],g);
            g.fillOval(ConnectFour.circles[i][0],ConnectFour.circles[i][1], ConnectFour.radius*2, ConnectFour.radius*2);
            ConnectFour.game.count++;
        }
        ConnectFour.temp=(ConnectFour.temp+1)%ConnectFour.player_number;
        index++;
        if(fourInARow()!=-1){
            Font f1 = new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 30);
            g.setFont(f1);
            g.setColor(Color.BLACK);
            g.drawString("Congratulation! Player " + fourInARow() + " wins!", 270,400);
            for(int i = 0;i<ConnectFour.rows;i++){
                for(int j = 0;j<ConnectFour.cols;j++){
                    System.out.print(grid[i][j].getPlayerNum() + " ");
                }
                System.out.println();
            }
            playing = false;
        }
    }
    public void convertStringToColor(String str, Graphics g){//convert string of color names to color
        if(str.equals("red")){
            g.setColor(Color.RED);
        }
        else if(str.equals("black")){
            g.setColor(Color.BLACK);
        }
        else if(str.equals("blue")){
            g.setColor(Color.BLUE);
        }
        else if(str.equals("orange")){
            g.setColor(Color.ORANGE);
        }
        else if(str.equals("yellow")){
            g.setColor(Color.YELLOW);
        }
        else if(str.equals("gray")){
            g.setColor(Color.GRAY);
        }
        else if(str.equals("white")){
            g.setColor(Color.WHITE);
        }
        else if(str.equals("green")){
            g.setColor(Color.GREEN);
        }
    }
}