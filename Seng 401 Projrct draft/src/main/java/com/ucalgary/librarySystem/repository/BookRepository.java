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
import com.ucalgary.librarySystem.model.Publisher;
import com.ucalgary.librarySystem.model.Review;
import com.ucalgary.librarySystem.model.Admin;
import com.ucalgary.librarySystem.model.Author;

@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //needed to have the controller
    public void addBook(int isbn, String name, String description, String category, int year, String author, String publisher, String section, int location){
        String query="insert into book (ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location, Status) values (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,isbn,name,description,category,year,author,publisher,section,location,"Available");

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

    public List<Author> searchByBookAuthor(String bookName){
        return jdbcTemplate.query(
                "SELECT author.Name, author.Address, author.PhoneNumber FROM author, book WHERE author.Name = book.Auhor AND book.Name = ?",
                BookRepository::mapAllAuthor,bookName
        );
        
    }

    public List<Publisher> searchByBookPublisher(String bookName){
        return jdbcTemplate.query(
                "SELECT publisher.Name, publisher.Adddress, publisher.PhoneNumber FROM publisher, book WHERE publisher.Name = book.Publisher AND book.Name = ?",
                BookRepository::mapAllPublisher,bookName
        );
        
    }

    public List<Book> searchByAuthor(String author){
        return jdbcTemplate.query(
            "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location FROM book WHERE Author = ?",
                BookRepository::mapAllBooks, author
        );
    }

    public List<Review> searchByBookReview(int bookID){
        return jdbcTemplate.query(
            "SELECT Description, Rating, ReviewDate FROM review WHERE Book_ID = ?",
                BookRepository::mapAllReviews, bookID
        );
    }
 
    public List<Book> getAllBooks(){
        return jdbcTemplate.query(
                "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location FROM book",
                BookRepository::mapAllBooks
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
            rs.getInt("Location"),
            rs.getString("Status")
        );
    }

    private static Author mapAllAuthor(ResultSet rs,int rowNum) throws SQLException{
        return new Author(
            rs.getString("Name"),
            rs.getString("Address"),
            rs.getString("PhoneNumber")
        );
    }

    private static Publisher mapAllPublisher(ResultSet rs,int rowNum) throws SQLException{
        return new Publisher(
            rs.getString("Name"),
            rs.getString("Adddress"),
            rs.getString("PhoneNumber")
        );
    }

    private static Review mapAllReviews(ResultSet rs,int rowNum) throws SQLException{
        return new Review(
            rs.getString("Description"),
            rs.getString("Rating"),
            rs.getString("ReviewDate")
        );
    }


    }

