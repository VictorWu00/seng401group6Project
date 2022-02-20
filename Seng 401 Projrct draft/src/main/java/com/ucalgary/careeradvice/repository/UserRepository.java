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
                "SELECT * FROM Users WHERE username=? AND password=?",
                new Object[]{username, password},
                new int[]{Types.VARCHAR, Types.VARCHAR},
                UserRepository::mapFromResultSet // pass method reference (not spring-boot specific, but with newer versions of java)
        );
    }

    private static User mapFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("phone_no"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("sin"),
                rs.getString("application_name"),
                rs.getString("application_status")
        );
    }

    public static boolean isUser(String username, String password) {
        String sql = "select email, password from userLoginInfo where email = ? and password = ?";
        try{
            /*User authroity = this.jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new RowMapper<Admin>() {
                @Override
                public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Admin a = new Admin();
                    a.setEmail(rs.getString("email"));
                    a.setPassword(rs.getString("password"));
                    return a;
                }
            });*/
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

}
