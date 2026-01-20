package com.dao;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexDaoImp implements IndexDao {
    @Override
    public boolean addIndex(String first, String second) throws SQLException, NamingException {
        String sql =  "INSERT INTO hands VALUES(?, ?)";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, first);
        ps.setString(2, second);
        ps.execute();
        return true;
    }

    @Override
    public String[] getByFirst(String first) throws SQLException, NamingException {
        String sql  = "SELECT * FROM hands WHERE firsthand = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, first);
        ResultSet rs = ps.executeQuery();
        String[] res = new String[100];
        int i = 0;
        while (rs.next())
            res[i++] = rs.getString(1);
        return res;
    }

    @Override
    public boolean deleteFirst(String first) throws SQLException, NamingException {
        String sql = "DELETE FROM hands WHERE firsthand = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, first);
        ps.execute();
        return true;
    }

    @Override
    public boolean deleteSecond(String second) throws SQLException, NamingException {
        String sql = "DELETE FROM hands WHERE secondhand = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, second);
        ps.execute();
        return true;
    }
}
