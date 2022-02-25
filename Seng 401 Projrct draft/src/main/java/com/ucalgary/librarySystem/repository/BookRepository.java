package com.ucalgary.librarySystem.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Types;
import com.ucalgary.librarySystem.model.Book;

import com.ucalgary.librarySystem.model.Admin;

@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //needed to have the controller
    public boolean addBook(int isbn, String name, String description, String category, int year, String author, String publisher, String section, int location){
        String query="insert into book (ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location) values (?,?,?,?,?,?,?,?,?)";
        int result=jdbcTemplate.update(query,isbn,name,description,category,year,author,publisher,section,location);
        if(result>0){
            return true;
        }
        return false;
    }

    public boolean deleteBook(String bookName, String Author){
        String query="delete from book where Auhor = ? and Name = ?";
        int result=jdbcTemplate.update(query, bookName, Author);
        if(result>0){
            return true;
        }
        return false;
    }
    
    public List<Book> searchByBookName(String bookName){
        return jdbcTemplate.query(
                "SELECT B.ISBN, B.Name, B.Description, B.Category, B.Year, B.Auhor, B.Publisher, B.SectionName, B.Location" +
                        "FROM book AS B " +
                        "WHERE B.Name = ?",
                new Object[]{"%" + bookName + "%"},
                new int[]{Types.VARCHAR},
                BookRepository::mapAllBooks
        );
        
    }

    public List<Book> searchByAuthor(String author){
        return jdbcTemplate.query(
                "SELECT B.ISBN, B.Name, B.Description, B.Category, B.Year, B.Auhor, B.Publisher, B.SectionName, B.Location" +
                        "FROM book AS B " +
                        "WHERE B.Auhor = ?",
                new Object[]{"%" + author + "%"},
                new int[]{Types.VARCHAR},
                BookRepository::mapAllBooks
        );
    }

    private static Book mapAllBooks(ResultSet rs,int rowNum) throws SQLException{
        return new Book(
            rs.getInt("ID"),
            rs.getInt("ISBN"),
            rs.getString("Name"),
            rs.getString("Description"),
            rs.getString("Category"),
            rs.getInt("Year"),
            rs.getString("Auhor"),
            rs.getString("Publisher"),
            rs.getString("SectionName"),
            rs.getInt("Location")
        );
    }


}
