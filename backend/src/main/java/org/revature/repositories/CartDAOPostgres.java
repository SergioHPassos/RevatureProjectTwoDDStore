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
        if (Main.currentUser == null) {
            return null;
        }
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "select * from carts where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, Main.currentUser.getUsername());
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setName(rs.getString("itemname"));
                product.setPrice(rs.getInt("price"));
                product.setCartAmount(rs.getInt("cartcount"));
                product.setType(rs.getString("itemtype"));
                product.setSubtype(rs.getString("itemsubtype"));
                product.setImage(rs.getBytes("image"));
                Main.cart.add(product);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Products> addToCart(Products product) {
        if (Main.currentUser == null) {
            return null;
        }
        // Variables used later in the code and refresh user cart.
        getUserCart(Main.currentUser);
        boolean cartcontains = false;
        int cartNum = 0;
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
            //TODO: Might want to do something other than return null to inform user stock is empty.
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
            for(int i=0;i<Main.cart.size();i++){
                if(Main.cart.get(i).getName().equals(checkedProduct.getName())){
                    cartcontains = true;
                    int amount = Main.cart.get(i).getCartAmount() + product.getCartAmount();
                    Main.cart.get(i).setCartAmount(amount);
                    cartNum = i;
                }
            }
            if (cartcontains){
                // Update instead of insert if the product already exists in cart.
                sql = "update carts set cartcount = ? where username = ? and itemname = ?";
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, Main.cart.get(cartNum).getCartAmount());
                preparedStatement.setString(2, Main.currentUser.getUsername());
                preparedStatement.setString(3, Main.cart.get(cartNum).getName());
                result = preparedStatement.executeUpdate();
                if (result == 0){
                    return null;
                } else{
                    return Main.cart;
                }

            }else{
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

            }
            return Main.cart;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Products updateCartProduct(Products product) {
        if (Main.currentUser == null) {
            return null;
        }
        try (Connection connection = DBConnection.getConnection()) {
            // Make sure cart is up-to-date.
            getUserCart(Main.currentUser);
            Products checkedProduct = new Products();
            boolean confirmProduct = false;
            int cartNum = 0;
            int oldAmount = 0;
            int amountDifference = 0;
            // Check to make sure the product is inside the cart.
            for(int i = 0; i<Main.cart.size();i++){
                if(Main.cart.get(i).getName().equals(product.getName())){
                    confirmProduct = true;
                    cartNum = i;
                    oldAmount = Main.cart.get(i).getCartAmount();
                    Main.cart.get(i).setCartAmount(product.getCartAmount());
                }
            }
            // Send null if the product does not appear in the cart.
            if (!confirmProduct) return null;
            if(oldAmount > Main.cart.get(cartNum).getCartAmount()){
                // Old amount was larger, so add back to stock.
                amountDifference = oldAmount - Main.cart.get(cartNum).getCartAmount();
                // Get product to check the amount of stock.
                String sql = "select * from stock where itemname = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, product.getName());
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
                // Updates the stock to take out the number of items added to a cart.
                sql = "update stock set stockcount = ? where itemname = ?";
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, checkedProduct.getStock() + amountDifference);
                preparedStatement.setString(2, product.getName());
                int result = preparedStatement.executeUpdate();
                if (result == 0){
                    return null;
                }

            } else if(oldAmount < Main.cart.get(cartNum).getCartAmount()){
                // Old amount was smaller, take away from stock.
                amountDifference = Main.cart.get(cartNum).getCartAmount() - oldAmount;
                // Get product to check the amount of stock.
                String sql = "select * from stock where itemname = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, product.getName());
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
                // Return null if there is not enough stock to remove.
                //TODO: Might want to do something other than return null to inform user stock is empty.
                if (checkedProduct.getStock() < amountDifference){
                    return null;
                }
                // Updates the stock to take out the number of items added to a cart.
                sql = "update stock set stockcount = ? where itemname = ?";
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, checkedProduct.getStock() - amountDifference);
                preparedStatement.setString(2, product.getName());
                int result = preparedStatement.executeUpdate();
                if (result == 0){
                    return null;
                }
            } else if (oldAmount == Main.cart.get(cartNum).getCartAmount()) {
                // The amount is the same. No changes need to be made to stocks.
            }
            String sql = "update carts set cartcount = ? where username = ? and itemname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, Main.cart.get(cartNum).getCartAmount());
            preparedStatement.setString(2, Main.currentUser.getUsername());
            preparedStatement.setString(3, Main.cart.get(cartNum).getName());
            preparedStatement.execute();
            int result = preparedStatement.executeUpdate();
            if (result == 0){
                return null;
            } else {
                return Main.cart.get(cartNum);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Products> deleteCartProduct(Products product) {
        return null;
    }
}
