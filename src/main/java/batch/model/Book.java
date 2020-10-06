package batch.model;

import java.util.Date;

public class Book {

    private Long id;
    private String title;
    private Date pubDate;
    private int page;
    private String synopsis;

    public Book() {
    }

    public Book(final Long id, final String title, final Date pubDate, final int page, final String synopsis) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.page = page;
        this.synopsis = synopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(final Date pubDate) {
        this.pubDate = pubDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(final String synopsis) {
        this.synopsis = synopsis;
    }
}
