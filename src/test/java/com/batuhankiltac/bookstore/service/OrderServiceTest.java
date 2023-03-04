package com.batuhankiltac.bookstore.service;

import com.batuhankiltac.bookstore.entity.Book;
import com.batuhankiltac.bookstore.entity.Order;
import com.batuhankiltac.bookstore.model.request.BookOrderRequest;
import com.batuhankiltac.bookstore.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private BookService bookService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void it_should_get_all_orders() {
        // Given
        final Order order1 = Order.builder()
                .id(1)
                .totalPrice(50.0)
                .userName("test-username1")
                .build();
        final Order order2 = Order.builder()
                .id(2)
                .totalPrice(60.0)
                .userName("test-username2")
                .build();
        final List<Order> orders = Arrays.asList(order1, order2);

        // When
        orderService.getAllOrders();

        // Then
        verify(orderRepository).findAll();
        assertThat(orders).isNotEmpty();
        assertThat(order1.getId()).isNotEqualTo(order2.getId());
        assertThat(order2.getUserName()).isEqualTo("test-username2");
    }

    @Test
    public void it_should_save_order() {
        // Given
        final Integer bookId1 = 1;
        final Integer bookId2 = 2;
        final Integer bookId3 = 3;
        final List<Integer> bookIdList = Arrays.asList(bookId1, bookId2, bookId3);
        final BookOrderRequest bookOrderRequest = BookOrderRequest.builder()
                .bookIdList(bookIdList)
                .userName("test-username")
                .build();
        final Book book = Book.builder()
                .price(BigDecimal.TEN.doubleValue())
                .build();
        final Order order = Order.builder()
                .id(1)
                .bookList(bookOrderRequest.getBookIdList())
                .totalPrice(book.getPrice())
                .userName(bookOrderRequest.getUserName())
                .build();

        // When
        orderService.addOrder(bookOrderRequest);

        // Then
        verify(bookService).findBookById(bookOrderRequest.getBookIdList().get(0));
        assertThat(order.getId()).isNotNull();
        assertThat(order.getUserName()).isEqualTo("test-username");
    }
}