package com.dao;

import com.model.Proj;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjDaoImp implements ProjDao {
    @Override
    public boolean addProj(Proj proj) throws SQLException, NamingException {
        String sql = "insert into programs values (?, ?, ?)", ans = "";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, proj.getId());
        ps.setString(2, proj.getName());
        ps.setBinaryStream(3, proj.getDoc());
        ps.executeUpdate();
        sql = "insert into platforms values (?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, proj.getId());
        ps.setInt(2, proj.getUserid());
        ps.executeUpdate();
        sql = "select * from divisions where depid = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, proj.getDepid());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            ans = rs.getString("department");
        if (ans != null) {
            sql = "insert into divisions values (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, proj.getDepid());
            ps.setString(2, ans);
            ps.setInt(3, proj.getId());
            ps.executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProj(int projid, int depid) throws SQLException, NamingException {
        String sql = "select * from divisions where depid = ?", ans = "";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, depid);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            ans = rs.getString("department");
        if (ans != null) {
            updater(depid, ans, projid);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProj(int id) throws SQLException, NamingException {
        String sql = "delete from programs where projid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        sql =  "delete from divisions where projid = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }
    public void updater(int a, String b, int c) throws SQLException, NamingException {
        String sql = "update divisions set depid = ?, department = ? where projid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, a);
        ps.setString(2, b);
        ps.setInt(3, c);
        ps.executeUpdate();
    }

    @Override
    public Proj findProjById(int id) throws SQLException, NamingException {
        String sql = "select * from programs where projid = ?";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Proj proj = new Proj();
        if (rs.next()) {
            proj.setId(rs.getInt("projid"));
            proj.setName(rs.getString("project"));
            proj.setDoc(rs.getBlob("document").getBinaryStream());
            proj.setDepid(rs.getInt("depid"));
        }
        return proj;
    }
}
