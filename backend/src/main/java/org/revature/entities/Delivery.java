package org.revature.entities;

import java.util.ArrayList;

public class Delivery {
    int id;
    String username;
    int price;
    int cartcount;
    ArrayList<Products> items;
    String address;
    String orderDate;
    String deliveryDate;

    public Delivery() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCartcount() {
        return cartcount;
    }

    public void setCartcount(int cartcount) {
        this.cartcount = cartcount;
    }

    public ArrayList<Products> getItems() {
        return items;
    }

    public void setItems(ArrayList<Products> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", price=" + price +
                ", cartcount=" + cartcount +
                ", items=" + items +
                ", address='" + address + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}
