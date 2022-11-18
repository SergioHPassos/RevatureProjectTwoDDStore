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
            Main.cart = new ArrayList<>();
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
            // Return all remaining stock if there is requested amount is too high.
            if (checkedProduct.getStock() < product.getCartAmount()){
                product.setCartAmount(checkedProduct.getStock());
            }
            System.out.println("checked Name = "+checkedProduct.getName());
            System.out.println("cart size = "+Main.cart.size());
            checkedProduct.setCartAmount(product.getCartAmount());
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
                Main.cart.add(checkedProduct);

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
                // Return all remaining stock if the requested amount is larger than available stock.
                if (checkedProduct.getStock() < amountDifference){
                    amountDifference = checkedProduct.getStock();
                    Main.cart.get(cartNum).setCartAmount(oldAmount+amountDifference);
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
        if (Main.currentUser == null) {
            return null;
        }
        try(Connection conn = DBConnection.getConnection()){
            Products checkedProduct = new Products();
            String sql = "delete from carts where username = ? and itemname = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, Main.currentUser.getUsername());
            preparedStatement.setString(2, product.getName());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys(); //Returns the id that was created
            resultSet.next(); //you need to move the cursor to the first valid record, or you will get a null response.
            for(int i=0;i<Main.cart.size();i++){
                if(Main.cart.get(i).getName().equals(product.getName())){
                    // Get product to check the amount of stock.
                    sql = "select * from stock where itemname = ?";
                    preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
                    preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(1, checkedProduct.getStock() + Main.cart.get(i).getCartAmount());
                    preparedStatement.setString(2, product.getName());
                    int result = preparedStatement.executeUpdate();
                    if (result == 0){
                        return null;
                    }
                    Main.cart.remove(i);
                }
            }
            return Main.cart;
        } catch(SQLException|NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String checkout(User user) {
        if (Main.currentUser == null) {
            return null;
        }
        getUserCart(Main.currentUser);
        if (Main.cart.size() < 1){
            return null;
        }
        try(Connection conn = DBConnection.getConnection()){
            int fullPrice = 0;
            int fullCartCount = 0;
            ArrayList<String> cartString = new ArrayList<>();
            for(int i=0;i<Main.cart.size();i++){
                fullPrice += Main.cart.get(i).getCartAmount()*Main.cart.get(i).getPrice();
                fullCartCount += Main.cart.get(i).getCartAmount();
                cartString.add(Main.cart.get(i).toString());
            }
            String sql = "insert into deliveries values(default, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Main.currentUser.getUsername());
            ps.setInt(2, fullPrice);
            ps.setInt(3, fullCartCount);
            ps.setString(4, cartString.toString());
            ps.setString(5, Main.currentUser.getAddress());
            ps.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));

            ps.execute();

            sql = "delete from carts where username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, Main.currentUser.getUsername());

            preparedStatement.execute();
            return "Successful Delivery";
        } catch(SQLException|NullPointerException e){
            e.printStackTrace();
        }

        return null;
    }

}
