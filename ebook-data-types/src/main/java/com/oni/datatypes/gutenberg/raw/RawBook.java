package com.oni.datatypes.gutenberg.raw;

import com.oni.datatypes.shared.Constants;
import com.oni.datatypes.shared.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawBook {
    private long id;
    private String title;
    private List<Person> authors;
    private List<Person> translators;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
    private String copyright;
    private Map<String, String> formats;

    public String getBookContentUrl(){
        return formats.get(Constants.GUTENBERG_CONTENT_KEY);
    }
}
