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

    public Admin getAdminByEmail(String Email){
        Admin res;
        String sql = "SELECT ID, Name, Address, PhoneNumber, DateOfBirth, Email, library_Id FROM staff WHERE Email = ?";
        try{
            res = this.jdbcTemplate.queryForObject(sql, new RowMapper<Admin>(){
                @Override
                public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Admin a = new Admin(rs.getInt("ID"), rs.getString("Name"), rs.getString("Address"), rs.getString("PhoneNumber"), rs.getString("DateOfBirth")
                    , rs.getString("Email"), "",rs.getInt("Library_ID"));
                    return a;
                }
            }, Email);
        }catch(EmptyResultDataAccessException e){
            res = new Admin();
            return res;
        }
        return res;
    }
}
