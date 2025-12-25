import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionService {
    private StudentDAO studentDAO;
    private ProductDAO productDAO;

    public TransactionService() {
        this.studentDAO = new StudentDAO();
        this.productDAO = new ProductDAO();
    }

    public synchronized Response processPurchase(String studentId, String machineId,
                                                 String productId) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            BigDecimal balance = studentDAO.getBalance(studentId);
            ProductDTO product = productDAO.getProduct(productId);

            if (product == null) {
                conn.rollback();
                return new Response(false, "Product not found");
            }

            if (balance.compareTo(product.getPrice()) < 0) {
                conn.rollback();
                return new Response(false, "Insufficient funds");
            }

            if (!productDAO.decrementInventory(machineId, productId)) {
                conn.rollback();
                return new Response(false, "Product out of stock");
            }

            BigDecimal newBalance = balance.subtract(product.getPrice());
            if (!studentDAO.updateBalance(studentId, newBalance)) {
                conn.rollback();
                return new Response(false, "Failed to update balance");
            }

            String sql = "INSERT INTO transactions (student_id, machine_id, product_id, amount, status) " +
                    "VALUES (?, ?, ?, ?, 'COMPLETED')";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, studentId);
                stmt.setString(2, machineId);
                stmt.setString(3, productId);
                stmt.setBigDecimal(4, product.getPrice());
                stmt.executeUpdate();
            }

            conn.commit();
            Response response = new Response(true, "Purchase successful");
            response.setData("newBalance", newBalance);
            response.setData("productName", product.getName());
            return response;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return new Response(false, "Transaction failed: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}