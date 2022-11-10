package UtilsTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.revature.driver.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    void connection_available() throws SQLException {
        Connection connection = DBConnection.getConnection();
        System.out.println(connection);
        Assertions.assertNotNull(connection);
        connection.close();
    }
}
