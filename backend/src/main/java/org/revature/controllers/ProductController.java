package org.revature.controllers;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.revature.driver.Main;
import org.revature.entities.Products;
import org.revature.repositories.ProductDAO;
import org.revature.repositories.ProductDAOPostgres;

import java.util.ArrayList;

public class ProductController {
    public Handler getProducts = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        ProductDAOPostgres productDAO = new ProductDAOPostgres();
        ArrayList<Products> products = productDAO.getProducts();
        String jsonString = "";
         if(products.size() == 0) {
            ctx.status(404);
            ctx.result("No products available!");
        } else {
            for (int i = 0; i < products.size(); i++){
                jsonString += products.get(i).toString() + "\n\r";
            }
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
    public Handler getProductsbyType = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Products product = (Products) gson.fromJson(json, Products.class);
        ArrayList<Products> verifiedproduct = Main.productService.getProductbyDragon(product);
        String jsonString = "";
        if(verifiedproduct.size() == 0) {
            ctx.status(404);
            ctx.result("No products available!");
        } else {
            for (int i = 0; i < verifiedproduct.size(); i++){
                jsonString += verifiedproduct.get(i).toString() + "\n\r";
            }
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
    public Handler getProductsbyTypeAndSubtype = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        ProductDAOPostgres productDAO = new ProductDAOPostgres();
        ArrayList<Products> products = productDAO.getProducts();
        String jsonString = "";
        if(products.size() == 0) {
            ctx.status(404);
            ctx.result("No products available!");
        } else {
            for (int i = 0; i < products.size(); i++){
                jsonString += products.get(i).toString() + "\n\r";
            }
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
}
