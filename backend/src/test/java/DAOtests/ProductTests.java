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
    void get_all_tickets_test() {
        ArrayList<Products> products = productDAOMock.getProducts();
        System.out.println(products.get(1).toString());
        Assertions.assertTrue(products.size() > 2);
    }

}
