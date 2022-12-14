package org.revature.controllers;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
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
         if(products.size() == 0) {
            ctx.status(404);
            ctx.result("No products available!");
        } else {
            String jsonString = gson.toJson(products);
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
    public Handler getProductsbyType = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Products product = (Products) gson.fromJson(json, Products.class);
        ArrayList<Products> verifiedproduct = Main.productService.getProductbyType(product);
        if(verifiedproduct.size() == 0) {
            ctx.status(404);
            ctx.result("No products available!");
        } else {
            String jsonString = gson.toJson(verifiedproduct);
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
    public Handler getProductsbyTypeAndSubtype = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Products product = (Products) gson.fromJson(json, Products.class);
        ArrayList<Products> verifiedproduct = Main.productService.getProductbyTypeAndSubtype(product);
        if(verifiedproduct.size() == 0) {
            ctx.status(404);
            ctx.result("No products available!");
        } else {
            String jsonString = gson.toJson(verifiedproduct);
            ctx.status(200);
            ctx.result(jsonString);
        }
    };

    public Handler getProductsbyid = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Products product = (Products) gson.fromJson(json, Products.class);
        ArrayList<Products> verifiedproduct = Main.productService.getProductbyId(product.getId());
        if(verifiedproduct.size() == 0) {
            ctx.status(404);
            ctx.result("No products available!");
        } else {
            String jsonString = gson.toJson(verifiedproduct);
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
}
