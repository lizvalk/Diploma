package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
//    private static String url = System.getProperty("db.url");
//    private static String user = System.getProperty("db.user");
//    private static String password = System.getProperty("db.password");
//
//    @SneakyThrows
//    public static void cleanDB() {
//        var cleanCreditRequest = "DELETE FROM credit_request_entity;";
//        var cleanOrder = "DELETE FROM order_entity;";
//        var cleanPayment = "DELETE FROM payment_entity;";
//        var runner = new QueryRunner();
//        var conn = DriverManager.getConnection(url, user, password);
//        {
//            runner.update(conn, cleanCreditRequest);
//            runner.update(conn, cleanOrder);
//            runner.update(conn, cleanPayment);
//
//        }
//    }
//
//    @SneakyThrows
//    public static String getPaymentStatus() {
//        var codesSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
//        return getData(codesSQL);
//    }
//
//    @SneakyThrows
//    public static String getCreditRequestStatus() {
//        var codesSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
//        return getData(codesSQL);
//    }
//
//    @SneakyThrows
//    public static String getOrderCount() {
//        var codesSQL = "SELECT COUNT(*) FROM order_entity;";
//        var runner = new QueryRunner();
//        var conn = DriverManager.getConnection(url, user, password);
//        var count = runner.query(conn, codesSQL, new ScalarHandler<>());
//        return String.valueOf(count);
//    }
//
//
//    @SneakyThrows
//    private static String getData(String query) {
//        var runner = new QueryRunner();
//        var conn = DriverManager.getConnection(url, user, password);
//        return runner.query(conn, query, new ScalarHandler<>());
//
//    }

//    private static final QueryRunner runner = new QueryRunner();
//
//    private static final String url = System.getProperty("db.url");
//    private static final String user = System.getProperty("db.user");
//    private static final String password = System.getProperty("db.password");
//    private SQLHelper() {
//
//    }
//    @SneakyThrows
//    private static Connection getConn() throws SQLException {
//        return DriverManager.getConnection(url, user, password);
//    }
//    @SneakyThrows
//    public static String getPaymentStatus() {
//        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
//        var conn = getConn();
//        var status = runner.query(conn, statusSQL, new ScalarHandler<>());
//        return String.valueOf(status);
//    }
//    @SneakyThrows
//    public static String getCreditRequestStatus() {
//        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
//        var conn = getConn();
//        var status = runner.query(conn, statusSQL, new ScalarHandler<>());
//        return String.valueOf(status);
//    }
//    @SneakyThrows
//    public static void cleanDB(){
//        var connection = getConn();
//        runner.execute(connection, "DELETE FROM credit_request_entity");
//        runner.execute(connection, "DELETE FROM order_entity");
//        runner.execute(connection, "DELETE FROM payment_entity");
//    }

}
