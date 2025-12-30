
package app;


import java.sql.*;
import java.util.*;
import java.util.ArrayList;

public class MembershipRepository {
    private Connect connect;
    
    public MembershipRepository(Connect connect) {
        this.connect = connect;
    }
    
    //select all
    
    /*public ArrayList<Membership> findAll() throws SQLException {
        ArrayList<Membership> list=new ArrayList<>();
        String sql= "SELECT * FROM memberships";
        
        try (Connection conn = connect.getConnection();
                Statement st=conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
while (rs.next()){
    Membership m=new Membership(
            rs.getInt("membershipid"),
            rs.getInt("memberid"),
            rs.getInt("planid"),
            rs.getString("startdate"),
            rs.getString("enddate"),
            rs.getString("status"),
            rs.getDouble("paid")
    );
            list.add(m);
        }} return list;
    }*/
    
  /*  public boolean insert(Membership m) throws SQLException {
        if (connect == null)
        throw new SQLException("няма връзка с базата");
        int rows = 0;
        
        String sql = "INSERT INTO memberships(memberid, planid, startdate, enddate, status, paid) VALUES (?,?,?,?,?,?)";
        Connection conn = connect.getConnection();
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getMemberId());
            ps.setInt(2, m.getPlanId());
            ps.setString(3, m.getStartDate());
            ps.setString(4,m.getEndDate());
            ps.setString(5,m.getStatus());
            ps.setDouble(6,m.getPaidAmount());
            //ps.executeUpdate();
            
           rows = ps.executeUpdate();
       System.out.println("Rows affected (insert): " + rows);
        } 
        return rows > 0;
 
}*/
    
    public void insert(Membership m) throws SQLException {
        String sql = "INSERT INTO memberships "+ "(memberid, planid, startdate,enddate, status, paid)" + 
                "VALUES (?,?,?,?,?,?)";
        try (Connection conn=connect.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1, m.getMemberId());
            ps.setInt(2, m.getPlanId());
            ps.setString(3, m.getStartDate());
            ps.setString(4, m.getEndDate());
            ps.setString(5, m.getStatus());
            ps.setDouble(6, m.getPaidAmount());
            ps.executeUpdate();
        }
    }
    
    public void update(Membership m) throws SQLException {
        String sql = "UPDATE memberships SET memberid=?, planid=?, startdate=?, enddate=?, status=?, paid=? WHERE membershipid=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getMemberId());
            ps.setInt(2, m.getPlanId());
            ps.setString(3, m.getStartDate());
            ps.setString(4, m.getEndDate());
            ps.setString(5, m.getStatus());
            ps.setDouble(6, m.getPaidAmount());
            ps.executeUpdate();
        }
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM memberships WHERE membershipid=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
   //select where memberid=?
    
    public ArrayList<Membership> findByMemberId(int memberId) throws SQLException {
        ArrayList<Membership> list= new ArrayList<>();
        String sql="SELECT * FROM memberships WHERE memberid=?";
        
        try (Connection conn = connect.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, memberId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                Membership m= new Membership(
                rs.getInt("membershipid"),
                      rs.getInt("memberid"),
                        rs.getInt("planid"),
                        rs.getString("startdate"),
                        rs.getString("enddate"),
                        rs.getString("status"),
                        rs.getDouble("paid")
                );
                list.add(m);
            }
        }return list;
    }
    
    //select where status like?
    public ArrayList<Membership> findByStatus(String statuss) throws SQLException {
        ArrayList<Membership> list= new ArrayList<>();
        String sql="SELECT * FROM memberships WHERE status LIKE ?";
        
        try (Connection conn = connect.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "%"+ statuss + "%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                Membership m= new Membership(
                rs.getInt("membershipid"),
                      rs.getInt("memberid"),
                        rs.getInt("planid"),
                        rs.getString("startdate"),
                        rs.getString("enddate"),
                        rs.getString("status"),
                        rs.getDouble("paid")
                );
                list.add(m);
            }
        }return list;
    }
    
public String findStatusByMemberId(int memberId) throws SQLException {
    
     String sql = "SELECT status FROM memberships WHERE memberid = ?";

    try (Connection conn = connect.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, memberId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("status");
        }
    }
    return null;
    }
    
   public ArrayList<Membership> loadMemberships(String filter) throws SQLException {

    if (connect == null) {
        throw new SQLException("no connection to database");
    }

    ArrayList<Membership> list = new ArrayList<Membership>();

    String sql = "SELECT membershipid, memberid, planid, startdate, enddate, status, paid "
               + "FROM memberships";

    if (filter.length() > 0) {
        sql += " WHERE " + filter;
    }

    try ( Connection conn = connect.getConnection();
            Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Membership m = new Membership(
                    rs.getInt("membershipid"),
                rs.getInt("memberid"),
                rs.getInt("planid"),
                rs.getString("startdate"),
                rs.getString("enddate"),
                rs.getString("status"),
                rs.getDouble("paid")
            );
            list.add(m);
        }
    }

    return list;
}
}
