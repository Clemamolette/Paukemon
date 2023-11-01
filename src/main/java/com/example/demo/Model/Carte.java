package com.example.demo.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

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
    private String images;
    @Column
    private String serie;
    @Column
    private Integer quantity;
    @Column
    private boolean acquired;


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
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public String getSerie() {
        return serie;
    }
    public void setSerie(String serie) {
        this.serie = serie;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public boolean isAcquired() {
        return acquired;
    }
    public void setAcquired(boolean acquired) {
        this.acquired = acquired;
    }
}
