package com.modul2.learning.service;

import com.modul2.learning.entities.Application;
import com.modul2.learning.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application create(Application application) {
        if(application.getId() != null){
            throw new RuntimeException("You cannot provide an ID to a new application that you want to create");
        }
        return applicationRepository.save(application);
    }
}
