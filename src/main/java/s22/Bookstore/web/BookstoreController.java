package s22.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookstoreController {
	
		@GetMapping("/index")
		public String home(@RequestParam(name="title")String title,
				@RequestParam(name="author") String author,
				@RequestParam(name="year") String year,
				@RequestParam(name="isbn") String isbn,
				@RequestParam(name="price") String price,
				Model model){
			return "index";
		}

			
}
