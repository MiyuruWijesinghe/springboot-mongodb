package com.af.mongodb.service;

import java.util.List;
import java.util.Optional;
import com.af.mongodb.model.Book;
import com.af.mongodb.resource.BookAddResource;

public interface BookService {

	public List<Book> findAll();
	
	public Optional<Book> findById(int id);
	
	public Integer saveBook(BookAddResource bookAddResource);
	
	public String deleteBook(int id);
	
}
