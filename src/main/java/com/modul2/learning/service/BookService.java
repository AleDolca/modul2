package com.modul2.learning.service;

import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.User;
import com.modul2.learning.repository.BookRepository;
import com.modul2.learning.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public Book create(Book book) {
        if(book.getId() != null){
            throw new RuntimeException("You cannot provide an ID to a new application that you want to create");
        }
        return bookRepository.save(book);
    }

    public Book createWithUserId(Long userId, Book book) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityExistsException::new);
        //varianta comentata ar fi fost fara metoda addBook() in User, unde am setat
        //relatia bidirectional
        //user.getBooks().add(bookToCreate);
        //bookToCreate.setUser(user);
        book.setUser(user);
        return bookRepository.save(book);
    }
}
