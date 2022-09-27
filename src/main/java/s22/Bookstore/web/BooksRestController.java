package s22.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;

@RestController
public class BooksRestController {
	@Autowired
	BookRepository repository;

	// return list of cars
	@GetMapping("/books")
	public Iterable<Book> getBooks() { // fetch and return cars
		return repository.findAll();
	}

	// add new car
	@PostMapping("add")
	Book newBook(@RequestBody Book newBook) {
		return repository.save(newBook);
	}



	// find one car and return it
	@GetMapping("/books/{id}")
	Optional<Book> getBook(@PathVariable Long id) {
		return repository.findById(id);
	}

}
