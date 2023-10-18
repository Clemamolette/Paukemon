package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Pokemon {

    @Id @GeneratedValue
    private Long id;
    @Column
    private String nom;
    @Column
    private String type;
    @Column
    private String image;
    @Column
    private int pv;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getPv() {
        return pv;
    }
    public void setPv(int pv) {
        this.pv = pv;
    }

    @Override
    public String toString() {
        return nom;
    }
}
