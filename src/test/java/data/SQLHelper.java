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
        String dsn = System.getProperty("db.dsn");
        return DriverManager.getConnection(dsn, "app", "pass");
    }

    public static String getPaymentStatus() {
        var sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var runner = new QueryRunner();
            var status = runner.query(conn, sql, new ScalarHandler<String>());
            return status;
        } catch (SQLException exception) {
            return null;
        }
    }
    public static Object getPaymentAmount() {
        var sql = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var runner = new QueryRunner();
            var status = runner.query(conn, sql, new ScalarHandler<Integer>());
            return status;
        } catch (SQLException exception) {
            return null;
        }
    }

    public static String getCreditStatus() {
        var sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var runner = new QueryRunner();
            var status = runner.query(conn, sql, new ScalarHandler<String>());
            return status;
        } catch (SQLException exception) {
            return null;
        }
    }
    public static Object getCreditAmount() {
        var sql = "SELECT amount FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var runner = new QueryRunner();
            var status = runner.query(conn, sql, new ScalarHandler<Integer>());
            return status;
        } catch (SQLException exception) {
            return null;
        }
    }
    public static void CleanDatabase() throws SQLException {
        var connection = getConn();
        var runner = new QueryRunner();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
