package com.ucalgary.librarySystem.repository;


import com.ucalgary.librarySystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //needed to have the controller
    public void addBook(int isbn, String name, String description, String category, int year, String author, String publisher, String section, int location){
        String query="insert into book (ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location, Status) values (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,isbn,name,description,category,year,author,publisher,section,location,"Available");

    }

    public boolean insertRentedbook(int bookID, int userId, Date sdate, Date edate)
    {
        String sql = "insert into borrow (User_ID, Book_ID, Start_Date, End_Date) values (?,?,?,?)";
        int count;
        try{
            count = this.jdbcTemplate.update(sql, userId, bookID, sdate, edate);
            if (count > 0) return true;
            return false;
        }catch (EmptyResultDataAccessException e){
        return false;
        }
    }

    public void deleteBook(String bookName, String Author){
        String query="delete from book where Auhor = ? and Name = ?";
        jdbcTemplate.update(query, bookName, Author);
    }
    
    public List<Book> searchByBookName(String bookName){
        return jdbcTemplate.query(
                "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location, Status FROM book WHERE Name = ?",
                BookRepository::mapAllBooks,
                bookName
        );
        
    }

    public boolean searchRentedBook(int bookID)
    {
        String sql = "SELECT COUNT(*) FROM book WHERE  BOOKID = ? AND Status = ?";
        int count;
        try{
            count = this.jdbcTemplate.queryForObject(sql, Integer.class, bookID, "Available");
            if (count > 0) return true;
            return false;
        }catch (EmptyResultDataAccessException e){
        return false;
        }
    }

    public boolean checkForRentedBook(int bookID, int userID)
    {
        String sql = "SELECT COUNT(*) FROM borrow WHERE  User_ID = ? AND Book_ID = ?";
        int count;
        try{
            count = this.jdbcTemplate.queryForObject(sql, Integer.class, userID, bookID);
            if (count > 0) return true;
            return false;
        }catch (EmptyResultDataAccessException e){
        return false;
        }
    }

    public void updateStatus(int id)
    {
        String sql = "Update book Set Status = ? WHERE BOOKID = ?";
        jdbcTemplate.update(sql, "Rented", id);
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
            "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location, Status FROM book WHERE Author = ?",
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
                "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location, Status FROM book",
                BookRepository::mapAllBooks
        );
    }

    public List<Borrow> searchBorrowBooks(int user_id)
    {
        return jdbcTemplate.query(
            "SELECT borrow.Book_ID, book.Name, borrow.Start_Date, borrow.End_Date FROM borrow, book WHERE borrow.Book_ID = book.BOOKID AND borrow.User_ID = ?",
                BookRepository::mapAllBorrow, user_id
        );
    }

    public List<Review> searchReviewByUser(int user_id)
    {
        return jdbcTemplate.query(
            "SELECT review.Book_ID, book.Name, review.Description, review.Rating, review.ReviewDate FROM review, book WHERE review.Book_ID = book.BOOKID AND review.User_ID = ?",
                BookRepository::mapAllReview2, user_id
        );
    }

    public boolean checkUserReview(int user_id, int book_id)
    {
        String sql = "SELECT COUNT(*) FROM review WHERE User_ID = ? AND Book_ID = ?";
        int count;
        try{
            count = this.jdbcTemplate.queryForObject(sql, Integer.class, user_id, book_id);
            if (count > 0) return true;
            return false;
        }catch (EmptyResultDataAccessException e){
        return false;
        }
    }

    public void deleteReview(int userId, int bookID)
    {
        String query="delete from review where User_ID = ? AND Book_ID = ?";
        jdbcTemplate.update(query, userId, bookID);
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


    private static Borrow mapAllBorrow(ResultSet rs,int rowNum) throws SQLException{
        return new Borrow(
            rs.getInt("Book_ID"),
            rs.getString("Name"),
            rs.getString("Start_Date"),
            rs.getString("End_Date")
        );
    }

    private static Review mapAllReview2(ResultSet rs,int rowNum) throws SQLException{
        return new Review(
            rs.getInt("Book_ID"),
            rs.getString("Name"),
            rs.getString("Description"),
            rs.getString("Rating"),
            rs.getString("ReviewDate")
        );
    }

    public boolean writeReview(int userID,int bookID,String review,String rating, Date date){
        String query="INSERT INTO review (User_ID, Book_ID, Description, Rating, ReviewDate) values (?,?,?,?,?)";
        int count;
        try{
            count = this.jdbcTemplate.update(query,userID,bookID,review,rating,date);
            if(count>0){
                return true;
            }
            else{
                return false;
            }
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }

    public List<Book> searchByBookID(int bookID){
        return jdbcTemplate.query(
            "SELECT BookID, ISBN, Name, Description, Category, Year, Auhor, Publisher, SectionName, Location, Status FROM book WHERE BookID = ?",
            BookRepository::mapAllBooks,
            bookID
        );
    }

    public boolean HasNotReviewedYet(int userID, int bookID){
        List<Review> res = jdbcTemplate.query(
            "SELECT Description,Rating,ReviewDate FROM review WHERE User_ID = ? AND Book_ID = ?",
            BookRepository::mapAllReviews,
            userID,
            bookID
        );
        if(res.size()==0){
            return true;
        }
        else{
            return false;
        }

    }

    }

