package com.local;

public class OrderItemData {
    private String prodName;
    private String orderID;
    private int quantity;
    private float totalPrice;

    public OrderItemData( String prodName, String orderID, int quantity, float price ) {
        this.prodName = prodName;
        this.orderID = orderID;
        this.quantity = quantity;
        this.totalPrice = price;
    }

    public String getProdName() {
        return prodName;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
