package com.example.vocabluary.with.security.vocabulary.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@AllArgsConstructor
public class VocabularyController {
    @GetMapping("/get")
    public ResponseEntity<String> demoGet(){
        return ResponseEntity.ok("Success");
    }
}
