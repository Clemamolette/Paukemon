package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Carte {
    @Id
    private Long id;



    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
