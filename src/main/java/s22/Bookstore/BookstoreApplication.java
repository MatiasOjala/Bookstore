package s22.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger Log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository repository) {
		return (args) -> {
			Log.info("save a couple of books");
			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", "1232323-21", 1929, 25));
			repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1245, 15));
			
			Log.info("fetch all books");
			for (Book book : repository.findAll()) {
					Log.info(book.toString());
			}
		};
	}

}
