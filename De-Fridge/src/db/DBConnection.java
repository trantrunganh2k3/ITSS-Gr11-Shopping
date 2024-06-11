package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;
    private static DBConnection dbConnection;
    private static final String URL = "jdbc:postgresql://localhost:5432/defridge";
    private static final String USER = "postgres";
    private static final String PASSWORD = "trunganh";
    private DBConnection()throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection= DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public Connection getConnection(){

        return connection;
    }
    public static DBConnection getDBConnection()throws ClassNotFoundException,SQLException{
        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }
}