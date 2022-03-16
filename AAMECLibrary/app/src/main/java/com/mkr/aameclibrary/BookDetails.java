package com.mkr.aameclibrary;

public class BookDetails {

    String Authors;
    String Publishers;
    Long SRNO;
    String Title_of_the_book;
    BookDetails(){

    }

    public BookDetails(String authors, String publishers, Long SRNO, String title_of_the_book) {
        this.Authors = authors;
        this.Publishers = publishers;
        this.SRNO = SRNO;
        this.Title_of_the_book = title_of_the_book;
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

    public Long getSRNO() {
        return SRNO;
    }

    public void setSRNO(Long SRNO) {
        this.SRNO = SRNO;
    }

    public String getTitle_of_the_book() {
        return Title_of_the_book;
    }

    public void setTitle_of_the_book(String title_of_the_book) {
        Title_of_the_book = title_of_the_book;
    }
}
