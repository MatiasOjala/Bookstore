package s22.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s22.Bookstore.domain.ApplicationUser;
import s22.Bookstore.domain.ApplicationUserRepository;
import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger Log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Autowired
	CategoryRepository catRepository;
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	ApplicationUserRepository auRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository repository) {
		return (args) -> {
			Log.info("luodaan kaksi käyttäjää sovellukselle");
			auRepository.save(new ApplicationUser("Matias", "Ojala", "USER", "user", 
					"$2a$10$qTuhvwviu0XCo5UWYECE5eHzFuXV6AfndNOPjF1JDIJOs7Mz7pDzG"));
			auRepository.save(new ApplicationUser("Saitam", "Alajo", "ADMIN", "admin", 
					"$2a$10$ng6O4VyeXpWG12/WkA7AI.d.AsINXpIgJLd3PG1k9VIeR0OQbsB6G"));
			
			Log.info("save a couple of books");
			catRepository.save(new Category("Sci-fi"));
			catRepository.save(new Category("Mystery"));
			catRepository.save(new Category("Drama"));
			
			repository.save(new Book(null, "A Farewell to Arms", "Ernest Hemingway", "1232323-21", 1929, 25, catRepository.findByName("Sci-fi").get(0)));
			repository.save(new Book(null, "Animal Farm", "George Orwell", "2212343-5", 1245, 15, catRepository.findByName("Mystery").get(0)));
			
			Log.info("fetch all books");
			for (Book book : repository.findAll()) {
					Log.info(book.toString());
			}
		};
	}

}
