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
    public ArrayList<Products> getProductsbyType() {
        return null;
    }

    @Override
    public ArrayList<Products> getProductsbyTypeAndSubtype() {
        return null;
    }

    @Override
    public ArrayList<Products> getProductsbyID() {
        return null;
    }
}
