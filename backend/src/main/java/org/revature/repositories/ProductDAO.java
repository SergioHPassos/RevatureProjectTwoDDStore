package org.revature.repositories;

import org.revature.entities.Products;

import java.util.ArrayList;

public interface ProductDAO {

    ArrayList<Products> getProducts();

    ArrayList<Products> getProductsbyType(String type);

    ArrayList<Products> getProductsbyTypeAndSubtype(String type, String subtype);

    ArrayList<Products> getProductsbyID(int id);
}
