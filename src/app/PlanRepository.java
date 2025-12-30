
package app;


import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class PlanRepository {
    private Connect connect;
    
    public PlanRepository(Connect connect) {
        this.connect = connect;
    }
    // INSERT
    public void insert(Plan p) throws SQLException {
        String sql = "INSERT INTO plans(name, duration, price) VALUES (?,?,?)";
        try (Connection conn = connect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getDuration());
            ps.setDouble(3, p.getPrice());
            ps.executeUpdate();
        }
    }

    // UPDATE
    public void update(Plan p) throws SQLException {
        String sql = "UPDATE plans SET name=?, duration=?, price=? WHERE planid=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getDuration());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getPlanId());
            ps.executeUpdate();
        }
    }

    // DELETE
    public void delete(int planId) throws SQLException {
        String sql = "DELETE FROM plans WHERE planid=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, planId);
            ps.executeUpdate();
        }
    }

    // SELECT * 
    public ArrayList<Plan> findAll() throws SQLException {
        ArrayList<Plan> list = new ArrayList<>();
        String sql = "SELECT * FROM plans";

        try (Connection conn = connect.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Plan p = new Plan(
                        rs.getInt("planid"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getDouble("price")
                );
                list.add(p);
            }
        }
        return list;
    }

    // SELECT WHERE name LIKE ?
    public ArrayList<Plan> findByName(String namePart) throws SQLException {
        ArrayList<Plan> list = new ArrayList<>();
        String sql = "SELECT * FROM plans WHERE name LIKE ?";

        try (Connection conn = connect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + namePart + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Plan p = new Plan(
                        rs.getInt("planid"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getDouble("price")
                );
                list.add(p);
            }
        }
        return list;
    }
    
}
