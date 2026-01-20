package com.dao;

import com.model.Dep;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface DepDao extends Dao {
    boolean addDep(Dep dep, int projid) throws SQLException, NamingException;
    Dep findById(int id) throws SQLException, NamingException;
    boolean updateDep(Dep dep) throws SQLException, NamingException;
    boolean deleteDep(int id) throws SQLException, NamingException;
    List<Integer> findAllUsers(int id) throws SQLException, NamingException;
}
