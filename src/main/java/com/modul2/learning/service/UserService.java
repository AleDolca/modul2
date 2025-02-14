package com.modul2.learning.service;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.entities.Application;
import com.modul2.learning.entities.Book;
import com.modul2.learning.entities.User;
import com.modul2.learning.repository.ApplicationRepository;
import com.modul2.learning.repository.BookRepository;
import com.modul2.learning.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//clasa de business logic (un serviciu pe care aplicatia il ofera)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    public User create(User user) {
        if(user.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new user that you want to create");
        }
        return userRepository.save(user);
    }

    public User getById(Long userId){
         return userRepository.findById(userId)
                //.orElseThrow(EntityNotFoundException::new);
                .orElseThrow(() -> new EntityNotFoundException("User with id %s not found".formatted(userId)));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }

    public User updateById(Long userId, User userBody){
        User updatedUser = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        updatedUser.setFirstName(userBody.getFirstName());
        updatedUser.setLastName(userBody.getLastName());
        updatedUser.setUserName(userBody.getUserName());
        updatedUser.setAge(userBody.getAge());

        return userRepository.save(updatedUser);
    }

    public User addBookToUser(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        user.addBook(book);
        return userRepository.save(user);
    }

    public void removeBookFromUser(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book bookToRemove = user.getBooks().stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found for this user"));

        user.getBooks().remove(bookToRemove);
        //sa setezi pe null userul in  bookToRemove
        //sau implementezi un remove special in user in care sa le faci pe ambele
        userRepository.save(user);
    }

    public User addAppToUser(long userId, Long appId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Application application = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("App not found"));

        user.addApplication(application);
        return userRepository.save(user);
    }
}
