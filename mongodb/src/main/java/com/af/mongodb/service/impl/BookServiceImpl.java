package com.af.mongodb.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.af.mongodb.model.Book;
import com.af.mongodb.repository.BookRepository;
import com.af.mongodb.resource.BookAddResource;
import com.af.mongodb.service.BookService;

@Component
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@Override
	public Optional<Book> findById(int id) {
		return bookRepository.findById(id);
	}

	@Override
	public Integer saveBook(BookAddResource bookAddResource) {
		Book book = new Book();
		book.setId(bookAddResource.getId());
		book.setName(bookAddResource.getName());
		book.setPublishedDate(bookAddResource.getPublishedDate());
		bookRepository.save(book);
		return book.getId();
	}

	@Override
	public String deleteBook(int id) {
		bookRepository.deleteById(id);
		return "Book deleted with id : " + id;
	}
	
}
