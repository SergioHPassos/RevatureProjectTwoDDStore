package org.revature.service;

import org.revature.entities.Products;
import org.revature.repositories.ProductDAO;

import java.util.ArrayList;

public class ProductServicelmpl implements ProductService{

    private ProductDAO productDAO;
    public ProductServicelmpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ArrayList<Products> getProductbyType(Products product) {
        if(product == null){
            return null;
        } else if(product.getType().trim().equals("")){
            throw new RuntimeException("Product needs a type.");
        } else{
            ArrayList<Products> verifiedProducts = this.productDAO.getProductsbyType(product.getType());
            return verifiedProducts;
        }
    }

    @Override
    public ArrayList<Products> getProductbyTypeAndSubtype(Products product) {
        if(product == null){
            return null;
        } else if(product.getType().trim().equals("")){
            throw new RuntimeException("Product needs a type.");
        } else{
            ArrayList<Products> verifiedProducts = this.productDAO.getProductsbyType(product.getType());
            return verifiedProducts;
        }
    }

    @Override
    public ArrayList<Products> getProductbyId(int id) {
        if(id < 1){
            return null;
        } else{
            ArrayList<Products> verifiedProducts = this.productDAO.getProductsbyID(id);
            return verifiedProducts;
        }
    }
}
