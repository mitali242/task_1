package mypcakage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MCQQuiz extends JFrame {
	private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining = 60;
    private JLabel questionLabel;
    private JRadioButton option1RadioButton;
    private JRadioButton option2RadioButton;
    private JRadioButton option3RadioButton;
    private JRadioButton option4RadioButton;
    private JButton submitButton;
    private Connection connection;
    private ResultSet resultSet;
    private int currentQuestionIndex = 0;
    private int totalQuestions;
    private int correctAnswersCount = 0;

    public MCQQuiz() {
        // Set up the JFrame
        setTitle("MCQ Quiz");
        setLayout(new BorderLayout());

        JPanel timerPanel = new JPanel();
        timerLabel = new JLabel("Time remaining: " + timeRemaining);
        timerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerPanel.add(timerLabel);
        add(timerPanel, BorderLayout.EAST);
        
        // Create the question panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        questionLabel = new JLabel();
        questionPanel.add(questionLabel);
        add(questionPanel, BorderLayout.WEST);

        // Create the options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        option1RadioButton = new JRadioButton();
        option2RadioButton = new JRadioButton();
        option3RadioButton = new JRadioButton();
        option4RadioButton = new JRadioButton();

        // Group the radio buttons so that only one option can be selected
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(option1RadioButton);
        optionGroup.add(option2RadioButton);
        optionGroup.add(option3RadioButton);
        optionGroup.add(option4RadioButton);

        optionsPanel.add(option1RadioButton);
        optionsPanel.add(option2RadioButton);
        optionsPanel.add(option3RadioButton);
        optionsPanel.add(option4RadioButton);

        add(optionsPanel, BorderLayout.CENTER);

        // Create the submit button
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up the database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/task_1","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retrieve questions from the database
        retrieveQuestionsFromDatabase();

        // Load the first question
        loadQuestion(currentQuestionIndex);

        // Add ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAnswer = getSelectedAnswer();
                String correctAnswer = getCorrectAnswer(currentQuestionIndex);

                // Check if the selected answer matches the correct answer
                if (selectedAnswer != null && selectedAnswer.equals(correctAnswer)) {
                    correctAnswersCount++;
                }
                submitAnswers();
                // Load the next question or finish if all questions have been answered
                if (currentQuestionIndex < totalQuestions - 1) {
                    currentQuestionIndex++;
                    loadQuestion(currentQuestionIndex);
                } else {
                    // Handle finishing the quiz
                    finishQuiz();
                }
            }
        });

        // Set up the JFrame
       

        // Create the timer label
        
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
        pack();
    }
    private void submitAnswers() {
        // Code to submit the answers and perform necessary actions
        JOptionPane.showMessageDialog(this, "Answers submitted!");
        
    }

    public void startTimer() {
        timer.start();
    }

    private void retrieveQuestionsFromDatabase() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM mcq_questions";
            resultSet = statement.executeQuery(query);

            // Get the total number of questions
            resultSet.last();
            totalQuestions = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadQuestion(int questionIndex) {
        try {
            resultSet.absolute(questionIndex + 1);
            String question = resultSet.getString("question");
            String option1 = resultSet.getString("option1");
            String option2 = resultSet.getString("option2");
            String option3 = resultSet.getString("option3");
            String option4 = resultSet.getString("option4");

            questionLabel.setText(question);
            option1RadioButton.setText(option1);
            option2RadioButton.setText(option2);
            option3RadioButton.setText(option3);
            option4RadioButton.setText(option4);

            // Clear the selected answer
            option1RadioButton.setSelected(false);
            option2RadioButton.setSelected(false);
            option3RadioButton.setSelected(false);
            option4RadioButton.setSelected(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getSelectedAnswer() {
        if (option1RadioButton.isSelected()) {
            return option1RadioButton.getText();
        } else if (option2RadioButton.isSelected()) {
            return option2RadioButton.getText();
        } else if (option3RadioButton.isSelected()) {
            return option3RadioButton.getText();
        } else if (option4RadioButton.isSelected()) {
            return option4RadioButton.getText();
        }
        return null;
    }

    private String getCorrectAnswer(int questionIndex) {
        try {
            resultSet.absolute(questionIndex + 1);
            return resultSet.getString("correct_answer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void finishQuiz() {
        double percentage = (double) correctAnswersCount / totalQuestions * 100;

        JOptionPane.showMessageDialog(this, "Quiz completed!\n" +
                "Correct Answers: " + correctAnswersCount + "\n" +
                "Total Questions: " + totalQuestions + "\n" +
                "Percentage: " + percentage + "%");

        // Close the database connection
        try {
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop();
        // Exit the application
        //System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MCQQuiz mcqQuiz = new MCQQuiz();
                mcqQuiz.setVisible(true);
                mcqQuiz.startTimer();
            }
        });
    }
}
