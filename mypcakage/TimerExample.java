package mypcakage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerExample extends JFrame {
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining = 60; // Time in seconds
    private JButton submitButton;

    public TimerExample() {
        // Set up the JFrame
        setTitle("Timer Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the timer label
        timerLabel = new JLabel("Time remaining: " + timeRemaining);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timerLabel, BorderLayout.CENTER);

        // Create the submit button
        submitButton = new JButton("Submit");
        add(submitButton, BorderLayout.SOUTH);

        // Create the timer
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    timerLabel.setText("Time remaining: " + timeRemaining);
                } else {
                    timer.stop();
                    submitAnswers();
                }
            }
        });

        // Add ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                submitAnswers();
            }
        });

        pack();
    }

    private void submitAnswers() {
        // Code to submit the answers and perform necessary actions
        JOptionPane.showMessageDialog(this, "Answers submitted!");
        System.exit(0);
    }

    public void startTimer() {
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TimerExample timerExample = new TimerExample();
                timerExample.setVisible(true);
                timerExample.startTimer();
            }
        });
    }
}

