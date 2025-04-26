package com.ttknpdev.understandjwth2databasehelloworld.repository;

import com.ttknpdev.understandjwth2databasehelloworld.entities.Book;
import org.springframework.data.repository.CrudRepository;

// use passed @Service So it do not need to set it with @Repository
public interface BookRepository extends CrudRepository<Book,String> { }
