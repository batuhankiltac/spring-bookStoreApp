package com.batuhankiltac.bookstore.controller;

import com.batuhankiltac.bookstore.entity.Order;
import com.batuhankiltac.bookstore.model.request.BookOrderRequest;
import com.batuhankiltac.bookstore.service.BookService;
import com.batuhankiltac.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookstores")
@RequiredArgsConstructor
public class BookStoreController {
    private final OrderService orderService;
    private final BookService bookService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order addOrder(@RequestBody BookOrderRequest bookOrderRequest) {
        return orderService.addOrder(bookOrderRequest);
    }
}