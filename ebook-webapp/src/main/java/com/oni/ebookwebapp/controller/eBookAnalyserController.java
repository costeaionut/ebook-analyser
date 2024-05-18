package com.oni.ebookwebapp.controller;

import com.oni.ebookwebapp.shared.Constants;
import com.oni.ebookwebapp.shared.Validators.Format;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyse")
public class eBookAnalyserController {

    @GetMapping
    public ResponseEntity<String> analyzeBookByGutenbergURL(
            @RequestParam
            @Format(regex = Constants.GUTENBERG_URL_REGEX)
            String URL) {
        return new ResponseEntity<>(URL, HttpStatus.OK);
    }

}
