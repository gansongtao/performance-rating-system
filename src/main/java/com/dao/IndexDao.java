package com.dao;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface IndexDao extends Dao {
    boolean addIndex(String first, String second) throws SQLException, NamingException;
    String[] getByFirst(String first) throws SQLException, NamingException;
    boolean deleteFirst(String first) throws SQLException, NamingException;
    boolean deleteSecond(String second) throws SQLException, NamingException;
}
