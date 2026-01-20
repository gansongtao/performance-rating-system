package com.dao;

import com.model.Rate;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.SQLException;

public interface RateDao extends Dao {
    boolean addRate(Rate rate) throws SQLException, NamingException;
    String[] findRateById(int id) throws SQLException, NamingException;
    boolean updateRate(int projid, String oldSecond, String second, Date oldStart, Date start, Date oldEnding, Date ending) throws SQLException, NamingException;
    boolean deleteRate(int id) throws SQLException, NamingException;
}
