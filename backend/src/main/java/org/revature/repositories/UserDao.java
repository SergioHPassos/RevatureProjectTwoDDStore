package org.revature.repositories;

import org.revature.entities.User;

public interface UserDao {
    User registerUser(User user);
    User loginUser(User user);
    User getCurrentUser();
    User updateUser(User user);
    User getUserByUsername(String username);
//    Stock getAllProducts();
//    Stock getProductByTypeAndSubType(Stock type, Stock subType);
//    Stock getProductById(int ItemId);
    User getUserById(int UserId);
//    Stock getCartByUserId(int UserId);
//    Cart addItemToCart(Cart cart);
//    Cart updateItemQuantityInCart(Cart cart);
//    Cart deleteItemFrontCart(Cart cart);
}
