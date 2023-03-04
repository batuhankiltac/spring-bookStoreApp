package com.batuhankiltac.bookstore.service;

import com.batuhankiltac.bookstore.entity.Book;
import com.batuhankiltac.bookstore.entity.Order;
import com.batuhankiltac.bookstore.model.request.BookOrderRequest;
import com.batuhankiltac.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final BookService bookService;
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(BookOrderRequest bookOrderRequest) {
        List<Optional<Book>> bookList = bookOrderRequest.getBookIdList()
                .stream()
                .map(bookService::findBookById)
                .collect(Collectors.toList());

        Double totalPrice = bookList
                .stream()
                .map(optionalBook -> optionalBook.map(Book::getPrice).orElse(0.0))
                .reduce(0.0, Double::sum);

        Order order = Order.builder()
                .bookList(bookOrderRequest.getBookIdList())
                .totalPrice(totalPrice)
                .userName(bookOrderRequest.getUserName())
                .build();

        return orderRepository.save(order);
    }
}