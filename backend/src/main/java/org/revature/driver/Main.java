package org.revature.driver;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        // instance Javalin server object
        Javalin app = Javalin.create();

        // start server
        app.start();

    }
}