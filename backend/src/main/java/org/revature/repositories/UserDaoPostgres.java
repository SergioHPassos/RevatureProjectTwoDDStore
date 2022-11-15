package org.revature.repositories;;
import org.revature.driver.Main;
import org.revature.entities.User;
import org.revature.utils.DBConnection;

import java.sql.*;

public class UserDaoPostgres implements UserDao {
    @Override
    public User registerUser(User user) {
        User searchUser = getUserByUsername(user.getUsername());
        if (searchUser != null) {
            return null;
        }
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "insert into users values(default, ?, ?, ?, ?, ?)";
            //Three possible outcomes
            // 1)no rows return (typical): register the user
            // 2)password matches (user may have clicked Send twice): don't add another row, but populate user_id
            // 3)password mismatch
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDate(3, java.sql.Date.valueOf(user.getBirthday()));
            ps.setString(4, user.getAddress());
            ps.setBytes(5, user.getImage());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedKey = rs.getInt("userid");
            user.setUserId(generatedKey);
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User loginUser(User user) {
        User searchUser = getUserByUsername(user.getUsername());
        if (searchUser != null && user.getPassword().equals(searchUser.getPassword())) {
            Main.currentUser = searchUser;
            searchUser.setPassword("");
            return searchUser;
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        return Main.currentUser;
    }

    @Override
    public User updateUser(User user) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "update users set password = ?, birthday = ?, address = ?, image = ? where userid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setDate(2, java.sql.Date.valueOf(user.getBirthday()));
            ps.setString(3, user.getAddress());
            ps.setBytes(4, user.getImage());
            ps.setInt(5, user.getUserId());

            ps.executeUpdate();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "select * from users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User userone = new User();
                userone.setUserId(rs.getInt("userid"));
                userone.setUsername(rs.getString("username"));
                userone.setPassword(rs.getString("password"));
                userone.setBirthday(rs.getString("birthday"));
                userone.setAddress(rs.getString("address"));
                userone.setImage(rs.getBytes("image"));
                return userone;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



//    @Override
//    public Stock getAllProducts() {
//        return null;
//    }
//
//    @Override
//    public Stock getProductByTypeAndSubType(Stock type, Stock subType) {
//        return null;
//    }
//
//    @Override
//    public Stock getProductById(int ItemId) {
//        return null;
//    }
//
    @Override
    public User getUserById(int UserId) {
        return null;
    }
//
//    @Override
//    public Stock getCartByUserId(int UserId) {
//        return null;
//    }
//
//    @Override
//    public Cart addItemToCart(Cart cart) {
//        return null;
//    }
//
//    @Override
//    public Cart updateItemQuantityInCart(Cart cart) {
//        return null;
//    }
//
//    @Override
//    public Cart deleteItemFrontCart(Cart cart) {
//        return null;
//    }
}