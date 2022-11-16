package org.revature.controllers;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.revature.driver.Main;
import org.revature.entities.Products;
import org.revature.entities.User;
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

    public Handler getUserCart = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        // Currently not even using this, using Main.current user instead.
        User user = (User) gson.fromJson(json, User.class);
        Main.cartService.getUserCart(Main.currentUser);
        String jsonString = "";
        if (Main.currentUser == null){
            ctx.status(400);
            ctx.result("Please Login First!");
        }else if(Main.cart.size() < 1) {
            ctx.status(404);
            ctx.result("Cart is empty!");
        } else {
            for (int i = 0; i < Main.cart.size(); i++){
                jsonString += Main.cart.get(i).toString() + "\n\r";
            }
            ctx.status(200);
            ctx.result(jsonString);
        }
    };

    public Handler updateUserCart = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        // Currently not even using this, using Main.current user instead.
        Products product = (Products) gson.fromJson(json, Products.class);
        Products updatedProduct = Main.cartService.updateCartProduct(product);
        if (Main.currentUser == null){
            ctx.status(400);
            ctx.result("Please Login First!");
        }else if(updatedProduct == null) {
            ctx.status(400);
            ctx.result("Product does not appear in cart, or edited amount is more than is currently in stock.");
        } else {
            ctx.status(200);
            ctx.result(""+product);
        }
    };
}
