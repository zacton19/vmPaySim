import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public StudentDTO authenticateStudent(String studentId, String pin) {
        String sql = "SELECT student_id, name, pin_hash, salt, balance FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("pin_hash");
                String salt = rs.getString("salt");

                if (SecurityUtil.verifyPassword(pin, salt, storedHash)) {
                    return new StudentDTO(
                            rs.getString("student_id"),
                            rs.getString("name"),
                            rs.getBigDecimal("balance")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal getBalance(String studentId) {
        String sql = "SELECT balance FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public boolean updateBalance(String studentId, BigDecimal newBalance) {
        String sql = "UPDATE students SET balance = ? WHERE student_id = ? AND balance >= 0";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, newBalance);
            stmt.setString(2, studentId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addWeeklyAllowance(String studentId) {
        String sql = "UPDATE students SET balance = balance + weekly_allowance, " +
                "last_allowance_date = CURDATE() " +
                "WHERE student_id = ? AND " +
                "(last_allowance_date IS NULL OR last_allowance_date < CURDATE() - INTERVAL 7 DAY)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}