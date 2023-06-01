package mypcakage;
import java.sql.*;

public class SessionLogout {
    private static final String DB_URL = "jdbc:mysql://localhost/task_1";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    private static final String SESSION_TABLE = "sessions";
    private static final String SESSION_ID_COLUMN = "session_id";
    private static final String SESSION_USER_COLUMN = "user_id";

    int user_id;
    public static void main(String[] args) {
        // Establish database connection
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Simulating user login
        int userId = 123; // Replace with your user ID
        String sessionId = generateSessionId();

        // Store session ID in the database
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " + SESSION_TABLE + " (" + SESSION_ID_COLUMN + ", " + SESSION_USER_COLUMN + ") " +
                            "VALUES (?, ?)"
            );
            insertStatement.setString(1, sessionId);
            insertStatement.setInt(2, userId);
            insertStatement.executeUpdate();
            insertStatement.close();
            System.out.println("User logged in with session ID: " + sessionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Simulating user actions within the session
        // ...

        // User logout
        try {
            // Remove session ID from the database
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM " + SESSION_TABLE + " WHERE " + SESSION_ID_COLUMN + " = ?"
            );
            deleteStatement.setString(1, sessionId);
            deleteStatement.executeUpdate();
            deleteStatement.close();
            System.out.println("User logged out successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close the database connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean userID(Connection connection) throws SQLException {
        // Create a prepared statement to verify the current password
    	int user_id;
        String sql = "SELECT user_id FROM account";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        // Execute the query
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next(); // Returns true if a matching record is found
    }
    private static String generateSessionId() {
        // Generate a unique session ID
        // Implement your own logic here (e.g., using UUID.randomUUID())
        return "session_id_123";
    }
}
