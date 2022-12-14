package org.revature.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.http.Handler;
import org.postgresql.Driver;
import org.revature.driver.Main;
import org.revature.entities.User;
import org.revature.repositories.UserDao;
import org.revature.repositories.UserDaoPostgres;

import java.util.ArrayList;

public class UserController {
    public Handler registerUser = (ctx) ->{
        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        User newUser = Main.userService.registerUser(user);
        if(newUser == null){
            ctx.status(409); //This status code is for conflict.
            ctx.result("User is already in use.");
        }else {
            //DON:T EXPOSE SENSITIVE INFO
            String userJason = gson.toJson(newUser);
            ctx.status(201); //This is a status code that will tell us how things went
            ctx.result(userJason);
        }
    };

    public Handler logout = (ctx) ->{
        System.out.print("Hello world");
        if(Main.currentUser == null){
            ctx.status(400); //This is a status code that will tell us how things went
            ctx.result("You are not logged in!");
        } else {
            Main.currentUser = null;
            ctx.status(201); //This is a status code that will tell us how things went
            ctx.result("Logged out.");
        }
    };

    public Handler logInUser = (ctx) ->{
        String loginjson = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(loginjson, User.class);
        User newUser = Main.userService.logInUser(user);
        if(newUser == null){
            ctx.status(409); //This status code is for conflict.
            ctx.result("Your username or password is invalid.");
        }else {
            String userJason = gson.toJson(newUser);
            ctx.status(201); //This is a status code that will tell us how things went
            ctx.result(userJason);
        }
    };

    public Handler getCurrentUser = (ctx) ->{
        Gson gson = new Gson();
        User currentUser = Main.userService.getCurrentUser();
        if(currentUser == null){
            ctx.status(409); //This status code is for conflict.
            ctx.result("There is no current user");
        }else {
            String userJason = gson.toJson(currentUser);
            ctx.status(201); //This is a status code that will tell us how things went
            ctx.result(userJason);
        }
    };

    public Handler updateUser = (ctx) ->{
        String userJSON = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(userJSON, User.class);
        User updatedUser = Main.userService.updateUser(user);
        if (updatedUser != null) {
            ctx.status(201); //This is a status code that will tell us how things went
            ctx.result(gson.toJson(updatedUser));
        }
        else {
            ctx.status(409);
            ctx.result("Update failed");
        }
    };

    public Handler getAllPictures = (ctx) ->{
        Gson gson = new Gson();
        UserDao userDao = new UserDaoPostgres();
        ArrayList<String> pictures = userDao.getAllPictures();
        ctx.status(201); //This is a status code that will tell us how things went
        ctx.result(pictures.toString());
    };

}
