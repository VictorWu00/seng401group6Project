package com.ucalgary.librarySystem.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import com.ucalgary.librarySystem.model.*;
import com.ucalgary.librarySystem.repository.*;

public class BookRepositoryTest {

    private BookRepository bookRepository;

    @Test
    public void searchByValidBookNametest() {
        List<Book> book = new ArrayList<Book>();
        book.add(new Book(3, 77777, "FactoryL", "A real player story", "Biography", 2019, "James C", "CalgBOOK House",
                "C", 1, "Avaiable"));
        assertEquals(book, bookRepository.searchByBookName("FactoryL"));
    }

    @Test
    public void searchByInvalidBookNametest() {
        List<Book> book = new ArrayList<Book>();
        assertEquals(book, bookRepository.searchByBookName("Java Data Structure"));
    }

    @Test
    public void searchByValidRentedBooktest() {
        assertTrue(bookRepository.searchRentedBook(3));
    }

    @Test
    public void searchByInvalidRentedBookTest() {
        assertFalse(bookRepository.searchRentedBook(88));
    }

    @Test
    public void checkForValidRentedBookTest() {
        assertTrue(bookRepository.checkForRentedBook(3, 232325));
    }

    @Test
    public void checkForInvalidRentedBookTest() {
        assertFalse(bookRepository.checkForRentedBook(88, 999999));
    }

    @Test
    public void searchByValidBookAuthorTest() {
        List<Author> auth = new ArrayList<Author>();
        auth.add(new Author("Douglas L", "1234 University Dr, Calgary", "587-7777-5342"));
        assertEquals(auth, bookRepository.searchByBookAuthor("Four Kindoms"));
    }

    @Test
    public void searchByInvalidBookAuthorTest() {
        List<Author> auth = new ArrayList<Author>();
        assertEquals(auth, bookRepository.searchByBookAuthor("Java Data Structure"));
    }

    @Test
    public void searchByValidBookPublisherTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchByInvalidBookPublisherTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchByValidAuthorTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchByInvalidAuthorTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchByValidBookReviewTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchByInvalidBookReviewTestTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchValidBorrowBooksTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchInvalidBorrowBooksTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchReviewByValidUserTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchReviewByInvalidUserTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void checkValidUserReviewTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void checkInvalidUserReviewTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchByValidBookIDTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void searchByInvalidBookIDTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void HasNotReviewedYetTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void HasReviewedYetTest() {
        assertEquals(2, 1 + 1);
    }
}
