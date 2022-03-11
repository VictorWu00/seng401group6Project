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
    public void addBook(int isbn, String name, String description, String category, int year, String author, String publisher, String section, int location){
        String query="insert into book (ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location) values (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,isbn,name,description,category,year,author,publisher,section,location);

    }

    public void deleteBook(String bookName, String Author){
        String query="delete from book where Auhor = ? and Name = ?";
        jdbcTemplate.update(query, bookName, Author);
    }
    
    public List<Book> searchByBookName(String bookName){
        return jdbcTemplate.query(
                "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location FROM book WHERE Name = ?",
                BookRepository::mapAllBooks,
                bookName
        );
        
    }

    public List<Book> searchByAuthor(String author){
        return jdbcTemplate.query(
            "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location FROM book WHERE Author = ?",
                BookRepository::mapAllBooks, author
        );
    }


    private static Book mapAllBooks(ResultSet rs,int rowNum) throws SQLException{
        return new Book(
            rs.getInt("BookID"),
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
