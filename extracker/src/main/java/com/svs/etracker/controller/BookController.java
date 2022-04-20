package com.svs.etracker.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svs.etracker.model.Book;
import com.svs.etracker.model.Category;
import com.svs.etracker.model.UserExpense;
import com.svs.etracker.service.BookService;
import com.svs.etracker.service.CategoryService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String hello(Model model) {
		
		List<String> categories = categoryService.getOnlyCategories();
		model.addAttribute("categories", categories);
		return "book";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model){

		if (result.hasErrors()) {
			return "book";
		}
		else{			
		
			bookService.addBook(book);
			model.addAttribute("Saved", "Book Added");
			return "redirect:/book";
		}
	}

	@RequestMapping(value = "/showBook", method = RequestMethod.GET)
	public String getBooks(Model model,HttpServletResponse response){
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		return "showBooks";
		
	}
	

}
