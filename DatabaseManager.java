import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = System.getenv("DB_URL");        // use environment variable
    private static final String USER = System.getenv("DB_USER");      // use environment variable
    private static final String PASSWORD = System.getenv("DB_PASSWORD");  // use environment variable

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
