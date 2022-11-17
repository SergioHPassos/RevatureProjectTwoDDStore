package org.revature.service;

import org.revature.driver.Main;
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
    public Products updateCartProduct(Products product) {
        if (product.getName().trim().equals("")){
            throw new RuntimeException("Product needs a name.");
        }
        if(product.getCartAmount() < 0){
            throw new RuntimeException("Product amount cannot be negative.");
        }
        return this.cartDAO.updateCartProduct(product);
    }

    @Override
    public ArrayList<Products> deleteCartProduct(Products product) {
        return this.cartDAO.deleteCartProduct(product);
    }

    @Override
    public String checkout(User user) {
        return this.cartDAO.checkout(Main.currentUser);
    }
}
