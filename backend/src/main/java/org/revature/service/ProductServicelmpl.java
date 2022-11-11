package org.revature.service;

import org.revature.entities.Products;
import org.revature.repositories.ProductDAO;

import java.util.ArrayList;

public class ProductServicelmpl implements ProductService{

    private ProductDAO productDAO;
    public ProductServicelmpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

}
