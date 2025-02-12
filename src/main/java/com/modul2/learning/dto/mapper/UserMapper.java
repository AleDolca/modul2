package com.modul2.learning.dto.mapper;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.entities.Application;
import com.modul2.learning.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class UserMapper {
    public static User userDTO2User(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setUserName(userDTO.getUserName());
        user.setBooks(userDTO.getBooks() != null ?
                userDTO.getBooks().stream()
                        .map(BookMapper::bookDto2Book)
                        .peek(book -> book.setUser(user))
                        .toList()
                : new ArrayList<>());
        user.setApplications(userDTO.getApplications() != null ?
                userDTO.getApplications().stream()
                        .map(ApplicationMapper::applicationDTO2Application)
                        .toList() : new ArrayList<>());
        return user;
    }

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setUserName(user.getUserName());
        userDTO.setBooks(user.getBooks().stream()
                .map(BookMapper::book2BookDto)
                .toList());
        userDTO.setApplications(user.getApplications().stream()
                .map(ApplicationMapper::application2ApplicationDTO)
                .toList());
        return userDTO;
    }
}
