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
    static boolean drawing = false;//whether it's legal to draw a circle based on position
    static Board game;//???
    static int temp = 0;//index of player number array
    static int player_number;
    static int player[];//??? store player numbers
    static int radius;//radius of a circle
    static String colors[];//array to store colors for players
    static int[][] circles; //array to keep track of all circle
    public static void main(String[] args){//testing method

        Scanner user = new Scanner(System.in);
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

        game = new Board(rows,cols);//???


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

    int index = 0;


    public void paintComponent (Graphics g){//drawing horizontal and vertical lines to create grid
        super.paintComponent(g); //usual

        if(game.x_distance>=game.y_distance){
            radius = (game.y_distance-10)/2;
        }else{
            radius = (game.x_distance-10)/2;
        }
        game.draw(g);

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
        row_index= (p.y-50)/game.y_distance;
        col_index=(p.x-20)/game.x_distance;
//        System.out.println(row_index+ " " + col_index);
        if(row_index<rows&&col_index<cols){
           if(game.selectcell(row_index,col_index,player[temp])){
               drawing = true;
               repaint();
           }
        }
    }
}