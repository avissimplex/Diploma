package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private SQLHelper() {
    }

    private static QueryRunner runner;

    @SneakyThrows
    private static Connection getConn()  {
        String dsn = System.getProperty("db.dsn");
        String user = System.getProperty("db.user");
        String pass = System.getProperty("db.pass");
        return DriverManager.getConnection(dsn, user, pass);
    }
    @SneakyThrows
    public static String getPaymentStatus() {
        var sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var runner = new QueryRunner();
        var status = runner.query(conn, sql, new ScalarHandler<String>());
        return status;
    }
    @SneakyThrows
    public static Integer getPaymentAmount() {
        var sql = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var runner = new QueryRunner();
        var status = runner.query(conn, sql, new ScalarHandler<Integer>());
        return status;
    }

    @SneakyThrows
    public static String getCreditStatus() {
        var sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var runner = new QueryRunner();
        var status = runner.query(conn, sql, new ScalarHandler<String>());
        return status;
    }
    @SneakyThrows
    public static Integer getCreditAmount() {
        var sql = "SELECT amount FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var runner = new QueryRunner();
        var status = runner.query(conn, sql, new ScalarHandler<Integer>());
        return status;
    }
    @SneakyThrows
    public static void cleanDatabase()  {
        var connection = getConn();
        var runner = new QueryRunner();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
