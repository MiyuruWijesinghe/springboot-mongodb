package com.af.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.af.mongodb.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
