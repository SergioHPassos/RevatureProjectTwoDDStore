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
        System.out.println(products.get(1).toString());
        Assertions.assertTrue(products.size() > 2);
    }

    @Test
    @Order(1)
    void get_products_by_type_test() {
        ArrayList<Products> swords = productDAOMock.getProductsbyType("Swords");
        ArrayList<Products> potions = productDAOMock.getProductsbyType("Potions");
        System.out.println(swords.get(0).toString());
        System.out.println(potions.get(0).toString());
        Assertions.assertFalse(swords.get(0) == potions.get(0));
    }

}
