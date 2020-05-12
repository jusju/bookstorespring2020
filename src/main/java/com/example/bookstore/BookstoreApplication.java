package com.example.bookstore;

import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			
			
			log.info("save a couple of categories");
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Bio"));

			LocalDate pvm = LocalDate.parse("2020-04-20");
			java.util.Date date = java.sql.Date.valueOf(pvm);
	
			
			log.info("save a couple of books");
			repository.save(new Book("The Capital", "Marx", 1890, "123-234", 99.90, crepository.findByName("Bio").get(0)));
			repository.save(new Book("Nykypaivien Saksa", "Sven", 1935, "123-2346", 189.90, crepository.findByName("Horror").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
	
}
