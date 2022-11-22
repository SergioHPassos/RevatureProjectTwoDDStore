package org.revature.service;

import org.revature.entities.User;

import java.util.ArrayList;

public interface UserService {
    User registerUser(User user);
    User logInUser(User user);
    User getCurrentUser();
    User updateUser(User user);

}
