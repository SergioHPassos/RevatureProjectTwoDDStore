package org.revature.controllers;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.revature.driver.Main;
import org.revature.entities.Products;
import org.revature.repositories.CartDAOPostgres;
import org.revature.repositories.ProductDAOPostgres;

import java.util.ArrayList;

public class CartController {
    public Handler addToCart = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Products product = (Products) gson.fromJson(json, Products.class);
        ArrayList<Products> products = Main.cartService.addToCart(product);
        String jsonString = "";
        if(products == null) {
            ctx.status(404);
            ctx.result("Failed to add!");
        } else {
            for (int i = 0; i < products.size(); i++){
                jsonString += products.get(i).toString() + "\n\r";
            }
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
}
