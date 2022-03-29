package com.ucalgary.librarySystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BorrowRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean deleteBorrowInfo(int uID, int bID){
        String query="delete from borrow where User_ID = ? and Book_ID = ?";
        int res1=jdbcTemplate.update(query, uID, bID);
        String query2="UPDATE book SET Status=? WHERE BookID=?";
        int res2=jdbcTemplate.update(query2, "Available", bID);
        if(res1==1 && res2==1){
            return true;
        }
        else{
            return false;
        }
    }

}
