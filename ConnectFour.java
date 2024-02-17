//was on a flight on Friday night so please forgive that I didn't submit on time
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
public class ConnectFour extends JPanel implements MouseListener {//main method that play the game
    public ConnectFour() {
        addMouseListener(this); //always include this in the constructor!
    }
    static int rows;//size of board
    static int cols;
    static int row_index;//where the element representing the place clicked
    static int col_index;
    static int start = 0;//record the first player
    static boolean traditional = false;//determine whether to play  traditional ConnectFour
    static boolean drawing = false;//whether it's legal to draw a circle based on position
    static boolean virtual = false;//determine whether to play with virtual opponent
    static Board game;//create the object of Board
    static int temp = 0;//index of player number array
    static int player_number;//total number of players
    static int player[];// store player numbers
    static int radius;//radius of a circle
    static int traditionalRow;
    static String colors[];//array to store colors for players
    static int[][] circles; //array to keep track of all circle
    public static void main(String[] args){//testing method

        Scanner user = new Scanner(System.in);
        System.out.println("Do you want to play with a virtual component? (yes/no)");
        while(true){
            String temp = user.nextLine();
            if(temp.toLowerCase().equals("yes")){
                virtual = true;
                player_number = 2;
                ConnectFour.temp = (int)(2*Math.random());
                start = ConnectFour.temp;
                break;
            }
            else if(temp.toLowerCase().equals("no")){
                break;
            }
            else{
                System.out.println("Please decide yes or no!");
            }
        }
        System.out.println("How many rows do you want?");
        try{
            rows = user.nextInt();
        }
        catch(Exception e){
            while(!user.hasNextInt()){
                System.out.println("Need a valid int number!");
                user.nextLine();
            }
            rows=user.nextInt();
        }
        System.out.println("How many columns do you want?");
        try{
            cols = user.nextInt();
        }
        catch(Exception e){
            while(!user.hasNextInt()){
                System.out.println("Need a valid int number!");
                user.nextLine();
            }
            cols = user.nextInt();
        }
        circles = new int[rows*cols][2];
        if(!virtual){
            System.out.println("How many players are there?");
            try{
                player_number = user.nextInt();
            }
            catch(Exception e){
                while(!user.hasNextInt()){
                    System.out.println("Need a valid int number!");
                    user.nextLine();
                }
                player_number  = user.nextInt();
            }
            temp = (int)(player_number*Math.random());
            start = temp;
        }
        System.out.println("Do you want to play traditional ConnectFour? (yes/no)");
        while(true){
            String temp = user.nextLine();
            if(temp.toLowerCase().equals("yes")){
                traditional = true;
                break;
            }
            else if(temp.toLowerCase().equals("no")){
                break;
            }
            else{
                System.out.println("Please decide yes or no!");
            }
        }
        player = new int[player_number];//array of player number, player 1 in index 0
        colors = new String[player_number];//array of string of colors, player 1 in index 0
        System.out.println("Please choose colors of players.");
        for(int i = 0;i<player_number;i++){
            System.out.println("Player " + (i+1) + ": ");
            String temp = user.nextLine();
            while(!temp.toLowerCase().equals("black")&&!temp.toLowerCase().equals("red")&&!temp.toLowerCase().equals("gray")&&!temp.toLowerCase().equals("orange")&&!temp.toLowerCase().equals("blue")&&!temp.toLowerCase().equals("green")&&!temp.toLowerCase().equals("yellow")&&!temp.toLowerCase().equals("white")){
                System.out.println("Need a valid color!");
                temp = user.nextLine();
            }
            colors[i]=temp.toLowerCase();
            player[i]=i+1;
        }

        game = new Board(rows,cols);


        JFrame window = new JFrame("Graphics Demo");
        window.setBounds(200, 120, 1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConnectFour b = new ConnectFour();
        b.setBackground(Color.WHITE);  // background color; the default color is light gray
        Container c = window.getContentPane(); //gets the Window
        c.add(b); //adds the panel to the window
        window.setVisible(true);
    }

//creating the drawing panel



    public void paintComponent (Graphics g){//drawing horizontal and vertical lines to create grid
        super.paintComponent(g); //usual

        if(game.x_distance>=game.y_distance){
            radius = (game.y_distance-10)/2;
        }else{
            radius = (game.x_distance-10)/2;
        }
        game.draw(g);

        if(virtual&&temp==1){
            game.draw(g);
        }
    }

    //These 4 are necessary for mouseListener, but you don't need to do anything with them
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }

    //Called whenever mouse is clicked
    public void mouseClicked(MouseEvent e) {//determine situation after mouse is clicked
        drawing = false;
        Point p = e.getPoint(); //get the coordinates of the point where the mouse was clicked
        if(virtual&&temp==1){
            repaint();
        }
        row_index= (p.y-50)/game.y_distance;
        col_index=(p.x-20)/game.x_distance;

        if(game.count==rows*cols){
            drawing = false;
        }
        else if(!traditional) {
            if (row_index < rows && col_index < cols) {
                if (game.selectcell(row_index, col_index, player[temp])) {
                    drawing = true;
                    if (game.playing) {
                        repaint();
                    }
                }
            }
        }
        else{
            if(col_index<cols){
                if(game.traditionalSelectCell(col_index,player[temp])){
                    drawing = true;
                    row_index = traditionalRow;
                }
            }
            if(game.playing&&drawing){
                repaint();
            }
        }
    }
}