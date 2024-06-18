package com.oni.datatypes.shared;

public final class Constants {

    private Constants() {

    }

    public static final String GUTENBERG_URL_REGEX = "^(https:\\/\\/)?www\\.gutenberg\\.org\\/ebooks\\/\\d+$";
    public static final String GUTENBERG_API_URL = "https://gutendex.com/books/";
    public static final String GUTENBERG_CONTENT_KEY = "text/plain; charset=us-ascii";
}
