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
        String sql = "select email, password from adminLoginInfo where email = ? and password = ?";
        try{
            Admin authroity = this.jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new RowMapper<Admin>() {
                @Override
                public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Admin a = new Admin();
                    a.setEmail(rs.getString("email"));
                    a.setPassword(rs.getString("password"));
                    return a;
                }
            });
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }
}
