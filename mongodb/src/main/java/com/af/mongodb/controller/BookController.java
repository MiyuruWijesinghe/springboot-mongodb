package com.af.mongodb.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.af.mongodb.model.Book;
import com.af.mongodb.resource.BookAddResource;
import com.af.mongodb.resource.SuccessAndErrorDetailsResource;
import com.af.mongodb.service.BookService;


@RestController
@RequestMapping(value = "/book")
@CrossOrigin(origins = "*")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookService bookService;

	
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllBooks() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Book> book = bookService.findAll();
		if (!book.isEmpty()) {
			return new ResponseEntity<>((Collection<Book>) book, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("Records not found."));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBookById(@PathVariable(value = "id", required = true) int id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Book> isPresentBook = bookService.findById(id);
		if (isPresentBook.isPresent()) {
			return new ResponseEntity<>(isPresentBook, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("Record not found."));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBook(@RequestBody BookAddResource bookAddResource) {
		Integer bookId = bookService.saveBook(bookAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource("Successfully Created.", bookId.toString());
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable(value = "id", required = true) int id) {
		String message = bookService.deleteBook(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
