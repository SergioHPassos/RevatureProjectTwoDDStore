package org.revature.service;

import org.revature.driver.Main;
import org.revature.entities.User;
import org.revature.repositories.UserDao;

public class UserServiceImpl implements UserService{
    private UserDao userDao;
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User registerUser(User user) {
        return userDao.registerUser(user);
    }

    @Override
    public User logInUser(User user) {
        return userDao.loginUser(user);
    }

    @Override
    public User getCurrentUser() {
        return Main.currentUser;
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }
}
