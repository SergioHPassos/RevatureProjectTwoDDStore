package org.revature.service;

import org.revature.entities.Products;
import org.revature.entities.User;

import java.util.ArrayList;

public interface CartService {

    ArrayList<Products> getUserCart(User user);

    ArrayList<Products> addToCart(Products product);

    Products updateCartProduct(Products product);

    ArrayList<Products> deleteCartProduct(Products product);
}
