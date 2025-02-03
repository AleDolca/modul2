package com.modul2.learning.controller;

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

    //cand cream date, folosim POST (daca avem un singur tip de POST, nu avem nevoie de ce e in paranteza "/create")
    //POST reprezinta automat crearea unei noi entitati
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User user) {
        //comment daca am avea parametrul ca UserDTO
        //pas1: il convertesc intr-o entitate User (printr-o clasa Mapper)
        //pas2: linia de mai  jos
        User createdUser = userService.create(user);
        //pas3: convertesc entitatea din nou intr-un DTO
        return ResponseEntity.ok(createdUser);
    }

    //returnam un user dupa id
    //id ul il pun in path/cale, pentru ca un Get nu are Request Body (doar response body)
    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable(name="userId") Long userIdToSearchFor) {
        User foundUser = userService.getById(userIdToSearchFor);
        return ResponseEntity.ok(foundUser);

    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable(name="userId") Long userIdToDelete) {
        userService.deleteById(userIdToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateById(@PathVariable(name="userId") Long userIdToUpdate, @RequestBody User userBody){
        User updatedUser = userService.updateById(userIdToUpdate, userBody);
        return ResponseEntity.ok(updatedUser);
    }
}

