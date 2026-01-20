package com.dao;

import com.model.Proj;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface ProjDao extends Dao {
    boolean addProj(Proj proj) throws SQLException, NamingException;
    Proj findProjById(int id) throws SQLException, NamingException;
    boolean updateProj(int projid, int depid) throws SQLException, NamingException;
    boolean deleteProj(int id) throws SQLException, NamingException;
}
