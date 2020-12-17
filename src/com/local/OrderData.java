package com.local;

import java.util.ArrayList;

public class OrderData {
    public ArrayList<OrderItemData> getItemList() {
        return itemList;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public String getStatus() {
        return status;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getAddress() {
        return address;
    }

    private ArrayList<OrderItemData> itemList;
    private String orderID;
    private String customerUserName;
    private float totalPrice;
    private String paymentType;
    private String deliveryType;
    public String status;
    public String creditCard;
    public String address;

    public OrderData( String id, String customerUserName, float totalPrice, String paymentType, String deliveryType, String status ) {
        this.orderID = id;
        this.customerUserName = customerUserName;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.deliveryType = deliveryType;
        this.status = status;
        this.itemList = new ArrayList<>();
    }

    public void addItem ( OrderItemData item ) {
        itemList.add( item );
    }

    public void setCreditCard( String creditCard )  {
        this.creditCard = creditCard;
    }

    public void setAddress( String address, String province, String zip_code ) {
        this.address = address + "\n" + province + "\n" + zip_code;
    }
    public void setAddress( String fullAddress ) {
        this.address = address;
    }

    public void setItemList( ArrayList<OrderItemData> list ) {
        itemList = list;
    }
}
