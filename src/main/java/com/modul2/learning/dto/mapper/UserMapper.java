package com.modul2.learning.dto.mapper;

import com.modul2.learning.dto.UserDTO;
import com.modul2.learning.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Bean
    public UserMapper modelMapper() {
        return new UserMapper();
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAge(user.getAge());
        return dto;
    }


    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        return user;
    }

}
