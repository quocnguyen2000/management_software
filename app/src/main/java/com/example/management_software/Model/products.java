package com.example.management_software.Model;

public class products {
    public String  id;
    public String name;
    public Double price;
    public String description;

    public products() {
    }

    public products(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
