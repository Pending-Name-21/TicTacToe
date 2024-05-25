package com.tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true; // true for X's turn, false for O's turn
    private ImageIcon boardIcon;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load and resize the board image
        ImageIcon originalBoardIcon = new ImageIcon("app/src/main/java/com/tic_tac_toe/Images/Board.png");
        Image boardImage = originalBoardIcon.getImage();
        Image resizedBoardImage = boardImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        boardIcon = new ImageIcon(resizedBoardImage);

        // Create a layered pane for background and buttons
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 400));

        // Create the background label
        JLabel backgroundLabel = new JLabel(boardIcon);
        backgroundLabel.setBounds(0, 0, 400, 400);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // Set layout and add buttons
        JPanel panel = new JPanel(new GridLayout(3, 3));
        panel.setOpaque(false);
        panel.setBounds(0, 0, 400, 400);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setBorderPainted(false);
                buttons[i][j].setOpaque(false);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }
        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);

        setContentPane(layeredPane);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.getText().equals("")) {
            if (playerX) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.RED); // Optional: set X color
            } else {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.BLUE); // Optional: set O color
            }
            playerX = !playerX;
            checkForWinner();
        }
    }

    private void checkForWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2]) ||
                    checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) {
                showWinner();
                return;
            }
        }
        // Check diagonals
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {
            showWinner();
        }
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText()) &&
                !b1.getText().equals("");
    }

    private void showWinner() {
        String winner = playerX ? "O" : "X";
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerX = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }
}
