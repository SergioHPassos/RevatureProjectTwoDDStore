package org.revature.repositories;

import org.revature.entities.Products;
import org.revature.entities.User;

import java.util.ArrayList;

public interface CartDAO {

    ArrayList<Products> getUserCart(User user);

    ArrayList<Products> addToCart(Products product);

    ArrayList<Products> updateCartProduct(Products product);

    ArrayList<Products> deleteCartProduct(Products product);

    




}
