package com.web.library.batchlibrary.model;

public class Library {

    private Long id;
    private String nom;
    private String adress;
    private String phoneNum;
    private String email;

    public Library() {
    }

    public Library(final Long id, final String nom, final String adress, final String phoneNum, final String email) {
        this.id = id;
        this.nom = nom;
        this.adress = adress;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(final String adress) {
        this.adress = adress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(final String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
