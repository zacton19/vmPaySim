import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public ProductDTO getProduct(String productId) {
        String sql = "SELECT product_id, name, price, category FROM products WHERE product_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ProductDTO(
                        rs.getString("product_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getString("category")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> getAvailableProducts(String machineId) {
        String sql = "SELECT p.product_id, p.name, p.price, p.category, i.quantity " +
                "FROM products p " +
                "JOIN inventory i ON p.product_id = i.product_id " +
                "WHERE i.machine_id = ? AND i.quantity > 0";

        List<ProductDTO> products = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, machineId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new ProductDTO(
                        rs.getString("product_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean decrementInventory(String machineId, String productId) {
        String sql = "UPDATE inventory SET quantity = quantity - 1 " +
                "WHERE machine_id = ? AND product_id = ? AND quantity > 0";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, machineId);
            stmt.setString(2, productId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}