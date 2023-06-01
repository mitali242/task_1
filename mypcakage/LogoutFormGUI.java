package mypcakage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import mypcakage.SessionUtils; // Replace 'mypackage' with the correct package name for your SessionUtils class

public class LogoutFormGUI extends JFrame {

    public LogoutFormGUI() {
        setTitle("Logout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Are you sure you want to logout?");
        add(label);

        JButton logoutButton = new JButton("Logout");
        add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
     
            	SessionUtils.logout();
                // Show a message dialog indicating successful logout
                JOptionPane.showMessageDialog(null, "Logout Successful");

                // Close the application or navigate to the login page
                //////LoginForm();
                System.exit(0);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	LogoutFormGUI frame =  new LogoutFormGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void LoginForm(){
    	LoginForm frame = new LoginForm();
        frame.setVisible(true);
    }
}
