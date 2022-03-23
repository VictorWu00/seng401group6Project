package com.ucalgary.librarySystem.repository;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.model.Book;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BookRepositoryTest {

    @InjectMocks
    private StorageDAL dal;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void searchByBookName() {

        List<Book> book = new ArrayList<Book>();
        book.add(new Book(3, 77777, "FactoryL", "A real player story", "Biography", 2019, "James C", "CalgBOOK House",
                "C", 1, "Avaiable"));

        when(bookRepository.searchByBookName("FactoryL")).thenReturn(book);

        //bookRepository.searchByBookName("FactoryL");
        assertEquals(book.get(0).getBookID(),dal.searchByBookName("FactoryL").get(0).getBookID());
        verify(bookRepository,times(1)).searchByBookName("FactoryL");
    }

}