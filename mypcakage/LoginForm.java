package mypcakage;
import java.sql.*;
import java.util.Scanner;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class LoginForm  extends JFrame{
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField email;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton signupButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginForm frame = new LoginForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public LoginForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        												
        
        JLabel lblNewUserRegister = new JLabel("Login Form");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);
        
        JLabel lblEmailAddress = new JLabel("EmailId");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(58, 152, 99, 43);
        contentPane.add(lblEmailAddress);
        
        email = new JTextField();

        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(214, 151, 228, 50);
        contentPane.add(email);
        email.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(58, 243, 110, 29);
        contentPane.add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(214, 235, 228, 50);
        contentPane.add(passwordField);
        
        btnNewButton = new JButton("Login");
        signupButton = new JButton("Signup");
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String emailId = email.getText();
            	String password = passwordField.getText();
            	if (!validateForm(emailId, password)) {
            		JOptionPane.showMessageDialog(btnNewButton,"Invalid form data. Please try again.");
                    return;
                }
            	try { Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/task_1", "root", "");
                    // Create a prepared statement
                    String sql = "SELECT * FROM account WHERE email_id = ? AND password = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, emailId);
                    statement.setString(2, password);
                    // Execute the query
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                    	JOptionPane.showMessageDialog(btnNewButton,"Login Successful!");
              
                    } else {
                    	JOptionPane.showMessageDialog(btnNewButton,"Login failed. Please check your credentials and try again.");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        signupButton.addActionListener(e -> {
            // Open the signup page
        	UserRegistration signupPage = new UserRegistration();
            signupPage.setVisible(true);
            dispose(); // Close the login page
        });
        
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(214, 320, 228, 50);
        signupButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        signupButton.setBounds(707, 320, 228, 50);
        contentPane.add(btnNewButton);
        contentPane.add(signupButton);
    }

            private static boolean validateForm(String email, String password) {
                // Perform your form validation logic here
                // Return true if the form data is valid, false otherwise
                return isValidEmail(email) && !password.isEmpty();
            }

            private static boolean isValidEmail(String email) {
                // Perform email validation logic here
                // You can use regular expressions or any other validation method   r dtx  bc
                // Return true if the email is valid, false otherwise
                return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
            }

}
