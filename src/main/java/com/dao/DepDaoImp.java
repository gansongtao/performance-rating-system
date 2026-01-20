package com.dao;

import com.model.Dep;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepDaoImp implements DepDao {
    @Override
    public boolean addDep(Dep dep, int projid) throws SQLException, NamingException {
        String sql = "INSERT INTO divisions VALUES(?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, dep.getId());
        pstmt.setString(2, dep.getName());
        pstmt.setInt(3, projid);
        pstmt.executeUpdate();
        return true;
    }

    @Override
    public boolean updateDep(Dep dep) throws SQLException, NamingException {
        String sql = "UPDATE divisions SET department = ? WHERE depid = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dep.getName());
        pstmt.setInt(2, dep.getId());
        pstmt.executeUpdate();
        return true;
    }

    @Override
    public Dep findById(int id) throws SQLException, NamingException {
        String sql = "SELECT depid, department FROM divisions WHERE depid = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        Dep dep = new Dep();
        dep.setId(id);
        if (rs.next())
            dep.setName(rs.getString(2));
        return dep;
    }

    @Override
    public boolean deleteDep(int id) throws SQLException, NamingException {
        String sql = "DELETE FROM divisions WHERE depid = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        return true;
    }

    @Override
    public List<Integer> findAllUsers(int id) throws SQLException, NamingException {
        String sql = "SELECT DISTINCT userid FROM platforms WHERE projid = (SELECT projid FROM divisions WHERE depid = ?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> users = new ArrayList<>();
        while (rs.next())
            users.add(rs.getInt(1));
        return users;
    }
}
