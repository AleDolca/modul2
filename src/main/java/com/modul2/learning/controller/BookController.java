package com.modul2.learning.controller;

import com.modul2.learning.dto.BookDTO;
import com.modul2.learning.dto.mapper.BookMapper;
import com.modul2.learning.entities.Book;
import com.modul2.learning.service.BookService;
import com.modul2.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    //2 - create children
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody BookDTO bookDto) {
        Book bookToCreate = BookMapper.bookDto2Book(bookDto);
        Book createdBook = bookService.create(bookToCreate);
        return ResponseEntity.ok(BookMapper.book2BookDto(createdBook));
    }

    //4 - create child with parent id as param
    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable Long userId, @RequestBody BookDTO bookDto) {
        Book bookToCreate = BookMapper.bookDto2Book(bookDto);
        Book createdBook = bookService.createWithUserId(userId, bookToCreate);
        return ResponseEntity.ok(BookMapper.book2BookDto(createdBook));
    }
}
