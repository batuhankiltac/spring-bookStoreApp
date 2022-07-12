package com.batuhankiltac.bookstore.service;

import com.batuhankiltac.bookstore.entity.Order;
import com.batuhankiltac.bookstore.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
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
        final List<Order> all = orderRepository.saveAll(Arrays.asList(order1, order2));

        // When
        when(orderService.getAllOrders()).thenReturn(all);

        // Then
        assertThat(order1.getId()).isNotEqualTo(order2.getId());
        assertThat(order2.getUserName()).isEqualTo("test-username2");
    }

    @Test
    public void it_should_save_order() {
        // Given
        final Order order = Order.builder()
                .id(1)
                .totalPrice(50.0)
                .userName("test-username")
                .build();

        // When
        when(orderService.addOrder(Collections.singletonList(order.getId()), order.getUserName())).thenReturn(order);

        // Then
        assertThat(order.getId()).isNotNull();
        assertThat(order.getUserName()).isEqualTo("test-username");
    }
}