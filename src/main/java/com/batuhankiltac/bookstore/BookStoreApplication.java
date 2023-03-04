package com.batuhankiltac.bookstore;

import com.batuhankiltac.bookstore.entity.Book;
import com.batuhankiltac.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class BookStoreApplication implements CommandLineRunner {
	private final BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Book b1 = Book.builder()
				.name("Lord of the Rings 1-2-3")
				.author("J.R.R. Tolkien")
				.price(175.00)
				.stock(100).build();

		Book b2 = Book.builder()
				.name("Good Habits, Bad Habits")
				.author("Wendy Wood")
				.price(184.20)
				.stock(100).build();

		Book b3 = Book.builder()
				.name("Animal Farm")
				.author("George Orwell")
				.price(165.80)
				.stock(100).build();

		bookRepository.saveAll(Arrays.asList(b1, b2, b3));
	}
}