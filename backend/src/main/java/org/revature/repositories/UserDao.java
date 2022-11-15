package org.revature.repositories;

import org.revature.entities.User;

public interface UserDao {
    User registerUser(User user);
    User loginUser(User user);
    User getCurrentUser();
    User updateUser(User user);
    User getUserByUsername(String username);
    User getUserById(int UserId);
}
