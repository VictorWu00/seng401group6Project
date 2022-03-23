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
    public void searchByBookNameTGest() {

        List<Book> book = new ArrayList<Book>();
        book.add(new Book(3, 77777, "FactoryL", "A real player story", "Biography", 2019, "James C", "CalgBOOK House",
                "C", 1, "Avaiable"));

        when(bookRepository.searchByBookName("FactoryL")).thenReturn(book);

        assertEquals(book.get(0).getBookID(), dal.searchByBookName("FactoryL").get(0).getBookID());
        verify(bookRepository, times(1)).searchByBookName("FactoryL");
    }

}