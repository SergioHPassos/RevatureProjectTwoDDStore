package org.revature.driver;

import io.javalin.Javalin;
import org.revature.controllers.CartController;
import org.revature.controllers.UserController;
import org.revature.entities.Products;
import org.revature.entities.User;
import org.revature.repositories.CartDAOPostgres;
import org.revature.repositories.UserDaoPostgres;
import org.revature.service.*;
import org.revature.controllers.ProductController;
import org.revature.repositories.ProductDAOPostgres;

import java.util.ArrayList;

public class Main {
    // instance variables
    public static User currentUser;

    public static ArrayList<Products> cart = new ArrayList<>();
    public static UserService userService = new UserServiceImpl(new UserDaoPostgres());
    public static ProductService productService = new ProductServiceImpl(new ProductDAOPostgres());
    public static CartService cartService = new CartServiceImpl(new CartDAOPostgres());
    
    // entry point
    public static void main(String[] args) {
        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins(); //lets us process HTTP requests from anywhere
                }
                //may not need to do this if you've containerized? Patrick may know lol
        );
        
        UserController userController = new UserController();
        app.post("/registerUser", userController.registerUser);
        app.post("/logInUser", userController.logInUser);
        app.get("/getCurrentUser", userController.getCurrentUser);
        app.put("/updateUser", userController.updateUser);

        ProductController productController = new ProductController();
        app.get("/getProducts", productController.getProducts);
        app.post("/getProductsbyType", productController.getProductsbyType);
        app.post("/getProductsbyTypeAndSubtype", productController.getProductsbyTypeAndSubtype);
        app.post("/getProductsbyId", productController.getProductsbyid);

        CartController cartController = new CartController();
        app.post("/addToCart", cartController.addToCart);
        app.get("/getUserCart", cartController.getUserCart);
        app.post("/updateCart", cartController.updateUserCart);
        app.post("/deleteCartProduct", cartController.deleteCartProduct);
        app.post("/checkout", cartController.checkout);

        // start server
        app.start();
    }
}