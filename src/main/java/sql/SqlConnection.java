package sql;

import method.AppProperties;
import method.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SqlConnection {
    private Connection connection;
    private AppProperties appProperties = PropertiesLoader.loadProperties();

    private final String SQL_URL = appProperties.getImagePath("sqlUrl");

    public Statement connectUpdate() throws Throwable {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
        String url = SQL_URL;
        connection = DriverManager.getConnection(url);
        return connection.createStatement();
    }
}