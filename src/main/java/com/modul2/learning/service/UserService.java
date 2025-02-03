package com.modul2.learning.service;

import com.modul2.learning.entities.User;
import com.modul2.learning.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//clasa de business logic (un serviciu pe care aplicatia il ofera)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        if(user.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new user that you want to create");
        }
        return userRepository.save(user);
    }

    public User getById(Long userId){
         return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
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

        updatedUser.setFirst_name(userBody.getFirst_name());
        updatedUser.setLast_name(userBody.getLast_name());
        updatedUser.setUserName(userBody.getUserName());
        updatedUser.setAge(userBody.getAge());

        userRepository.save(updatedUser);

        return updatedUser;

    }


}
