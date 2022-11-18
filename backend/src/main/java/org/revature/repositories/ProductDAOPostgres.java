package org.revature.repositories;

import org.revature.entities.Products;
import org.revature.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAOPostgres implements ProductDAO{
    @Override
    public ArrayList<Products> getProducts() {
        try(Connection conn = DBConnection.getConnection()){
            ArrayList<Products> products = new ArrayList<>();
            String sql = "select * from stock";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Products product = new Products();
                product.setId(rs.getInt("itemId"));
                product.setName(rs.getString("itemname"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stockcount"));
                product.setType(rs.getString("itemtype"));
                product.setSubtype(rs.getString("itemsubtype"));
                product.setRarity(product.getRarity().valueOf(rs.getString("rarity")));
                product.setDiscount(rs.getBoolean("discount"));
                product.setImage(rs.getBytes("image"));
                products.add(product);
            }
            return products;
        } catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Products> getProductsbyType(String type) {
        try(Connection conn = DBConnection.getConnection()){
            ArrayList<Products> products = new ArrayList<>();
            String sql = "select * from stock where itemtype = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, type);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Products product = new Products();
                product.setId(rs.getInt("itemId"));
                product.setName(rs.getString("itemname"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stockcount"));
                product.setType(rs.getString("itemtype"));
                product.setSubtype(rs.getString("itemsubtype"));
                product.setRarity(product.getRarity().valueOf(rs.getString("rarity")));
                product.setDiscount(rs.getBoolean("discount"));
                product.setImage(rs.getBytes("image"));
                products.add(product);
            }
            return products;
        } catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Products> getProductsbyTypeAndSubtype(String type, String subtype) {
        try(Connection conn = DBConnection.getConnection()){
            ArrayList<Products> products = new ArrayList<>();
            String sql = "select * from stock where itemtype = ? and itemsubtype = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, subtype);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Products product = new Products();
                product.setId(rs.getInt("itemId"));
                product.setName(rs.getString("itemname"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stockcount"));
                product.setType(rs.getString("itemtype"));
                product.setSubtype(rs.getString("itemsubtype"));
                product.setRarity(product.getRarity().valueOf(rs.getString("rarity")));
                product.setDiscount(rs.getBoolean("discount"));
                product.setImage(rs.getBytes("image"));
                products.add(product);
            }
            return products;
        } catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Products> getProductsbyID(int id) {
        try(Connection conn = DBConnection.getConnection()){
            ArrayList<Products> products = new ArrayList<>();
            String sql = "select * from stock where itemid = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Products product = new Products();
                product.setId(rs.getInt("itemId"));
                product.setName(rs.getString("itemname"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stockcount"));
                product.setType(rs.getString("itemtype"));
                product.setSubtype(rs.getString("itemsubtype"));
                product.setRarity(product.getRarity().valueOf(rs.getString("rarity")));
                product.setDiscount(rs.getBoolean("discount"));
                product.setImage(rs.getBytes("image"));
                products.add(product);
            }
            return products;
        } catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateProductPicture(int id, byte[] array) {
        try(Connection conn = DBConnection.getConnection()){
            String sql = "update stock set image = ? where itemid = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBytes(1, array);
            preparedStatement.setInt(2, id);
            int rs = preparedStatement.executeUpdate();
            if (rs == 0){
                return "Failed! Change did not go through!\r\nPlease make sure id# is a valid ticket!";
            } else {
                return "Success! Picture was uploaded to ticket #"+id;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
