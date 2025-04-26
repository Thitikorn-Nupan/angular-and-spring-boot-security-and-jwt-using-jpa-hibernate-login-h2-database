package com.ttknpdev.understandjwth2databasehelloworld.dao;

import com.ttknpdev.understandjwth2databasehelloworld.entities.Book;
import com.ttknpdev.understandjwth2databasehelloworld.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookDao {

    private final BookRepository bookRepository;

    @Autowired
    public BookDao(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> reads() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> read(String bid) {
        return bookRepository.findById(bid);
    }

    public Book create (Book book) {
        return bookRepository.save(book);
    }

    public Map<String,Book> update(Book book , String bid){
        Map<String,Book> response = new HashMap<>();
        return bookRepository.findById(bid).map(oldBook ->  {
            oldBook.setTitle(book.getTitle());
            oldBook.setPrice(book.getPrice());
            bookRepository.save(oldBook);
            response.put("updated",oldBook);
            return response;
        }).orElseThrow(RuntimeException::new);
    }

    public Map<String,Boolean> delete(String bid) {
        Map<String,Boolean> response = new HashMap<>();
        return bookRepository.findById(bid).map(book -> {
            bookRepository.deleteById(bid);
            response.put("deleted",true);
            return response;
        }).orElseThrow(RuntimeException::new);
    }
}
