package com.mkr.aameclibrary.Admin.ui.edit_books;

public class AddNewBookDetails {
    String Title_of_the_book;
    String Authors;
    String Publishers;

    public AddNewBookDetails(String title_of_the_book, String authors, String publishers) {
        Title_of_the_book = title_of_the_book;
        Authors = authors;
        Publishers = publishers;
    }

    public String getTitle_of_the_book() {
        return Title_of_the_book;
    }

    public void setTitle_of_the_book(String title_of_the_book) {
        Title_of_the_book = title_of_the_book;
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String authors) {
        Authors = authors;
    }

    public String getPublishers() {
        return Publishers;
    }

    public void setPublishers(String publishers) {
        Publishers = publishers;
    }
}
