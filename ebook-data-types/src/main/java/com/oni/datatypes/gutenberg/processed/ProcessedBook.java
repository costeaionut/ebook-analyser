package com.oni.datatypes.gutenberg.processed;

import com.oni.datatypes.gutenberg.raw.RawBook;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedBook {
    private BookDetails details;
    private List<String> contentTokens;

    public ProcessedBook(RawBook rawBook, List<String> contentTokens) {
        this.details = new BookDetails(rawBook);
        this.contentTokens = contentTokens;
    }
}
