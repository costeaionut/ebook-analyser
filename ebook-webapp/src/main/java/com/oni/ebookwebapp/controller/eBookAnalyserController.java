package com.oni.ebookwebapp.controller;

import com.oni.book.gutenberg.GutenbergExternalAPI;
import com.oni.datatypes.gutenberg.processed.ProcessedBook;
import com.oni.datatypes.gutenberg.raw.RawBook;
import com.oni.datatypes.shared.Constants;
import com.oni.ebookwebapp.shared.Validators.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/analyse")
public class eBookAnalyserController {

    private final GutenbergExternalAPI gutenbergExternalAPI;

    @Autowired
    public eBookAnalyserController(GutenbergExternalAPI gutenbergExternalAPI) {
        this.gutenbergExternalAPI = gutenbergExternalAPI;
    }

    @GetMapping
    public ResponseEntity<ProcessedBook> analyzeBookByGutenbergURL(
            @RequestParam("URL")
            @Format(message = "Invalid Gutenberg URL", regex = Constants.GUTENBERG_URL_REGEX)
            String URL) {

        RawBook rawBook = gutenbergExternalAPI.getRawBook(retrieveBookId(URL));
        String rawBookContents = gutenbergExternalAPI.getBookContents(rawBook.getBookContentUrl());
        ProcessedBook book = new ProcessedBook(rawBook, tokenizeBookContents(rawBook.getTitle(), rawBookContents));

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    private int retrieveBookId(String URL) {
        String[] urlParts = URL.split("/");
        return Integer.parseInt(urlParts[urlParts.length - 1]);
    }

    private List<String> tokenizeBookContents(String bookTitle, String rawBookContent) {

        String beginningDelimiter =
                String.format("\\*\\*\\* START OF THE PROJECT GUTENBERG EBOOK %s \\*\\*\\*", bookTitle.toUpperCase());
        String endDelimiter =
                String.format("\\*\\*\\* END OF THE PROJECT GUTENBERG EBOOK %s \\*\\*\\*", bookTitle.toUpperCase());

        // Step 1: Trim Gutenberg beginning and end delimiters
        String beginningTrimmedBook = rawBookContent.split(beginningDelimiter)[1];
        String trimmedBookContent = beginningTrimmedBook.split(endDelimiter)[0].trim();

        // Step 2: Sanitize trimmed book content
        String regex = "[.,!?()\\[\\]{}#\\-—'’\"]|'s|’s";
        String sanitizedBookContent = trimmedBookContent.replaceAll(regex, " ").toLowerCase(Locale.ROOT);

        // Step 3: Tokenize book content and remove common english words

        return Arrays.asList(sanitizedBookContent.split("\\s+"));
    }

}
