package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Đường dẫn kết nối
    private static final String URL = "jdbc:mysql://localhost:3306/quan_ly_sinh_vien";
    private static final String USER = "root"; // Đổi thành username thực tế
    private static final String PASSWORD = "root"; // Đổi thành password thực tế

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // tạo Driver kết nối MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
        return connection;
    }
}