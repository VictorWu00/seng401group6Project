package com.ucalgary.librarySystem.repository;


import com.ucalgary.librarySystem.model.User;
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

    public User getUserByID(int ID){
        User res;
        String sql = "SELECT Name, Address, PhoneNumber, DateOfBirth, Email, Balance FROM user WHERE ID = ?";
        try{
            res = this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User a = new User(ID, rs.getString("Name"), rs.getString("Address"), rs.getString("PhoneNumber"), rs.getString("DateOfBirth")
                    , rs.getString("Email"), "",rs.getDouble("Balance"));
                    return a;
                }
            }, ID);
        }catch(EmptyResultDataAccessException e){
            res = new User();
            return res;
        }
        return res;
    }

    public User getUserByEmail(String Email){
        User res;
        String sql = "SELECT ID, Name, Address, PhoneNumber, DateOfBirth, Email, Balance FROM user WHERE Email = ?";
        try{
            res = this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User a = new User(rs.getInt("ID"), rs.getString("Name"), rs.getString("Address"), rs.getString("PhoneNumber"), rs.getString("DateOfBirth")
                    , rs.getString("Email"), "",rs.getDouble("Balance"));
                    return a;
                }
            }, Email);
        }catch(EmptyResultDataAccessException e){
            res = new User();
            return res;
        }
        return res;
    }

    //success return value > 0, else return 0
    public int addNewUser(String Name, String Address, String PhoneNumber, String DateOfBirth, String Email, String Password){
        String sql = "INSERT INTO user (Name,Address,PhoneNumber,DateOfBirth,Email,Password,Balance) values(?,?,?,?,?,0)";
        int res = this.jdbcTemplate.update(sql, Name, Address, PhoneNumber, DateOfBirth, Email, Password);
        return res;
    }

    public int registerNewUser(String Email, String Password){
        String sql = "INSERT INTO user (Email, Password) values (?, ?)";
        int res = this.jdbcTemplate.update(sql, Email, Password);
        return res;
    }

    public int deleteUser(int ID){
        String sql = "DELETE FROM user WHERE ID = ?";
        int res = this.jdbcTemplate.update(sql, ID);
        return res;
    }



}

