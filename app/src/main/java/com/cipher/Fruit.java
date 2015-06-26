package com.cipher;

import java.io.Serializable;

/**
 * Created by Eduardo Gorio on 26/06/2015.
 */
public class Fruit implements Serializable {
    public String title;
    public String description;
    public double valor;
    public String image;

    public Fruit() {
    }

    public Fruit(String title, String description, double valor, String image) {
        this.title = title;
        this.description = description;
        this.valor = valor;
        this.image = image;
    }

    @Override
    public String toString() {
        return title;
    }
}
