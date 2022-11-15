package DAOtests;

import org.junit.jupiter.api.*;
import org.revature.entities.Products;
import org.revature.repositories.ProductDAOPostgres;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTests {

    ProductDAOPostgres productDAOMock = new ProductDAOPostgres();
    public ProductTests() {
    }

    @Test
    @Order(1)
    void get_all_products_test() {
        ArrayList<Products> products = productDAOMock.getProducts();
        System.out.println("Test 1: "+ products.get(1).toString());
        Assertions.assertTrue(products.size() > 2);
    }

    @Test
    @Order(2)
    void get_products_by_type_test() {
        ArrayList<Products> swords = productDAOMock.getProductsbyType("Swords");
        ArrayList<Products> potions = productDAOMock.getProductsbyType("Potions");
        System.out.println("Test 2: "+swords.get(0).toString());
        System.out.println("Test 2: "+potions.get(0).toString());
        Assertions.assertFalse(swords.get(0) == potions.get(0));
    }

    @Test
    @Order(3)
    void get_products_by_type_and_subtype_test() {
        ArrayList<Products> swords = productDAOMock.getProductsbyTypeAndSubtype("Swords", "Longswords");
        ArrayList<Products> shortsword = productDAOMock.getProductsbyTypeAndSubtype("Swords", "Shortswords");
        System.out.println("Test 3: "+swords.get(0).toString());
        System.out.println("Test 3: "+shortsword.get(0).toString());
        Assertions.assertFalse(swords.get(0) == shortsword.get(0));
    }

    @Test
    @Order(4)
    void get_products_by_id_test() {
        // Works as long as id 1 belongs to Longsword
        ArrayList<Products> swords = productDAOMock.getProductsbyID(1);
        System.out.println("Test 4: "+swords.get(0).toString());
        Assertions.assertTrue(swords.get(0).getName().equals("Longsword"));
    }

}
