package com.oni.ebookwebapp.controller;

import com.oni.book.gutenberg.GutenbergExternalAPI;
import com.oni.datatypes.shared.Constants;
import com.oni.ebookwebapp.shared.Validators.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyse")
public class eBookAnalyserController {

    private final GutenbergExternalAPI gutenbergExternalAPI;

    @Autowired
    public eBookAnalyserController(GutenbergExternalAPI gutenbergExternalAPI) {
        this.gutenbergExternalAPI = gutenbergExternalAPI;
    }

    @GetMapping
    public ResponseEntity<String> analyzeBookByGutenbergURL(
            @RequestParam("URL")
            @Format(message = "Invalid Gutenberg URL", regex = Constants.GUTENBERG_URL_REGEX)
            String URL) {

        String jsonBook =
                gutenbergExternalAPI
                        .getBookDetails(retrieveBookId(URL))
                        .block();
        return new ResponseEntity<>(jsonBook, HttpStatus.OK);
    }

    private int retrieveBookId(String URL) {
        String[] urlParts = URL.split("/");
        return Integer.parseInt(urlParts[urlParts.length - 1]);
    }

}
