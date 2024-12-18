package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/devoilibreThreads";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        public static java.sql.Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }


}
