package com.example.cartes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Card implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String text;
    private String traits;
    private String imagesrc;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    @Override
    public String toString() {
        return "Card{" +
                "text='" + text + '\'' +
                ", traits='" + traits + '\'' +
                ", imagesrc='" + imagesrc + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
