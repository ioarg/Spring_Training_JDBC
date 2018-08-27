package john.learning.spring.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import john.learning.spring.model.EBook;
import john.learning.spring.model.EBookDAO;
import john.learning.spring.util.ViewNames;

@Controller
@RequestMapping("library")
public class LibraryController {
	@Resource
	EBookDAO ebookDao;

	// get page 1 for now - just for testing
	@GetMapping("list")
	public String getListPage(Model model) {
		List<EBook> books = ebookDao.getEBooksPage(1);
		model.addAttribute("book_list", books);
		return ViewNames.LIST_PAGE;
	}

	//Return a paginated view with the results of the selected page
	@GetMapping("tiles/{page}")
	public String getLibraryPage(Model model, @PathVariable int page) {
		//Get number of total pages to display
		int pagesToShow = ebookDao.getNumOfPages();
		//get the books of the current page only
		List<EBook> books;
		if(page<=0) {
			books = ebookDao.getEBooksPage(1);
		}else {
			books = ebookDao.getEBooksPage(page);
		}		
		model.addAttribute("pages", pagesToShow);
		model.addAttribute("book_list", books);
		model.addAttribute("selected_page", page);
		return ViewNames.TILE_PAGE;
	}

}
