package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInit {
    public static void init(){
        String sql = "CREATE TABLE IF NOT EXISTS word (id SERIAL PRIMARY KEY ," +
                "word VARCHAR ," +
                "translate VARCHAR ," +
                "description TEXT )";
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/atto", "postgres", "elmurod2203");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

}
