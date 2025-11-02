package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        try {
            // *أضفنا هذا السطر مرة أخرى للتحقق من وجود الدرايفر*
            Class.forName("org.sqlite.JDBC");

            String path = "car_sqlite.sqlite";

            // حاول الاتصال
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path);
            System.out.println("✅ SQLite Connected at: " + path);
            return conn;

        } catch (ClassNotFoundException e) {
            // *هذا الخطأ يعني أن ملف JAR الدرايفر غير موجود في المشروع!*
            System.out.println("❌ Critical Error: SQLite JDBC Driver not found. Check your project Libraries/Dependencies.");
            System.out.println("Details: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            // هذا الخطأ يعني أن الاتصال فشل لسبب آخر (مسار، أذونات، إلخ)
            System.out.println("❌ Connection Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args){
        // يجب أن يتم إظهار رسالة إما نجاح أو فشل الدرايفر أو فشل الاتصال.
        getConnection();
    }
}