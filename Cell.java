public class Cell {//Object class of cell which store whether it is empty or occupied by which player
    private boolean isEmpty;//make instance variable private to prevent unwanted changes to information
    private int playerNum;
    public Cell(){//constructor of a cell with information initialized
        isEmpty = true;
        playerNum = -1;
    }
    public boolean getEmpty(){//accessor of isEmpty
        return this.isEmpty;
    }
    public int getPlayerNum(){//accessor of playerNum
        return this.playerNum;
    }
    public void setEmpty(boolean empty){//mutator of isEmpty
        this.isEmpty = empty;
    }
    public void setPlayerNum(int num){//mutator of playerNum
        this.playerNum = num;
    }
}
