import java.awt.*;
import javax.swing.*;

public class TicTacToeGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private Game game;

    public TicTacToeGUI() {
        frame = new JFrame("Tic Tac Toe");
        buttons = new JButton[3][3];

        // Setup players
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');
        game = new Game(player1, player2);

        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = i;
                int col = j;

                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));

                buttons[i][j].addActionListener(e -> handleClick(row, col));
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private void handleClick(int row, int col) {
        if (buttons[row][col].getText().equals("") && game.makeMove(row, col)) {
            buttons[row][col].setText(String.valueOf(game.getCurrentPlayer().getSymbol()));

            if (game.checkWin()) {
                JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getName() + " wins!");
                frame.dispose(); // close window
            } else if (game.checkDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                frame.dispose();
            } else {
                game.switchPlayer();
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
