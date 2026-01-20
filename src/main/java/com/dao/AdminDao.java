package com.dao;

import com.model.Admin;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface AdminDao extends Dao {
    boolean addAdmin(Admin admin) throws SQLException, NamingException;
    Admin findById(int id) throws SQLException, NamingException;
    boolean updateAdmin(Admin admin) throws SQLException, NamingException;
    boolean deleteAdmin(Admin admin) throws SQLException, NamingException;
}
