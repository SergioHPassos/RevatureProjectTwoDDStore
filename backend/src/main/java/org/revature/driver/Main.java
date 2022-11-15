package org.revature.driver;

import io.javalin.Javalin;
import org.revature.controllers.UserController;
import org.revature.entities.Products;
import org.revature.entities.User;
import org.revature.repositories.UserDaoPostgres;
import org.revature.service.UserService;
import org.revature.service.UserServiceImpl;
import org.revature.controllers.ProductController;
import org.revature.repositories.ProductDAOPostgres;
import org.revature.service.ProductService;
import org.revature.service.ProductServicelmpl;

import java.util.ArrayList;

public class Main {
    // instance variables
    public static User currentUser;

    public static ArrayList<Products> cart;
    public static UserService userService = new UserServiceImpl(new UserDaoPostgres());
    public static ProductService productService = new ProductServicelmpl(new ProductDAOPostgres());
    
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

        // start server
        app.start();
    }
}