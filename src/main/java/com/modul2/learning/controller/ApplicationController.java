package com.modul2.learning.controller;

import com.modul2.learning.dto.ApplicationDTO;
import com.modul2.learning.dto.mapper.ApplicationMapper;
import com.modul2.learning.entities.Application;
import com.modul2.learning.service.ApplicationService;
import com.modul2.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationService applicationService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ApplicationDTO applicationDTO){
        Application appToCreate = ApplicationMapper.applicationDTO2Application(applicationDTO);
        Application createdApp = applicationService.create(appToCreate);
        return ResponseEntity.ok(ApplicationMapper.application2ApplicationDTO(createdApp));
    }
}
