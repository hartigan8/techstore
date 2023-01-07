package com.example.techstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.Log;
import com.example.techstore.repositories.LogRepo;

@RestController
public class LogController {

    @Autowired
    private LogRepo logRepo;
    
    @GetMapping("logs")
    @PreAuthorize("hasAuthority('admin')")
    public List<Log> getLogs() {
        return logRepo.findAll();
    }
}
