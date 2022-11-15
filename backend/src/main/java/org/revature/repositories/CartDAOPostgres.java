package org.revature.repositories;

import org.revature.driver.Main;
import org.revature.entities.Products;
import org.revature.entities.User;
import org.revature.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CartDAOPostgres implements CartDAO {
    @Override
    public ArrayList<Products> getUserCart(User user) {
        return null;
    }

    @Override
    public ArrayList<Products> addToCart(Products product) {
        if (Main.currentUser == null) {
            return null;
        }
        try (Connection connection = DBConnection.getConnection()) {
            Products checkedProduct = new Products();
            // Get product to check the amount of stock.
            String sql = "select * from stock where itemid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                checkedProduct.setId(rs.getInt("itemId"));
                checkedProduct.setName(rs.getString("itemname"));
                checkedProduct.setDescription(rs.getString("description"));
                checkedProduct.setPrice(rs.getInt("price"));
                checkedProduct.setStock(rs.getInt("stockcount"));
                checkedProduct.setType(rs.getString("itemtype"));
                checkedProduct.setSubtype(rs.getString("itemsubtype"));
                checkedProduct.setRarity(checkedProduct.getRarity().valueOf(rs.getString("rarity")));
                checkedProduct.setDiscount(rs.getBoolean("discount"));
                checkedProduct.setImage(rs.getBytes("image"));
            }
            // Return null if there is not enough stock.
            if (checkedProduct.getStock() < product.getCartAmount()){
                return null;
            }
            // Updates the stock to take out the number of items added to a cart.
            sql = "update stock set stockcount = ? where itemid = ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, checkedProduct.getStock() - product.getCartAmount());
            preparedStatement.setInt(2, product.getId());
            int result = preparedStatement.executeUpdate();
            if (result == 0){
                return null;
            }
            //TODO: Check if product already exists in cart, if it does just update rather than insert.

            // Finally insert into carts if there is currently nothing to update.
            sql = "insert into carts values(default, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, checkedProduct.getName());
            preparedStatement.setInt(2, checkedProduct.getPrice());
            preparedStatement.setInt(3, product.getCartAmount());
            preparedStatement.setString(4, checkedProduct.getType());
            preparedStatement.setString(5, checkedProduct.getSubtype());
            preparedStatement.setString(6, Main.currentUser.getUsername());
            preparedStatement.setBytes(7, checkedProduct.getImage());
            preparedStatement.execute();

            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            Main.cart.add(product);
            return Main.cart;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Products> updateCartProduct(Products product) {
        return null;
    }

    @Override
    public ArrayList<Products> deleteCartProduct(Products product) {
        return null;
    }
}
