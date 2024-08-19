import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {

    private JButton[][] buttons;
    private boolean xTurn;

    public TicTacToe() {
        buttons = new JButton[3][3];
        xTurn = true; // X starts the game

        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        ActionListener buttonListener = new ButtonClickListener();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setPreferredSize(new Dimension(100, 100));
                buttons[i][j].addActionListener(buttonListener);
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                button.setText(xTurn ? "X" : "O");
                xTurn = !xTurn;
                checkForWinner();
            }
        }
    }

    private void checkForWinner() {
        String winner = null;

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                winner = buttons[i][0].getText();
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals("")) {
                winner = buttons[0][i].getText();
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            winner = buttons[0][0].getText();
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            winner = buttons[0][2].getText();
        }

        if (winner != null) {
            JOptionPane.showMessageDialog(this, winner + " wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true; // X starts again
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
