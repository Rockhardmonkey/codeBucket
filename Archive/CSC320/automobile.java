import java.util.Scanner;

public class Automobile {
    private String make;
    private String model;
    private int year;
    private int speed;
    private boolean engineOn;

    public Automobile() {
        this("", "", 0);
    }

    public Automobile(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.speed = 0;
        this.engineOn = false;
    }

    // Getters / setters
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getSpeed() { return speed; }

    public boolean isEngineOn() { return engineOn; }

    // Simple behaviors
    public void startEngine() { engineOn = true; }

    public void stopEngine() {
        engineOn = false;
        speed = 0;
    }

    // Increase speed only when engine is on
    public void accelerate(int amount) {
        if (engineOn && amount > 0) {
            speed += amount;
        }
    }

    public void brake(int amount) {
        if (amount > 0) {
            speed -= amount;
            if (speed < 0) speed = 0;
        }
    }

    public void honk() {
        System.out.println("Beep!");
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model + " (speed=" + speed + ", engine on=" + engineOn + ")";
    }
}
