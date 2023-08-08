package data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private SQLHelper() {
    }

    private static QueryRunner runner;

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static String getPaymentStatus() {
        var sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var status = runner.query(conn, sql, new ScalarHandler<String>());
            return status;
        } catch (SQLException exception) {
            return null;
        }
    }

    public static void CleanDatabase() throws SQLException {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_enity");
        runner.execute(connection, "DELETE FROM order_enity");
        runner.execute(connection, "DELETE FROM payment_enity");
    }
}
