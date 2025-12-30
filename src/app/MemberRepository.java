
package app;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.*;
import java.awt.*;

public class MemberRepository {
    private  Connection conn;

    public MemberRepository() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:kursova.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public MemberRepository(Connect query){
        conn = query.getConnection();
    }
    
    public ArrayList<Member> loadMembers(String filter) throws SQLException{
        if(conn==null)
            throw new SQLException("no connection to databse");
        ArrayList<Member> list = new ArrayList<Member>();
        String sql = "select memberid, name, phone, "
            + " email, gender, datajoin from members ";
        if(filter.length() > 0){
            sql += " where " + filter;
        }
        try(Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
        ){
            while(rs.next()){
                Member m = new Member(
                    rs.getInt("memberid"), rs.getString("name"),
                    rs.getString("phone"), rs.getString("email"),
                    rs.getString("gender"), rs.getString("datajoin"));
                list.add(m);
            }
        }
        return list;
    }
    
    
    public boolean update(Member member) throws SQLException{
        if(conn==null)
            throw new SQLException("no connection to database");
        int rows = 0;
        String sql = "UPDATE Members SET name = ?, "
          + " phone = ?, email=?, gender=?, datajoin=? "
          + " where memberid = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getPhone());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getGender());
            stmt.setString(5, member.getDateJoined());
            stmt.setInt(6, member.getId());
            rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        }
        return rows > 0;
    }

    public boolean deleteByID(int id) throws SQLException{
        if(conn==null)
            throw new SQLException("no connection to database");
        String sql = "DELETE FROM Members where memberid = ?";
        int rows = 0;
        try(PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setInt(1, id);
            rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        }
        return rows > 0;
    }
    
    public boolean insert(Member m) throws SQLException {
    if (conn == null)
        throw new SQLException("no connection to database");

    int rows = 0;
    
    String sql = "INSERT INTO Members(name, phone, email, gender, datajoin) " +
                 "VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, m.getName());
        stmt.setString(2, m.getPhone());
        stmt.setString(3, m.getEmail());
        stmt.setString(4, m.getGender());
        stmt.setString(5, m.getDateJoined()); 

        rows = stmt.executeUpdate();
        System.out.println("Rows affected (insert): " + rows);
    }

    return rows > 0;
}
}
