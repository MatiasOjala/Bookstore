package s22.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;
import s22.Bookstore.domain.CategoryRepository;

@Controller
public class BookstoreController {
		@Autowired
		private BookRepository repository;
		
		@Autowired
		private CategoryRepository catRepository;
	
		@RequestMapping(value= {"/", "/booklist"})
		public String bookList(Model model) {
		model.addAttribute("book", repository.findAll());
		return "booklist";
		}
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value= "/add")
		public String addBook(Model model) {
			model.addAttribute("book", new Book());
			model.addAttribute("categories", catRepository.findAll());
			return "addbook";
		}
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value= "/save", method = RequestMethod.POST)
		public String save(Book book) {
			repository.save(book);
			return "redirect:booklist";
		}
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long id, Model model) {
	    	repository.deleteById(id);
	        return "redirect:../booklist";
	    } 
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	    public String editBook(@PathVariable("id") Long id, Model model) {
	    	model.addAttribute("editbook", repository.findById(id));
	    	model.addAttribute("categories", catRepository.findAll());
	    	return "editbook";
		}
		@RequestMapping(value= {"/", "/mainpage"})
		public String mainPage(Model model) {
		return "mainpage";
		}
}
