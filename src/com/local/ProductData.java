package com.local;

public class ProductData {
    private byte[] image;
    private String name;
    private String description;


    public String getCategory() {
        return category;
    }

    private String category;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
    private float price;

    public byte[] getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public ProductData(String name, String description, int quantity, float price, String category ) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public ProductData( ProductData data ) {
        this.name = data.getName();
        this.description = data.getDescription();
        this.quantity = data.getQuantity();
        this.price = data.getPrice();
        this.category = data.getCategory();
        this.image = data.getImage();
    }

    public void setImage( byte[] image ) {
        this.image = image;
    }
}
