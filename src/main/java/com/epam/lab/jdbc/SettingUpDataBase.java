package com.epam.lab.jdbc;

import com.epam.lab.jdbc.sqlConst.SQLConst;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class SettingUpDataBase {
    public static void setUp(Connection connection) throws SQLException { // 1 transaction
        try {
            connection.setAutoCommit(false);

            setDBStatement(connection, SQLConst.DROP_DATABASE_IFEXIST);
            setDBStatement(connection, SQLConst.CREATE_DATABASE);
            setDBStatement(connection, SQLConst.USE_DATABASE);
            setDBStatement(connection, SQLConst.CREATE_TABLE_STUDENT);

            setDBStatement(connection,SQLConst.CREATE_TABLE_DEPARTMENT);
            setDBStatement(connection, SQLConst.WIRE_TABLES);
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static void useUniverDB(Connection connection) throws SQLException {
        setDBStatement(connection, SQLConst.USE_DATABASE);
    }

    public static void setDBStatement(Connection connection, String sql) throws SQLException {
        Statement setUpDBStatement = null;
        try {
            setUpDBStatement = connection.createStatement();
            setUpDBStatement.execute(sql);
        } finally {
            if (setUpDBStatement != null) {
                setUpDBStatement.close();
            }
        }
    }
}
