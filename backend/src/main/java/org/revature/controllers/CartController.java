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
            jsonString = gson.toJson(Main.cart);
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
            ctx.result(gson.toJson("Please Login First!"));
        }else if(Main.cart.size() < 1) {
            ctx.status(404);
            ctx.result("Cart is empty!");
        } else {
            jsonString = gson.toJson(Main.cart);
            ctx.status(200);
            ctx.result(jsonString);
        }
    };

    public Handler updateUserCart = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Products product = (Products) gson.fromJson(json, Products.class);
        ArrayList<Products> updatedProduct = Main.cartService.updateCartProduct(product);
        if (Main.currentUser == null){
            ctx.status(400);
            ctx.result("Please Login First!");
        }else if(updatedProduct == null) {
            ctx.status(400);
            ctx.result("Product does not appear in cart, or edited amount is more than is currently in stock.");
        } else {
            ctx.status(200);
            ctx.result(gson.toJson(updatedProduct));
        }
    };

    public Handler deleteCartProduct = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Products product = (Products) gson.fromJson(json, Products.class);
        int previous = 0;
        if (Main.currentUser == null){
            ctx.status(400);
            ctx.result("Please Login First!");
        }else{

            Main.cartService.getUserCart(Main.currentUser);
            previous = Main.cart.size();
            System.out.println(previous);
            Main.cartService.deleteCartProduct(product);
            System.out.println(Main.cart.size());
        }
        String jsonString = "";
       if(previous == Main.cart.size()) {
            ctx.status(400);
            ctx.result("Product was not removed.");
        } else {
           jsonString = gson.toJson(Main.cart);
           ctx.status(200);
           ctx.result(jsonString);
        }
    };

    public Handler checkout = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        // Currently not even using this, using Main.current user instead.
        User user = (User) gson.fromJson(json, User.class);
        String jsonString = Main.cartService.checkout(Main.currentUser);
        if (Main.currentUser == null){
            ctx.status(400);
            ctx.result("Please Login First!");
        }else if(Main.cart.size() < 1) {
            ctx.status(400);
            ctx.result("Your cart is empty!");
        }else if(jsonString == null) {
            ctx.status(400);
            ctx.result("Be sure to login first and have products in your cart!");
        } else {
            ctx.status(200);
            ctx.result(jsonString);
        }
    };
}
