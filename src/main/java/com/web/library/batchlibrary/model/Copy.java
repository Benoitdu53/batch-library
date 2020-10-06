package com.web.library.batchlibrary.model;

public class Copy {

    private Long id;
    private String format;
    private String status;
    private Book book;
    private Library library;

    public Copy() {
    }

    public Copy(final Long id, final String format, final String status, final Book book, final Library library) {
        this.id = id;
        this.format = format;
        this.status = status;
        this.book = book;
        this.library = library;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(final Library library) {
        this.library = library;
    }
}
