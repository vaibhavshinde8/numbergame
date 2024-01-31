import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame extends JFrame {
    private Random random;
    private int generatedNum;
    private int maxAttempts;
    private int attempts;
    private int score;

    private JTextField guessField;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public NumberGame() {
        random = new Random();
        maxAttempts = 5;
        score = 0;

        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        setPreferredSize(new Dimension(300, 150)); 

        initializeComponents();
        setupGame();

        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void initializeComponents() {
        JLabel titleLabel = new JLabel("Number Game:");
        add(titleLabel);

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:");
        add(instructionLabel);

        guessField = new JTextField();
        add(guessField);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(80, 30)); 
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
        add(submitButton);

        resultLabel = new JLabel();
        add(resultLabel);

        attemptsLabel = new JLabel();
        add(attemptsLabel);

        scoreLabel = new JLabel();
        add(scoreLabel);

        JButton playAgainButton = new JButton("Play");
        playAgainButton.setPreferredSize(new Dimension(80, 30)); 
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupGame();
            }
        });
        add(playAgainButton);
    }

    private void setupGame() {
        generatedNum = random.nextInt(100) + 1;
        attempts = 0;
        guessField.setText("");
        resultLabel.setText("");
        attemptsLabel.setText("");
        scoreLabel.setText("Score: " + score);
    }

    private void processGuess() {
        if (attempts < maxAttempts) {
            try {
                int guessNum = Integer.parseInt(guessField.getText());
                attempts++;

                if (guessNum < generatedNum) {
                    resultLabel.setText("Too low! Try again.");
                } else if (guessNum > generatedNum) {
                    resultLabel.setText("Too high! Try again.");
                } else {
                    resultLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                    score++;
                }

                attemptsLabel.setText("Attempts: " + attempts);
                scoreLabel.setText("Score: " + score);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a number.");
            }
        } else {
            resultLabel.setText("Game over! The correct number was: " + generatedNum);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGame();
            }
        });
    }
}
