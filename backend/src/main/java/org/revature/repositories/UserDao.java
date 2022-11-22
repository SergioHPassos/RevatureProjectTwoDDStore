package org.revature.repositories;
import org.revature.entities.User;

import java.util.ArrayList;

public interface UserDao {
    User registerUser(User user);
    User loginUser(User user);
    User getCurrentUser();
    User updateUser(User user);
    User getUserByUsername(String username);
    User getUserById(int UserId);

    ArrayList<String> getAllPictures();
}
