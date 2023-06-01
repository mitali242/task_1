package mypcakage;
import java.sql.*;
import java.util.Scanner;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UpdateProfileForm extends JFrame{
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField email;
    private JTextField name;
    private JPasswordField currentPassword;
    private JPasswordField newPassword;
    
    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UpdateProfileForm frame = new UpdateProfileForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public UpdateProfileForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JLabel lblNewUserRegister = new JLabel("UpdateProfile Form");
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
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);
        
        name = new JTextField();
        name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        name.setBounds(707, 151, 228, 50);
        contentPane.add(name);
        name.setColumns(10);
        
        JLabel lblPassword = new JLabel("CurrentPassword");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(58, 243, 110, 29);
        contentPane.add(lblPassword);
        
        currentPassword = new JPasswordField();
        currentPassword.setFont(new Font("Tahoma", Font.PLAIN, 32));
        currentPassword.setBounds(214, 235, 228, 50);
        contentPane.add(currentPassword);
        
        JLabel lblnwPassword = new JLabel("NewPassword");
        lblnwPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblnwPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblnwPassword);
        
        newPassword = new JPasswordField();
        newPassword.setFont(new Font("Tahoma", Font.PLAIN, 32));
        newPassword.setBounds(707, 235, 228, 50);
        contentPane.add(newPassword);
        
        
        btnNewButton = new JButton("Update Profile");
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
            	String emailId = email.getText();
            	String currentPass = currentPassword.getText();
            	String newPass = newPassword.getText();

            	if (!validateForm(Name, emailId, currentPass, newPass)) {
            		JOptionPane.showMessageDialog(btnNewButton,"Invalid form data. Please try again.");
                    return;
                }
            	try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/task_1", "root", "")) {
                    // Verify the current password
                    if (!verifyCurrentPassword(connection, emailId, currentPass)) {
                    	JOptionPane.showMessageDialog(btnNewButton,"Invalid current password/emailID. Profile update failed.");
                        return;
                    }

                    // Update the user's profile and password
                    if (updateProfileAndPassword(connection, emailId, newPass)) {
                    	JOptionPane.showMessageDialog(btnNewButton,"\nProfile and Password update successful!");
                    } else {
                    	JOptionPane.showMessageDialog(btnNewButton,"\nProfile and Password update failed.");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(214, 320, 228, 50);
        contentPane.add(btnNewButton);
    }
    private static boolean verifyCurrentPassword(Connection connection, String emailId, String currentPassword) throws SQLException {
        // Create a prepared statement to verify the current password
        String sql = "SELECT * FROM account WHERE email_id = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, emailId);
        statement.setString(2, currentPassword);

        // Execute the query
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next(); // Returns true if a matching record is found
    }
    private static boolean updateProfileAndPassword(Connection connection, String emailId,String newPass) throws SQLException {
        // Create a prepared statement to update the user's profile and password
        String sql = "UPDATE account SET password = ? WHERE email_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newPass);
        statement.setString(2, emailId );

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }
            private static boolean validateForm(String Name, String emailId,String currentPass,String newPass) {
                // Perform your form validation logic here
                // Return true if the form data is valid, false otherwise
                return isValidEmail(emailId) && !currentPass.isEmpty() &&  !newPass.isEmpty();
            }

            private static boolean isValidEmail(String emailId) {
                // Perform email validation logic here
                // You can use regular expressions or any other validation method
                // Return true if the email is valid, false otherwise
                return emailId.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
            }


}
