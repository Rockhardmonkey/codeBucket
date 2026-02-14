import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Home {
    private int square_feet;
    private String address;
    private String city;
    private String state;
    private int zip_code;
    private String Model_name;
    private String sale_status; // sold, available, or under contract

    public Home(int square_feet, String address, String city, String state, int zip_code, String Model_name, String sale_status) {
        this.square_feet = square_feet;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.Model_name = Model_name;
        this.sale_status = sale_status;
    }

    // Getters and setters
    public int getSquare_feet() { return square_feet; }
    public void setSquare_feet(int square_feet) { this.square_feet = square_feet; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public int getZip_code() { return zip_code; }
    public void setZip_code(int zip_code) { this.zip_code = zip_code; }

    public String getModel_name() { return Model_name; }
    public void setModel_name(String model_name) { Model_name = model_name; }

    public String getSale_status() { return sale_status; }
    public void setSale_status(String sale_status) { this.sale_status = sale_status; }

    @Override
    public String toString() {
        return String.format("Model: %s, %d sqft, %s, %s %d, Status: %s",
                Model_name, square_feet, address, city, zip_code, sale_status);
    }

    public void clear() {
        this.square_feet = 0;
        this.address = "";
        this.city = "";
        this.state = "";
        this.zip_code = 0;
        this.Model_name = "";
        this.sale_status = "";
    }
}

class HomeInventory {
    private final List<Home> homes = new ArrayList<>();

    public String addHome(Home h) {
        try {
            homes.add(h);
            return "Home added successfully.";
        } catch (Exception e) {
            return "Failed to add home: " + e.getMessage();
        }
    }

    // remove by index
    public String removeHome(int index) {
        try {
            if (index < 0 || index >= homes.size()) {
                return "Failed to remove home: index out of range.";
            }
            homes.remove(index);
            return "Home removed successfully.";
        } catch (Exception e) {
            return "Failed to remove home: " + e.getMessage();
        }
    }

    // update entire home at index
    public String updateHomeAttributes(int index, Home newHome) {
        try {
            if (index < 0 || index >= homes.size()) {
                return "Failed to update home: index out of range.";
            }
            homes.set(index, newHome);
            return "Home updated successfully.";
        } catch (Exception e) {
            return "Failed to update home: " + e.getMessage();
        }
    }

    // update sale status only
    public String updateSaleStatus(int index, String newStatus) {
        try {
            if (index < 0 || index >= homes.size()) {
                return "Failed to update sale status: index out of range.";
            }
            String normalized = newStatus == null ? "" : newStatus.trim().toLowerCase();
            if (!normalized.equals("sold") && !normalized.equals("available") && !normalized.equals("under contract")) {
                return "Failed to update sale status: invalid status (must be 'sold', 'available', or 'under contract').";
            }
            homes.get(index).setSale_status(newStatus);
            return "Sale status updated successfully.";
        } catch (Exception e) {
            return "Failed to update sale status: " + e.getMessage();
        }
    }

    // print to console and return status message
    public String listHomes() {
        try {
            if (homes.isEmpty()) {
                System.out.println("No homes in inventory.");
                return "Listed 0 homes.";
            }
            System.out.println("Listing homes:");
            for (int i = 0; i < homes.size(); i++) {
                System.out.println("Index " + i + ": " + homes.get(i).toString());
            }
            return "Listed " + homes.size() + " homes.";
        } catch (Exception e) {
            return "Failed to list homes: " + e.getMessage();
        }
    }

    // return inventory as a single string (for writing to file)
    public String getInventoryString() {
        try {
            StringBuilder sb = new StringBuilder();
            if (homes.isEmpty()) {
                sb.append("No homes in inventory.\n");
            } else {
                for (int i = 0; i < homes.size(); i++) {
                    sb.append("Index ").append(i).append(": ").append(homes.get(i).toString()).append(System.lineSeparator());
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "Failed to construct inventory string: " + e.getMessage();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HomeInventory inventory = new HomeInventory();
        Scanner scanner = new Scanner(System.in);

        try {
            // Create a home using parameterized constructor
            Home home1 = new Home(1800, "123 Maple St", "Springfield", "IL", 62704, "MapleModel", "available");

            // Add home
            System.out.println(inventory.addHome(home1));

            // List homes
            System.out.println(inventory.listHomes());

            // Remove home (clear variables by removing from inventory)
            System.out.println(inventory.removeHome(0));

            // Add a new home
            Home home2 = new Home(2200, "456 Oak Ave", "Lincoln", "NE", 68508, "OakModel", "available");
            System.out.println(inventory.addHome(home2));

            // List to show new home
            System.out.println(inventory.listHomes());

            // Update the home sale status
            System.out.println(inventory.updateSaleStatus(0, "sold"));

            // List to show updated status
            System.out.println(inventory.listHomes());

            // Ask user whether to print to file
            System.out.print("Do you want to print the information to a file? (Y/N): ");
            String resp = scanner.nextLine().trim().toUpperCase();
            if ("Y".equals(resp)) {
                String content = inventory.getInventoryString();
                Path outPath = Paths.get("C:\\Temp\\Home.txt");
                try {
                    Files.createDirectories(outPath.getParent());
                    try (BufferedWriter writer = Files.newBufferedWriter(outPath)) {
                        writer.write(content);
                    }
                    System.out.println("Inventory written to " + outPath.toString());
                } catch (IOException ioe) {
                    System.out.println("Failed to write file: " + ioe.getMessage());
                }
            } else {
                System.out.println("File will not be printed.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred in main: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
