package com.ucalgary.librarySystem.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import com.ucalgary.librarySystem.model.Book;
import com.ucalgary.librarySystem.model.User;
import com.ucalgary.librarySystem.repository.AdminRepository;
import com.ucalgary.librarySystem.repository.BookRepository;
import com.ucalgary.librarySystem.repository.UserRepository;

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
    
    public int registerUser(String Email, String Password){
        return userRepository.registerNewUser(Email, Password);
    }

    public List<Book> searchByBookName(String bookName){
        return  bookRepository.searchByBookName(bookName).stream()
        .map((book) -> new Book.Builder(book).build())
        .collect(Collectors.toList());
    }
}

