import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int jackpotLocation;
    private int livesRemaining;

    JPanel p = new JPanel();
    JButton b1 = new JButton("Box 1");
    JButton b2 = new JButton("Box 2");
    JButton b3 = new JButton("Box 3");
    JLabel l1 = new JLabel("Guess where the Jackpot is!");
    JLabel l2 = new JLabel("Find the Jackpot! You have 3 lives.");
    JLabel statusLabel = new JLabel("Lives Remaining: 3");
    JButton playAgainButton = new JButton("Play Again");

    public GuessingGame() {
        setTitle("Jackpot Guessing Game");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        p.setBackground(Color.PINK);

        p.setLayout(null);
        p.setBounds(0, 0, 600, 500);
        add(p);

        setComponents();
        startGame();

        setVisible(true);
    }

    public void setComponents() {
        p.removeAll();

        b1.setBounds(0, 20, 200, 400);
        b1.setBackground(Color.CYAN);
        p.add(b1);

        b2.setBounds(200, 20, 200, 400);
        b2.setBackground(Color.GREEN);
        p.add(b2);

        b3.setBounds(400, 20, 200, 400);
        b3.setBackground(Color.YELLOW);
        p.add(b3);

        l1.setBounds(10, 0, 400, 20);
        p.add(l1);

        l2.setBounds(200, 420, 400, 40);
        p.add(l2);

        statusLabel.setBounds(200, 440, 300, 30);
        p.add(statusLabel);

        // remove any old ActionListeners to avoid duplicate handling
        for (ActionListener al : b1.getActionListeners()) {
            b1.removeActionListener(al);
        }
        for (ActionListener al : b2.getActionListeners()) {
            b2.removeActionListener(al);
        }
        for (ActionListener al : b3.getActionListeners()) {
            b3.removeActionListener(al);
        }

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess(0);
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess(1);
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess(2);
            }
        });
        //recalculates layout
        p.revalidate();
        //visually redraws
        p.repaint();
    }

    public void startGame() {
        livesRemaining = 3;
        Random rand = new Random();
        jackpotLocation = rand.nextInt(3);
        statusLabel.setText("Lives Remaining: " + livesRemaining);
    }

    public void handleGuess(int guess) {
        if (guess == jackpotLocation) {
            showWinScreen();
        } else {
            livesRemaining = livesRemaining - 1;
            if (livesRemaining == 0) {
                showGameOverScreen();
            } else {
                statusLabel.setText("Wrong! Jackpot shuffled.");
                Random rand = new Random();
                jackpotLocation = rand.nextInt(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                statusLabel.setText("Wrong Guess Lives Remaining: " + livesRemaining);
            }
        }
    }

    private void showWinScreen() {
        p.removeAll();
        p.setBackground(Color.GREEN);
        p.setLayout(null);

        JLabel winLabel = new JLabel("YOU WIN!");
        winLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winLabel.setBounds(200, 150, 300, 50);
        p.add(winLabel);

        JButton restart = new JButton("Play Again");
        restart.setBounds(220, 250, 150, 40);
        restart.setFont(new Font("Arial", Font.BOLD, 16));
        p.add(restart);

        restart.addActionListener(e -> {
            p.removeAll();
            p.setBackground(Color.PINK);
            setComponents();
            startGame();
            repaint();
            revalidate();
        });

        repaint();
        revalidate();
    }

    private void showGameOverScreen() {
        p.removeAll();
        p.setBackground(Color.RED);
        p.setLayout(null);

        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setBounds(190, 150, 300, 50);
        p.add(gameOverLabel);

        JLabel winLabel = new JLabel("YOU Lose!");
        winLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winLabel.setBounds(220, 250, 300, 50);
        p.add(winLabel);

        JButton restart = new JButton("Try Again");
        restart.setBounds(220, 350, 150, 40);
        restart.setFont(new Font("Arial", Font.BOLD, 16));
        p.add(restart);

        restart.addActionListener(e -> {
            p.removeAll();
            p.setBackground(Color.PINK);
            setComponents();
            startGame();
            repaint();
            revalidate();
        });

        repaint();
        revalidate();
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}
