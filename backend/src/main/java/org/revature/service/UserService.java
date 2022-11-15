package org.revature.service;

import org.revature.entities.User;

public interface UserService {
    User registerUser(User user);
    User logInUser(User user);
    User getCurrentUser();
    User updateUser(User user);
}
