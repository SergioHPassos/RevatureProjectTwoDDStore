package org.revature.driver;

import io.javalin.Javalin;
import org.revature.controllers.UserController;
import org.revature.entities.User;
import org.revature.repositories.UserDaoPostgres;
import org.revature.service.UserService;
import org.revature.service.UserServiceImpl;

public class Main {
    // main entry point
    public static User currentUser;
    public static UserService userService = new UserServiceImpl(new UserDaoPostgres());
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
        // start server
        app.start();
    }
}