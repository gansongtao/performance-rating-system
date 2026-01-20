package com.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface Dao {
    static DataSource getDataSource() throws NamingException {
        InitialContext initialContext = new InitialContext();
        return (DataSource) initialContext.lookup("java:comp/env/jdbc/openGauss");
    }
    default Connection getConnection() throws SQLException, NamingException {
        DataSource dataSource = getDataSource();
        return dataSource.getConnection();
    }
}
