package com.ucalgary.careeradvice.repository;


import com.ucalgary.careeradvice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Exposes APIs that provide direct access to the User table in the database.
 */
@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // https://mkyong.com/spring-boot/spring-boot-jdbc-examples/

    public User getUser(String username, String password) {
        // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
        return jdbcTemplate.query(
                "SELECT * FROM User WHERE Name=? AND Password=?",
                new Object[]{username, password},
                new int[]{Types.VARCHAR, Types.VARCHAR},
                UserRepository::mapFromResultSet // pass method reference (not spring-boot specific, but with newer versions of java)
        );
    }

    private static User mapFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("ID"),
                rs.getString("Name"),
                rs.getString("Address"),
                rs.getString("PhoneNumber"),
                rs.getString("DateOfBirth"),
                rs.getString("Email"),
                rs.getString("Password"),
                rs.getDouble("Balance")
        );
    }

    // public boolean isUser(String name, String password) {
    //     String sql = "SELECT Email, Password FROM user WHERE Email = ? AND Password = ?";
    //     try{
            
    //         User authroity = this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
    //         @Override
    //         public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    //             User a = new User();
    //             a.setEmail(rs.getString("email"));
    //             a.setPassword(rs.getString("password"));
    //             return a;
    //         }
    //         }, name, password);
    //     }
    //     catch (EmptyResultDataAccessException e){
    //         return false;
    //     }
    //     return true;
    // }

    public boolean isUser(String name, String password) {
    String sql = "SELECT COUNT(*) FROM user WHERE Email = ? AND Password = ?";
    int count;
    try{
        count = this.jdbcTemplate.queryForObject(sql, Integer.class, name, password);
        if (count > 0) return true;
        return false;
    }catch (EmptyResultDataAccessException e){
        return false;
    }
    }


}


// public static boolean isUser(String name, String password) {
//     String sql = "SELECT COUNT(*) FROM user WHERE Name = ? AND Password = ?;";
//     int count;
//     try{
//         count = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{name, password});
//     }
//     catch (EmptyResultDataAccessException e){
//         return false;
//     }
//     if (count > 0) return true;
//     return false;
// }
