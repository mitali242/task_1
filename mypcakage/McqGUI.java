package mypcakage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class McqGUI extends JFrame {
    private JLabel questionLabel;
    private JTextField questionTextField;
    private JTextField option1TextField;
    private JTextField option2TextField;
    private JTextField option3TextField;
    private JTextField option4TextField;
    private JTextField correctTextField;
    private JButton submitButton;
    private Connection connection;

    public McqGUI() {
        // Set up the JFrame
        setTitle("Multiple Choice Questions");
        setLayout(new BorderLayout());

        // Create the question panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(2, 1));
        questionLabel = new JLabel("Question:");
        questionTextField = new JTextField();
        questionPanel.add(questionLabel);
        questionPanel.add(questionTextField);
        add(questionPanel, BorderLayout.NORTH);

        // Create the options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(5, 2));
        option1TextField = new JTextField();
        option2TextField = new JTextField();
        option3TextField = new JTextField();
        option4TextField = new JTextField();
        correctTextField = new JTextField();
        optionsPanel.add(new JLabel("Option 1:"));
        optionsPanel.add(option1TextField);
        optionsPanel.add(new JLabel("Option 2:"));
        optionsPanel.add(option2TextField);
        optionsPanel.add(new JLabel("Option 3:"));
        optionsPanel.add(option3TextField);
        optionsPanel.add(new JLabel("Option 4:"));
        optionsPanel.add(option4TextField);
        optionsPanel.add(new JLabel("Correct Answer:"));
        optionsPanel.add(correctTextField);
        add(optionsPanel, BorderLayout.CENTER);

        // Create the submit button
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up the database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/task_1", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String question = questionTextField.getText();
                String option1 = option1TextField.getText();
                String option2 = option2TextField.getText();
                String option3 = option3TextField.getText();
                String option4 = option4TextField.getText();
                String correct_answer = correctTextField.getText();

                // Store the question and options into the database
                storeQuestionInDatabase(question, option1, option2, option3, option4,correct_answer);

                // Clear the input fields
                questionTextField.setText("");
                option1TextField.setText("");
                option2TextField.setText("");
                option3TextField.setText("");
                option4TextField.setText("");
                correctTextField.setText("");
                JOptionPane.showMessageDialog(McqGUI.this, "Question submitted!");
            }
        });

        pack();
    }

    private void storeQuestionInDatabase(String question, String option1, String option2, String option3, String option4,String correct_answer) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO mcq_questions (question, option1, option2, option3, option4,correct_answer) VALUES (?, ?, ?, ?, ?,?)");
            statement.setString(1, question);
            statement.setString(2, option1);
            statement.setString(3, option2);
            statement.setString(4, option3);
            statement.setString(5, option4);
            statement.setString(6, correct_answer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                McqGUI mcqGUI = new McqGUI();
                mcqGUI.setVisible(true);
                
            }
        });
    }
}

