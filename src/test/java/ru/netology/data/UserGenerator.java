package ru.netology.data;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class UserGenerator {
    @SneakyThrows
    public static Connection getConnection() {
        var connection = DriverManager.getConnection
                ("jdbc:mysql://185.119.57.126:3306/app", "app", "pass");
        return connection;
    }

    @SneakyThrows
    public static String getVerificationCode(String login) {
        String verCode = null;
        String autCode = "select code from auth_codes ac JOIN users u WHERE u.login = ? order by ac.created desc limit 1;";
        try (var connect = getConnection();
             var codeStatement = connect.prepareStatement(autCode);) {
            codeStatement.setString(1, login);
            try (var rs = codeStatement.executeQuery()) {
                if (rs.next()) {
                    verCode = rs.getString("code");

                }
            }
            return verCode;

        }
    }

    @SneakyThrows
    public static void cleanDB() {
        String deleteCards = "delete from cards where true;";
        String deleteAutoCodes = "delete from auth_codes where true;";
        String deleteUsers = "delete from users where true;";
        try (var conn = UserGenerator.getConnection();
             var deleteCardsStmt = conn.createStatement();
             var deleteAutoCodesStmt = conn.createStatement();
             var deleteUsersStmt = conn.createStatement();) {
            deleteCardsStmt.executeUpdate(deleteCards);
            deleteAutoCodesStmt.executeUpdate(deleteAutoCodes);
            deleteUsersStmt.executeUpdate(deleteUsers);
        }
    }
    @SneakyThrows
    public static String getStatus (String login) {
        String sqlStatus = "select status from users where login = ?;";
        String status = null;
        try (var conn = getConnection();
        var statusStmt = conn.prepareStatement(sqlStatus);){
            statusStmt.setString(1,login);
            try (var rs = statusStmt.executeQuery()){
                if (rs.next()){
                    status = rs.getString("status");
                }

            }

        }
        return status;
    }


}

