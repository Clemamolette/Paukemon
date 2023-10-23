package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Carte {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String hp;
    @Column
    private String type;
    @Column
    private String rarity;
    @Column
    private String image;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHp() {
        return hp;
    }
    public void setHp(String hp) {
        this.hp = hp;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRarity() {
        return rarity;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
