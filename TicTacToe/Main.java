import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create players
        System.out.print("Enter name for Player 1 (X): ");
        String name1 = scanner.nextLine();
        Player player1 = new Player(name1, 'X');

        System.out.print("Enter name for Player 2 (O): ");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2, 'O');

        // Create game
        Game game = new Game(player1, player2);

        // Game loop
        while (true) {
            System.out.println("\nCurrent Board:");
            printBoard(game);

            System.out.println(game.getCurrentPlayer().getName() + "'s turn. Enter row and column (0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
                if (game.checkWin()) {
                    printBoard(game);
                    System.out.println(game.getCurrentPlayer().getName() + " wins!");
                    break;
                } else if (game.checkDraw()) {
                    printBoard(game);
                    System.out.println("It's a draw!");
                    break;
                } else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("Invalid move, try again.");
            }
        }

        scanner.close();
    }

    // Helper method to print board
    private static void printBoard(Game game) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + game.getSymbolAt(i, j) + "]");
            }
            System.out.println();
        }
    }
}
