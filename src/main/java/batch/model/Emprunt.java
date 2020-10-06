package batch.model;

import java.util.Date;

public class Emprunt {

    private Long id;
    private Date empruntDate;
    private Date returnDate;
    private Boolean isExtended;
    private Copy copy;
    private Customer customer;

    public Emprunt() {
    }

    public Emprunt(final Long id, final Date empruntDate, final Date returnDate, final Boolean isExtended, final Copy copy, final Customer customer) {
        this.id = id;
        this.empruntDate = empruntDate;
        this.returnDate = returnDate;
        this.isExtended = isExtended;
        this.copy = copy;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getEmpruntDate() {
        return empruntDate;
    }

    public void setEmpruntDate(final Date empruntDate) {
        this.empruntDate = empruntDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final Date returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getExtended() {
        return isExtended;
    }

    public void setExtended(final Boolean extended) {
        isExtended = extended;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(final Copy copy) {
        this.copy = copy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
}
