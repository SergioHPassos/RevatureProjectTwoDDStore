package org.revature.repositories;

import org.revature.entities.Products;

import java.util.ArrayList;

public interface ProductDAO {

    ArrayList<Products> getProducts();

    ArrayList<Products> getProductsbyType();

    ArrayList<Products> getProductsbyTypeAndSubtype();

    ArrayList<Products> getProductsbyID();
}
