package models;

@SuppressWarnings("unused")
public class CarModel {
    private int id;
    private String registration;
    private String brand;
    private String model;
    private String status;
    private double price;

    public CarModel(int id, String registration, String brand, String model, String status, double price) {
        this.id = id;
        this.registration = registration;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.price = price;
    }

    public CarModel(String registration, String brand, String model, String status, double price) {
        this(-1, registration, brand, model, status, price);
    }

    // Getters and setters
    public int getId() { return id; }
    public String getRegistration() { return registration; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getStatus() { return status; }
    public double getPrice() { return price; }

    public void setRegistration(String registration) { this.registration = registration; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setStatus(String status) { this.status = status; }
    public void setPrice(double price) { this.price = price; }
}
