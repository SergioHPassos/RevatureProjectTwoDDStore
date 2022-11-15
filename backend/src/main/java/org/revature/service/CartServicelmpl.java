package org.revature.service;

import org.revature.entities.Products;
import org.revature.entities.User;
import org.revature.repositories.CartDAO;
import org.revature.repositories.ProductDAO;

import java.util.ArrayList;

public class CartServicelmpl implements CartService{

    private CartDAO cartDAO;
    public CartServicelmpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public ArrayList<Products> getUserCart(User user) {
        return null;
    }

    @Override
    public ArrayList<Products> addToCart(Products product) {
        ArrayList<Products> cart = this.cartDAO.addToCart(product);
        return cart;
    }

    @Override
    public ArrayList<Products> updateCartProduct(Products product) {
        return null;
    }

    @Override
    public ArrayList<Products> deleteCartProduct(Products product) {
        return null;
    }
}
