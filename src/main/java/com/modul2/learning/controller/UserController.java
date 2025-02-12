package com.modul2.learning.controller;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.dto.mapper.UserMapper;
import com.modul2.learning.entities.User;
import com.modul2.learning.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//operatii de tipul CRUD - create, read, update, delete
//poate primi request de http
@RestController
//folosim forma de plural la endpoints
@RequestMapping("/users")
public class UserController {
    //dependency injection + inversion of control
    //un userService se creeaza automat la crearea unui userController
    //bean - un object manage-uit de SpringBoot
    @Autowired
    private UserService userService;


    //1 - create parent with/without children
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);
        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }

    //3 - add existing child to parent
    @PutMapping("/{userId}/add-book/{bookId}")
    public ResponseEntity<?> addBookToUser(@PathVariable Long userId, @PathVariable Long bookId) {
        User updatedUser = userService.addBookToUser(userId, bookId);
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }

    //6 - remove child from parent
    @DeleteMapping("/{userId}/remove-book/{bookId}")
    public ResponseEntity<?> removeBookFromUser(@PathVariable Long userId, @PathVariable Long bookId) {
        userService.removeBookFromUser(userId, bookId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/with-apps")
    public ResponseEntity<?> createWithApplications(@RequestBody UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);
        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }

    @PutMapping("{userId}/add-app/{appId}")
    public ResponseEntity<?> addAppToUser(@PathVariable long userId, @PathVariable Long appId){
        User updatedUser = userService.addAppToUser(userId, appId);
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }

    //returnam un user dupa id
    //id ul il pun in path/cale in postman, pentru ca un Get nu are Request Body (doar response body)
    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable(name = "userId") Long userIdToSearchFor) {
        User foundUser = userService.getById(userIdToSearchFor);
        return ResponseEntity.ok(UserMapper.user2UserDTO(foundUser));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users.stream().map(UserMapper::user2UserDTO).toList());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "userId") Long userIdToDelete) {
        userService.deleteById(userIdToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateById(@PathVariable(name = "userId") Long userIdToUpdate, @RequestBody UserDTO userBody) {
        User userEntity = UserMapper.userDTO2User(userBody);
        User updatedUser = userService.updateById(userIdToUpdate, userEntity);
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }
}

