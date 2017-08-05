package com.epam.lab.jdbc.controller;

import com.epam.lab.jdbc.sqlConst.SQLConst;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class BDMetaData {
    public static final Logger LOG = Logger.getLogger(BDMetaData.class);
    static DatabaseMetaData databaseMetaData = null;
    static Connection connection = null;

    static {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_univer",
                    SQLConst.USER, SQLConst.PASSWORD);
        } catch (SQLException e) {
            LOG.error("There was an error getting the connection: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            LOG.error("There was an error getting the metadata: " + e.getMessage());
        }
    }

    public static void showMetaData() throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(SQLConst.URL, SQLConst.USER, SQLConst.PASSWORD)) {
            databaseMetaData = connection.getMetaData();
            LOG.info("DB minor version " + databaseMetaData.getDatabaseMinorVersion());
            LOG.info("BD product name " + databaseMetaData.getDatabaseProductName());
            LOG.info("Driver " + databaseMetaData.getDriverName());
            LOG.info("DB driver minor version " + databaseMetaData.getDriverMinorVersion());
            getColumnsMetadata(getTablesMetadata());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList getTablesMetadata() throws SQLException {
        String table[] = {"TABLE"};
        ResultSet rs = null;
        ArrayList tables = null;
        rs = databaseMetaData.getTables(null, null, null, table);
        tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

    public static void getColumnsMetadata(ArrayList<String> tables) throws SQLException {
        ResultSet rs = null;
        for (String actualTable : tables) {
            rs = databaseMetaData.getColumns(null, null, actualTable, null);
            System.out.println(actualTable.toUpperCase());
            while (rs.next()) {
                LOG.info(rs.getString("COLUMN_NAME") + " "
                        + rs.getString("TYPE_NAME") + " "
                        + rs.getString("COLUMN_SIZE"));
            }
            System.out.println("\n");
        }
    }

}
