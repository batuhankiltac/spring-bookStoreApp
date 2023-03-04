package com.batuhankiltac.bookstore.service;

import com.batuhankiltac.bookstore.entity.Book;
import com.batuhankiltac.bookstore.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void it_should_get_book_by_id() {
        // Given
        final Book book = Book.builder()
                .id(1)
                .name("test-name")
                .author("test-author")
                .price(10.50)
                .stock(20)
                .build();

        // When
        bookService.findBookById(book.getId());

        // Then
        verify(bookRepository).findById(book.getId());
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor()).isEqualTo("test-author");
    }
}