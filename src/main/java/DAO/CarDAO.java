package DAO;

import Database.DBConnection;
import models.CarModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public static boolean addCar(CarModel car) {
        String sql = "INSERT INTO cars (registration, brand, model, status, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, car.getRegistration());
                pstmt.setString(2, car.getBrand());
                pstmt.setString(3, car.getModel());
                pstmt.setString(4, car.getStatus());
                pstmt.setDouble(5, car.getPrice());
                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding car: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateCar(CarModel car) {
        String sql = "UPDATE cars SET brand=?, model=?, status=?, price=? WHERE registration=?";
        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, car.getBrand());
                pstmt.setString(2, car.getModel());
                pstmt.setString(3, car.getStatus());
                pstmt.setDouble(4, car.getPrice());
                pstmt.setString(5, car.getRegistration());
                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating car: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteCar(String registration) {
        String sql = "DELETE FROM cars WHERE registration=?";
        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, registration);
                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting car: " + e.getMessage());
            return false;
        }
    }

    public static List<CarModel> getAllCars() {
        List<CarModel> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    cars.add(new CarModel(
                            rs.getInt("id"),
                            rs.getString("registration"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("status"),
                            rs.getDouble("price")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching cars: " + e.getMessage());
        }
        return cars;
    }
}
