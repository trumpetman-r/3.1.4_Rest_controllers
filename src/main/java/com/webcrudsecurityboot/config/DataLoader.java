package com.webcrudsecurityboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    private final DatabaseInitializer databaseInitializer;

    @Autowired
    public DataLoader(DatabaseInitializer databaseInitializer) {
        this.databaseInitializer = databaseInitializer;
    }

    @PostConstruct
    public void init() {
        databaseInitializer.populateDatabase();
    }
}