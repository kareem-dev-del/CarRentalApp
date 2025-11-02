package Database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {
    public static void initialize() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // create table in string
            String sql = """
                CREATE TABLE IF NOT EXISTS cars (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    registration TEXT UNIQUE NOT NULL,
                    brand TEXT NOT NULL,
                    model TEXT NOT NULL,
                    status TEXT NOT NULL,
                    price REAL NOT NULL
                );
            """;
            stmt.execute(sql);

            System.out.println("✅ Tables created or already exist.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

