package com.dao;

import com.model.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    @Override
    public boolean addUser(User user) throws SQLException, NamingException {
        String sql = "insert into users values (?, ?)";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, user.getId());
        ps.setString(2, user.getName());
        ps.executeUpdate();
        return true;
    }

    @Override
    public boolean updateUser(User user) throws SQLException, NamingException {
        String sql = "update users set username = ? where userid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setInt(2, user.getId());
        return true;
    }

    @Override
    public String findById(int id) throws SQLException, NamingException {
        String sql = "select userid, username from users where userid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getString(2);
        return null;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException, NamingException {
        String sql = "delete from users where userid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        sql = "delete from platforms where userid = ?";
        conn = getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }
}
