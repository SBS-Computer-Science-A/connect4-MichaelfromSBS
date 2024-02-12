public class Board extends JFrame{
    
  private Cell[][] grid;
  
  public Board(int row, int col){
    
  }

  //Implemented in part 2
  //Fills the cell at (row,col) with a piece by the given player
  //returns true if succeeds, returns false if unable to fill (because of an occupied cell)
  public boolean selectCell(int player, int row, int col){

  }

  //Implemented in part 4
  //chooses the lowest available cell in the selected column and fills that space
  //with a piece from the given player.
  //returns true if succeeds, returns false if unable to fill (because of a full column)
    public boolean selectCell(int player, int col){
  return false;
  }
  
  //returns whether the game is done (someone has won or the board is full) or not
  public boolean over(){

  }  
  
   //returns the number of spaces wide in this board
  public int getWidth(){

  }
  
  //returns the number of spaces high in this board
  public int getHeight(){
    
  }

  //returns the player number of the player with 4-in-a-row.
  //returns -1 if no 4-in-a-row's
  public int fourInARow(){
    } 
}

  //draws the board, similar to how you would usually write your paintComponent method (without the super.paintComponent line)
  public void draw(Graphics g, Color[] colors){ 
    
  }
}
