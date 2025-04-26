package com.ttknpdev.understandjwth2databasehelloworld.controller;

import com.ttknpdev.understandjwth2databasehelloworld.dao.BookDao;
import com.ttknpdev.understandjwth2databasehelloworld.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins= {"http://localhost:4200","http://thitikorn-nupan.com:4201","http://localhost:4201"}) // very importance it works like module cors in node
// /ttknpdev/book-store/programing/reads (alex)
public class PrivateApi {
    private BookDao bookDao;
    private final String BOOK_PROGRAMING_API = "/book-store/programing/";
    @Autowired
    public PrivateApi(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @GetMapping(value = "/test")
    private ResponseEntity<?> test() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("hello world!!");
    }
    @GetMapping(value = BOOK_PROGRAMING_API+"reads")
    private ResponseEntity<?> programingBooks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDao.reads());
    }
    @GetMapping(value = BOOK_PROGRAMING_API+"read/{bid}")
    private ResponseEntity<?> programingBook(@PathVariable String bid) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDao.read(bid));
    }
    @PostMapping(value = BOOK_PROGRAMING_API+"create")
    private ResponseEntity<?> programingBook(@RequestBody Book book) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookDao.create(book));
    }
    @PutMapping(value = BOOK_PROGRAMING_API+"update/{bid}")
    private ResponseEntity<?> programingBookUpdate(@RequestBody Book book,
                                             @PathVariable String bid) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(bookDao.update(book,bid));
    }
    @DeleteMapping(value = BOOK_PROGRAMING_API+"delete/{bid}")
    private ResponseEntity<?> programingBookDelete(@PathVariable String bid) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDao.delete(bid));
    }
}
