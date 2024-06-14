package com.oni.datatypes.gutenberg.processed;

import com.oni.datatypes.gutenberg.raw.RawBook;
import com.oni.datatypes.shared.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDetails {
    private long id;
    private String title;
    private List<Person> authors;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
    private Boolean copyright;

    public BookDetails(RawBook rawBook){
        this.id = rawBook.getId();
        this.title = rawBook.getTitle();
        this.authors = rawBook.getAuthors();
        this.subjects = rawBook.getSubjects();
        this.bookshelves = rawBook.getBookshelves();
        this.languages = rawBook.getLanguages();
        this.copyright = Boolean.valueOf(rawBook.getCopyright());
    }
}
