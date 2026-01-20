package com.dao;

import com.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface UserDao extends Dao {
    boolean addUser(User user) throws SQLException, NamingException;
    String findById(int id) throws SQLException, NamingException;
    boolean updateUser(User user) throws SQLException, NamingException;
    boolean deleteUser(int id) throws SQLException, NamingException;
}
