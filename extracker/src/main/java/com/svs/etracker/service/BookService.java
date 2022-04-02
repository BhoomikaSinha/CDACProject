package com.svs.etracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svs.etracker.model.Book;
import com.svs.etracker.model.Category;
import com.svs.etracker.model.UserCategory;
import com.svs.etracker.repository.BookRepository;
import com.svs.etracker.repository.CategoryRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getBooks(){		
		List<Book> books = new ArrayList<Book>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}

	public void addBook(Book book){
		bookRepository.save(book);
	}
}
