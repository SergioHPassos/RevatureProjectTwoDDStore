package org.revature.service;

import org.revature.entities.Products;
import org.revature.entities.User;
import org.revature.repositories.CartDAO;

import java.util.ArrayList;

public class CartServiceImpl implements CartService{

    private CartDAO cartDAO;
    public CartServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public ArrayList<Products> getUserCart(User user) {
        return this.cartDAO.getUserCart(user);
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
