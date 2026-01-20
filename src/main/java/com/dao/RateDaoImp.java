package com.dao;

import com.model.Rate;
import com.model.User;

import javax.naming.NamingException;
import java.sql.*;

public class RateDaoImp implements RateDao {
    @Override
    public boolean addRate(Rate rate) throws SQLException, NamingException {
        String sql = "update programs set start = ?, ending = ? where projid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, rate.getStart());
        ps.setDate(2, rate.getEnding());
        ps.setInt(3, rate.getId());
        ps.executeUpdate();
        sql = "update users set secondhand = ? where userid = (select userid from platforms where projid = ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, rate.getSeconds()[0]);
        ps.setInt(2, rate.getId());
        ps.executeUpdate();
        int[] ids = new int[100];
        int i = 0;
        String[] names = new String[100];
        sql = "select userid, username from users";
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ids[i] = rs.getInt(1);
            names[i] = rs.getString(2);
            i++;
        }
        i = 0;
        for (int n : ids) {
            sql = "insert into users values (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, n);
            ps.setString(2, names[i]);
            i++;
            ps.setString(3, rate.getSeconds()[i]);
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean updateRate(int projid, String oldSecond, String second, Date oldStart, Date start, Date oldEnding, Date ending) throws SQLException, NamingException {
        if (second != null) {
            String sql = "update users set secondhand = ? where secondhand = ? and userid = (select userid from platforms where projid = ?)";
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, second);
            ps.setString(2, oldSecond);
            ps.setInt(3, projid);
            ps.executeUpdate();
        }
        if (start != null) {
            String sql = "update programs set start = ? where start = ? and projid = ?";
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, start);
            ps.setDate(2, oldStart);
            ps.setInt(3, projid);
            ps.executeUpdate();
        }
        if (ending != null) {
            String sql = "update programs set ending = ? where ending = ? and projid = ?";
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, ending);
            ps.setDate(2, oldEnding);
            ps.setInt(3, projid);
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteRate(int id) throws SQLException, NamingException {
        String sql = "update programs set start = null, ending = null where projid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        sql = "select distinct userid, username from users where userid = (select userid from platforms where projid = ?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int i = 0;
        int[] ids = new int[100];
        String[] names = new String[100];
        while (rs.next()) {
            ids[i] = rs.getInt(1);
            names[i] = rs.getString(2);
            i++;
        }
        i = 0;
        sql = "delete from users where userid = (select userid from platforms where projid = ?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        UserDaoImp userDaoImp = new UserDaoImp();
        for (int n : ids) {
            User user = new User();
            user.setId(n);
            user.setName(names[i]);
            userDaoImp.addUser(user);
            i++;
        }
        return true;
    }

    @Override
    public String[] findRateById(int id) throws SQLException, NamingException {
        String sql = "select distinct secondhand from users where userid = (select userid from platforms where projid = ?)";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        String[] names = new String[100];
        int i = 0;
        while (rs.next())
            names[i] = rs.getString("secondhand");
        return names;
    }
}
