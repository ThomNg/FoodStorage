package com.tng.tnabschlussprojekt.logic.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
    //region Konstanten
    private static DBManager instance;
    private FoodDao foodDao;

    private static final String PORT = "3306";
    private static final String USERNAME = "root";
    private static final String SERVER_IP = "localhost";
    private static final String PW = "";
    private static final String CONNECTION_PROTOCOL = "jdbc:mariadb://";
    private static final String DB_NAME = "/foods";
    private static final String CONNECTION_URL = CONNECTION_PROTOCOL+SERVER_IP+DB_NAME;
    // endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    private DBManager() {
        foodDao = new FoodDao();

    }
    //endregion

    //region Methoden
    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection () throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PW);
    }
    //endregion
}
