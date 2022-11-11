package org.revature.driver;

import io.javalin.Javalin;
import org.revature.controllers.ProductController;
import org.revature.repositories.ProductDAOPostgres;
import org.revature.service.ProductService;
import org.revature.service.ProductServicelmpl;


public class Main {
    public static ProductService productService = new ProductServicelmpl(new ProductDAOPostgres());
    // main entry point
    public static void main(String[] args) {
        // instance Javalin server object
        Javalin app = Javalin.create();
        ProductController productController = new ProductController();
        app.get("/getProducts", productController.getProducts);
        app.post("/getProductsbyType", productController.getProductsbyType);
        app.post("/getProductsbyTypeAndSubtype", productController.getProductsbyTypeAndSubtype);
        app.post("/getProductsbyId", productController.getProductsbyid);



        // start server
        app.start();

    }
}