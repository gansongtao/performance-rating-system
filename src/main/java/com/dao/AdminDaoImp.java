package com.dao;

import com.model.Admin;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImp implements AdminDao {
    @Override
    public boolean addAdmin(Admin admin) throws SQLException, NamingException {
        String sql = "INSERT INTO admins VALUES(?,?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,admin.getId());
        pstmt.setString(2,admin.getName());
        pstmt.executeUpdate();
        return true;
    }

    @Override
    public Admin findById(int id) throws SQLException, NamingException {
        String sql = "SELECT * FROM admins WHERE managerid = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        Admin admin = new Admin();
        if (rs.next()) {
            admin.setId(rs.getInt("managerid"));
            admin.setName(rs.getString("managername"));
        }
        return admin;
    }

    @Override
    public boolean updateAdmin(Admin admin) throws SQLException, NamingException {
        String sql = "UPDATE admins SET managername = ? WHERE managerid = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(2,admin.getId());
        pstmt.setString(1,admin.getName());
        pstmt.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteAdmin(Admin admin) throws SQLException, NamingException {
        String sql = "DELETE FROM admins WHERE managerid = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,admin.getId());
        pstmt.executeUpdate();
        return true;
    }
}
