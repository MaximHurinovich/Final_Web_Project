package by.gurinovich.webproject.pool;

import by.gurinovich.webproject.resource.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionDB {
    private static String url = ConfigurationManager.getProperty("cn.url");
    private static String userName = ConfigurationManager.getProperty("cn.root");
    private static String userPassword = ConfigurationManager.getProperty("cn.password");

    static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", userName);
        properties.put("password", userPassword);
        properties.put("autoReconnect", ConfigurationManager.getProperty("cn.autoreconnect"));
        properties.put("characterEncoding", ConfigurationManager.getProperty("cn.encoding"));
        properties.put("useUnicode", ConfigurationManager.getProperty("cn.unicode"));
        return DriverManager.getConnection(url, properties);
    }
}
