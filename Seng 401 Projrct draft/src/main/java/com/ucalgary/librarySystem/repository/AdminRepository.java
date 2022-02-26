package com.ucalgary.librarySystem.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ucalgary.librarySystem.model.Admin;

@Repository
public class AdminRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isAdmin(String username, String password) {
        String sql = "SELECT COUNT(*) FROM staff WHERE Email = ? AND Password = ?";
        int count;
        try{
            count = this.jdbcTemplate.queryForObject(sql, Integer.class, username, password);
            if (count > 0) return true;
            return false;
        }catch (EmptyResultDataAccessException e){
        return false;
        }
    }
}
