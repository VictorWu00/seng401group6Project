package com.ucalgary.librarySystem.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ucalgary.librarySystem.model.Admin;
import com.ucalgary.librarySystem.model.Author;
import com.ucalgary.librarySystem.model.Book;
import com.ucalgary.librarySystem.model.Borrow;
import com.ucalgary.librarySystem.model.Publisher;
import com.ucalgary.librarySystem.model.Review;
import com.ucalgary.librarySystem.model.User;
import com.ucalgary.librarySystem.repository.AdminRepository;
import com.ucalgary.librarySystem.repository.BookRepository;
import com.ucalgary.librarySystem.repository.UserRepository;
import com.ucalgary.librarySystem.repository.BorrowRepository;

/**
 * Provides access to stored data, whether through a database or a file stored on the device.
 * <p>
 * This class provides an abstraction layer over the repository layer for easy mocking/testing of data.
 */
@Service
public class StorageDAL {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    public boolean isAdmin(String username, String password){
        boolean auth = adminRepository.isAdmin(username,password);
        return auth;
    }

    public boolean isUser(String username, String password){
        boolean auth = userRepository.isUser(username,password);
        return auth;
    }

    public void addBook(int isbn, String name, String description, String category, int year, String author, String publisher, String section, int location){
        bookRepository.addBook(isbn, name, description, category, year, author, publisher, section, location);
    }

    public void deleteBook(String name, String author){
        bookRepository.deleteBook(author, name);
    }

    public User getUserByEmail(String Email){
        return userRepository.getUserByEmail(Email);
    }

    public Admin getAdminByEmail(String Email){
        return adminRepository.getAdminByEmail(Email);
    }
    
    public int registerUser(String Email, String Password, String Name, String Address, String Phone, String Birth){
        return userRepository.registerNewUser(Email, Password, Name, Address, Phone, Birth);
    }

    public List<Book> searchByBookName(String bookName){
        return  bookRepository.searchByBookName(bookName).stream()
        .map((book) -> new Book.Builder(book).build())
        .collect(Collectors.toList());
    }

    public void modifyUser(String name, String address, String phone, String birth, double balance){
        userRepository.modifyUser(name, address, phone, birth, balance);
    }

    public List<Author> searchByBookAuthor(String bookName)
    {
        return  bookRepository.searchByBookAuthor(bookName);
    }

    public List<Publisher> searchByBookPublisher(String bookName)
    {
        return  bookRepository.searchByBookPublisher(bookName);
    }

    public List<Review> searchByBookReview(int bookId)
    {
        return  bookRepository.searchByBookReview(bookId);
    }

    public boolean searchRentedBook(int bookId)
    {
        return bookRepository.searchRentedBook(bookId);
    }
    
    public boolean deleteBorrowInfo(int uID, int bID){
        return borrowRepository.deleteBorrowInfo(uID, bID);
    }

    public boolean insertRentedBook(int bookId, int userId, Date sdate, Date eDate)
    {
        return bookRepository.insertRentedbook(bookId, userId, sdate, eDate);
    }

    public void updateStatus(int id)
    {
        bookRepository.updateStatus(id);
    }

    public boolean checkForRentedBook(int id, int uid)
    {
        return bookRepository.checkForRentedBook(id, uid);
    }


    public List<Borrow> searchBorrowBooks(int UserID)
    {
        return bookRepository.searchBorrowBooks(UserID);
    }
    public List<Review> searchReviewByUser(int UserID)
    {
        return bookRepository.searchReviewByUser(UserID);
    }

    public boolean checkUserReview(int uid, int bid)
    {
        return bookRepository.checkUserReview(uid, bid);
    }

    public void deleteReview(int uid, int bid)
    {
         bookRepository.deleteReview(uid, bid);
    }

    public boolean writeReview(int userID, int bookID,String review,String rating,Date date){
        return bookRepository.writeReview(userID, bookID,review, rating,date);
    }

    public List<Book> searchByBookID(int bookID){
        return bookRepository.searchByBookID(bookID);
    }

    public boolean HasNotReviewedYet(int userID, int bookID){
        return bookRepository.HasNotReviewedYet(userID, bookID);

    }

    public List<Book> searchguestBook(String bookName)
    {
        return bookRepository.searchguestBook(bookName);
    }
}

