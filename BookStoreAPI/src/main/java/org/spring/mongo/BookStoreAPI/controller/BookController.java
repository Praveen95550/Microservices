package org.spring.mongo.BookStoreAPI.controller;

import java.util.List;
import java.util.Optional;

import org.spring.mongo.BookStoreAPI.model.Book;
import org.spring.mongo.BookStoreAPI.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	private BookRepository book;
	
	@PostMapping("/addBook")
	public void saveBook(@RequestBody Book b) {
		
		book.save(b);
	}
	
	@GetMapping("/getBooks")
	public List<Book> getBooks(){
		return book.findAll();
	}
	
	@GetMapping("/getBooks/{id}")
	public Optional<Book> getBookById(@PathVariable int id){
		return book.findById(id);
	}
	
	@DeleteMapping("/deleteById")
	public String deleteBook(@RequestParam int id) {
		book.deleteById(id);
		return "Book deleted for given ID";
	}
	
	
}
