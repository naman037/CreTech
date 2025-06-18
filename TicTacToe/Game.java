public class Game {
    private char[][] board;
    private Player currentPlayer;
    private Player player1;
    private Player player2;                  // Player is a class and char is class tooo

    public Game(Player p1, Player p2){
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = player1;
        board = new char[3][3];
        initializeBoard();               //setting up board
    }


    // initializing board
    public void initializeBoard(){
        for(int i =0; i<3 ; i++){
        for(int j =0; j<3 ; j++){
        board[i][j] = ' ';
        }
    }
    }

    // checking board if the move is possible or not


    public boolean makeMove(int row, int col){
        if (board[row][col] == ' '){
            board[row][col] = currentPlayer.getSymbol();
            return true;
        }
        return false;
    }
    //        winning 
    
    public boolean checkWin(){
        char s = currentPlayer.getSymbol();                                    //checking rows
        for (int i = 0; i < 3; i++){
           if(board[i][0] == s && board[i][1] == s && board[i][2] == s)
           return true;
        }
           for (int j = 0; j < 3; j++)   {                       //checking columns
             if (board[0][j] == s && board[1][j] == s && board[2][j] == s)
             return true;
           }
             if(board[0][0] == s && board[1][1] == s && board[2][2] == s)
             return true;

             if(board[0][2] == s && board[1][1] == s && board[2][0] == s)
             return true;

             return false;
    }
    
    
    public boolean checkDraw(){
        for(int i=0; i<3; i++)
         for(int j=0; j<3; j++)
          if(board[i][j] == ' ')
          return false;                  // not draw
          return true;                     // draw
    }


    public void switchPlayer(){
        if (currentPlayer == player1) {
    currentPlayer = player2;
} else {
    currentPlayer = player1;
}
    }


    public Player getCurrentPlayer() {
    return currentPlayer;
}



  public char getSymbolAt(int row, int col) {
    return board[row][col];
}

}
