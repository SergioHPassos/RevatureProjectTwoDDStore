package org.revature.repositories;;
import org.revature.driver.Main;
import org.revature.entities.User;
import org.revature.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoPostgres implements UserDao {
    @Override
    public User registerUser(User user) {
        User searchUser = getUserByUsername(user.getUsername());
        if (searchUser.getUsername() != null) {
            return null;
        }
        try (Connection connection = DBConnection.getConnection()) {
            int pfpid = 1;
            String sql = "select * from profilepictures where image = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getImage());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pfpid = rs.getInt("pfpid");
            }
            sql = "insert into users values(default, ?, ?, ?, ?, ?)";
            //Three possible outcomes
            // 1)no rows return (typical): register the user
            // 2)password matches (user may have clicked Send twice): don't add another row, but populate user_id
            // 3)password mismatch
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDate(3, java.sql.Date.valueOf(user.getBirthday()));
            ps.setString(4, user.getAddress());
            ps.setInt(5, pfpid);

            ps.execute();

            rs = ps.getGeneratedKeys();
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
            ps.setString(4, user.getImage());
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
            int pfp = 1;
            User userone = new User();
            String sql = "select * from users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userone.setUserId(rs.getInt("userid"));
                userone.setUsername(rs.getString("username"));
                userone.setPassword(rs.getString("password"));
                userone.setBirthday(rs.getString("birthday"));
                userone.setAddress(rs.getString("address"));
                pfp = rs.getInt("image");

            }
            sql = "select * from profilepictures where pfpid = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pfp);

            rs = ps.executeQuery();
            if (rs.next()) {
                userone.setImage(rs.getString("image"));
            }
            return userone;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int UserId) {
        return null;
    }

    @Override
    public ArrayList<String> getAllPictures() {
        try (Connection connection = DBConnection.getConnection()) {
            ArrayList<String> pictures = new ArrayList<>();
            String sql = "select * from profilepictures";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                pictures.add(rs.getString("image"));
            }
            return pictures;
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}