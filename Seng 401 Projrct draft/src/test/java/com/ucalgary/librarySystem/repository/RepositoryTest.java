package com.ucalgary.librarySystem.repository;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.model.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RepositoryTest {

    @InjectMocks
    private StorageDAL dal;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BorrowRepository borrowRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isAdminValidTest() {
        when(adminRepository.isAdmin("ruiguan@gamil.com", "401")).thenReturn(true);

        assertTrue(dal.isAdmin("ruiguan@gamil.com", "401"));
        verify(adminRepository, times(1)).isAdmin("ruiguan@gamil.com", "401");
    }

    @Test
    public void isAdminInvalidTest() {
        when(adminRepository.isAdmin("DNEADMIN@gamil.com", "DNEDNE")).thenReturn(false);

        assertFalse(dal.isAdmin("DNEADMIN@gamil.com", "DNEDNE"));
        verify(adminRepository, times(1)).isAdmin("DNEADMIN@gamil.com", "DNEDNE");
    }

    @Test
    public void isUserValidTest() {
        when(userRepository.isUser("zhengchen@gamil.com", "seng401")).thenReturn(true);

        assertTrue(dal.isUser("zhengchen@gamil.com", "seng401"));
        verify(userRepository, times(1)).isUser("zhengchen@gamil.com", "seng401");
    }

    @Test
    public void isUserInvalidTest() {
        when(userRepository.isUser("DNEUSER@gamil.com", "DNEDNE")).thenReturn(false);

        assertFalse(dal.isUser("DNEUSER@gamil.com", "DNEDNE"));
        verify(userRepository, times(1)).isUser("DNEUSER@gamil.com", "DNEDNE");
    }

    @Test
    public void getUserByEmailTest() {
        User user = new User(232325, "Zheng", "1919 University Dr, Calgary", "587-999-4181", "2000-01-01",
                "zhengchen@gamil.com", "seng401", 50);

        when(userRepository.getUserByEmail("zhengchen@gamil.com")).thenReturn(user);

        assertEquals(user.getUserID(), dal.getUserByEmail("zhengchen@gamil.com").getUserID());
        verify(userRepository, times(1)).getUserByEmail("zhengchen@gamil.com");
    }

    @Test
    public void getAdminByEmailTest() {
        Admin admin = new Admin(881324, "Rui", "2500 University Dr, Calgary", "403-457-1234", "2000/06/06",
                "ruiguan@gmail.com", "401", 1);

        when(adminRepository.getAdminByEmail("ruiguan@gmail.com")).thenReturn(admin);

        assertEquals(admin.getAdminID(), dal.getAdminByEmail("ruiguan@gmail.com").getAdminID());
        verify(adminRepository, times(1)).getAdminByEmail("ruiguan@gmail.com");
    }

    @Test
    public void registerNewUserTest() {

        when(userRepository.registerNewUser("testonly@gmail.com", "401", "TestOnly", "1234 3rd st, Calgary",
                "587-666-5555", "2003-03-03")).thenReturn(1);

        assertEquals(1, dal.registerUser("testonly@gmail.com", "401", "TestOnly", "1234 3rd st, Calgary",
                "587-666-5555", "2003-03-03"));
        verify(userRepository, times(1)).registerNewUser("testonly@gmail.com", "401", "TestOnly",
                "1234 3rd st, Calgary", "587-666-5555", "2003-03-03");
    }

    @Test
    public void searchByBookNameTGest() {

        List<Book> book = new ArrayList<Book>();
        book.add(new Book(3, 77777, "FactoryL", "A real player story", "Biography", 2019, "James C", "CalgBOOK House",
                "C", 1, "Avaiable"));

        when(bookRepository.searchByBookName("FactoryL")).thenReturn(book);

        assertEquals(book.get(0).getBookID(), dal.searchByBookName("FactoryL").get(0).getBookID());
        verify(bookRepository, times(1)).searchByBookName("FactoryL");
    }

    @Test
    public void searchByBookAuthorTest() {

        List<Author> author = new ArrayList<Author>();
        author.add(new Author("James C", "9981 University Dr, Calgary", "587-6666-5442"));

        when(bookRepository.searchByBookAuthor("FactoryL")).thenReturn(author);

        assertEquals(author.get(0).getName(), dal.searchByBookAuthor("FactoryL").get(0).getName());
        verify(bookRepository, times(1)).searchByBookAuthor("FactoryL");
    }

    @Test
    public void searchByBookPublisherTest() {

        List<Publisher> publisher = new ArrayList<Publisher>();
        publisher.add(new Publisher("CalgBOOK House", "1 centre st, Calgary", "587-888-8888"));

        when(bookRepository.searchByBookPublisher("FactoryL")).thenReturn(publisher);

        assertEquals(publisher.get(0).getName(), dal.searchByBookPublisher("FactoryL").get(0).getName());
        verify(bookRepository, times(1)).searchByBookPublisher("FactoryL");
    }

    @Test
    public void searchByBookReviewTest() {

        List<Review> review = new ArrayList<Review>();
        review.add(new Review(2, "Magic Rings", "Just so-so", "2 stars", "2022-03-15"));

        when(bookRepository.searchByBookReview(2)).thenReturn(review);

        assertEquals(review.get(0).getDescription(), dal.searchByBookReview(2).get(0).getDescription());
        verify(bookRepository, times(1)).searchByBookReview(2);
    }

    @Test
    public void searchRentedBookTrueTest() {

        when(bookRepository.searchRentedBook(2)).thenReturn(true);

        assertTrue(dal.searchRentedBook(2));
        verify(bookRepository, times(1)).searchRentedBook(2);
    }

    @Test
    public void searchRentedBookFalseTest() {

        when(bookRepository.searchRentedBook(77)).thenReturn(false);

        assertFalse(dal.searchRentedBook(77));
        verify(bookRepository, times(1)).searchRentedBook(77);
    }

    @Test
    public void deleteBorrowInfoTrueTest() {

        when(borrowRepository.deleteBorrowInfo(232326, 3)).thenReturn(true);

        assertTrue(dal.deleteBorrowInfo(232326, 3));
        verify(borrowRepository, times(1)).deleteBorrowInfo(232326, 3);
    }

    @Test
    public void deleteBorrowInfoFalseTest() {

        when(borrowRepository.deleteBorrowInfo(239999, 88)).thenReturn(false);

        assertFalse(dal.deleteBorrowInfo(239999, 88));
        verify(borrowRepository, times(1)).deleteBorrowInfo(239999, 88);
    }
    
    
    
    
    
    
    //need to be tested
    
    @Test
    public void insertRentedBookTest() {
        when(bookRepository.insertRentedbook(2, 232324, Date.valueOf("2022-03-01"), Date.valueOf("2022-03-31"))).thenReturn(true);
        assertTrue(dal.insertRentedBook(2, 232324, Date.valueOf("2022-03-01"), Date.valueOf("2022-03-31")));
        verify(bookRepository, times(1)).insertRentedbook(2, 232324, Date.valueOf("2022-03-01"), Date.valueOf("2022-03-31"));
    }

    @Test
    public void checkForValidRentedBookTest() {
        when(bookRepository.checkForRentedBook(232324, 2)).thenReturn(true);
        assertTrue(dal.checkForRentedBook(232324,2));
        verify(bookRepository, times(1)).checkForRentedBook(232324,2);
    }

    @Test
    public void checkForInvalidRentedBookTest() {
        when(bookRepository.checkForRentedBook(232320, 2)).thenReturn(false);
        assertTrue(dal.checkForRentedBook(232320,2));
        verify(bookRepository, times(1)).checkForRentedBook(232320,2);
    }

    @Test
    public void searchBorrowBooksTest() {
        List <Borrow> borrows = new ArrayList<Borrow>();
        borrows.add(new Borrow(2, "Four Kindoms", "2022-03-01", "2022-03-31"));
        borrows.add(new Borrow(1, "Magic Rings", "2022-03-01", "2022-03-31"));
        when(bookRepository.searchBorrowBooks(232324)).thenReturn(borrows);
        assertEquals(borrows, dal.searchBorrowBooks(232324));
        verify(bookRepository, times(1)).searchBorrowBooks(232324);
    }

    @Test
    public void searchReviewByUserTest() {
        List <Review> reviews = new ArrayList<Review>();
        reviews.add(new Review(2, "Four Kindoms", "Just so-so", "2 stars", "2022-03-15"));
        reviews.add(new Review(1, "Magic Rings", "good book", "3 stars", "2022-03-14"));
        when(bookRepository.searchReviewByUser(232324)).thenReturn(reviews);
        assertEquals(reviews, dal.searchReviewByUser(232324));
        verify(bookRepository, times(1)).searchReviewByUser(232324);
    }


    @Test
    public void checkValidUserReviewTest() {
        when(bookRepository.checkUserReview(232324, 2)).thenReturn(true);
        assertTrue(dal.checkUserReview(232324,2));
        verify(bookRepository, times(1)).checkUserReview(232324,2);
    }

    @Test
    public void checkInvalidUserReviewTest() {
        when(bookRepository.checkUserReview(232327, 6)).thenReturn(false);
        assertTrue(dal.checkUserReview(232327,6));
        verify(bookRepository, times(1)).checkUserReview(232327,6);
    }

    @Test
    public void writeReview() {
        when(bookRepository.writeReview(232324, 5, "good story", "4 stars", Date.valueOf("2022-02-22"))).thenReturn(true);
        assertTrue(dal.writeReview(232324, 5, "good story", "4 stars", Date.valueOf("2022-02-22")));
        verify(bookRepository, times(1)).writeReview(232324, 5, "good story", "4 stars", Date.valueOf("2022-02-22"));
    }

    @Test
    public void hasNotReviewedYetTest() {
        when(bookRepository.HasNotReviewedYet(232324, 2)).thenReturn(true);
        assertTrue(dal.HasNotReviewedYet(232324,2));
        verify(bookRepository, times(1)).HasNotReviewedYet(232324,2);
    }



    @Test
    public void searchByValidBookIDTest() {
        List <Book> books = new ArrayList <Book> ();
        books.add(new Book(9, 888888, "The Little Prince", "A real warm story", "child's fairy tale", 1943, "Antoine de Saint-Exupéry", "Reynal & Hitchcock",
                "C", 1, "Avaiable"));
        books.add(new Book(3, 77777, "FactoryL", "A real player story", "Biography", 2019, "James C", "CalgBOOK House",
                "C", 1, "Avaiable"));
        when(bookRepository.searchByBookID(9)).thenReturn(books);

        assertEquals(books.get(0).getName(), dal.searchByBookID(9).get(0).getName());
        verify(bookRepository, times(1)).searchByBookID(9);
    }
}
