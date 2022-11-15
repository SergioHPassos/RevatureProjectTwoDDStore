package org.revature.service;

import org.revature.entities.Products;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<Products> getProductbyType(Products product);

    ArrayList<Products> getProductbyTypeAndSubtype(Products product);

    ArrayList<Products> getProductbyId(int id);
}
