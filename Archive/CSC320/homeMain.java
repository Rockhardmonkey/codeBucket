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

    // new: return a reference to the home at index, or null if out of range
    public Home getHome(int index) {
        if (index < 0 || index >= homes.size()) return null;
        return homes.get(index);
    }
}

public class homeMain {
    public static void main(String[] args) {
        HomeInventory inventory = new HomeInventory();
        Scanner scanner = new Scanner(System.in);

        try {
            boolean running = true;
            while (running) {
                System.out.println();
                System.out.println("Menu:");
                System.out.println("1) Construct & add a new home");
                System.out.println("2) Remove a home by index");
                System.out.println("3) Update home attributes by index");
                System.out.println("4) Update sale status by index");
                System.out.println("5) List homes");
                System.out.println("6) Save inventory to file");
                System.out.println("0) Exit");
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        Home newHome = constructHomeFromUser(scanner);
                        System.out.println(inventory.addHome(newHome));
                        break;
                    case "2":
                        int remIdx = readInt(scanner, "Enter index to remove: ");
                        System.out.println(inventory.removeHome(remIdx));
                        break;
                    case "3":
                        int updIdx = readInt(scanner, "Enter index to update attributes: ");
                        Home existing = inventory.getHome(updIdx);
                        if (existing == null) {
                            System.out.println("Index out of range.");
                            break;
                        }
                        Home updated = constructHomeWithDefaults(scanner, existing);
                        System.out.println(inventory.updateHomeAttributes(updIdx, updated));
                        break;
                    case "4":
                        int stIdx = readInt(scanner, "Enter index to update sale status: ");
                        System.out.print("Enter new status (sold / available / under contract): ");
                        String status = scanner.nextLine().trim();
                        System.out.println(inventory.updateSaleStatus(stIdx, status));
                        break;
                    case "5":
                        System.out.println(inventory.listHomes());
                        break;
                    case "6":
                        String content = inventory.getInventoryString();
                        Path outPath = Paths.get("C:\\Temp\\Home.txt");
                        try {
                            Files.createDirectories(outPath.getParent());
                            Files.writeString(outPath, content);
                            System.out.println("Inventory written to " + outPath.toString());
                        } catch (IOException ioe) {
                            System.out.println("Failed to write file: " + ioe.getMessage());
                        }
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred in main: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Home constructHomeFromUser(Scanner scanner) {
        int sqft = readInt(scanner, "Square feet: ");
        System.out.print("Address: ");
        String address = scanner.nextLine().trim();
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        System.out.print("State: ");
        String state = scanner.nextLine().trim();
        int zip = readInt(scanner, "Zip code: ");
        System.out.print("Model name: ");
        String model = scanner.nextLine().trim();
        String saleStatus = "";
        while (true) {
            System.out.print("Sale status (sold / available / under contract): ");
            saleStatus = scanner.nextLine().trim();
            String n = saleStatus.toLowerCase();
            if (n.equals("sold") || n.equals("available") || n.equals("under contract")) break;
            System.out.println("Invalid status; try again.");
        }
        return new Home(sqft, address, city, state, zip, model, saleStatus);
    }

    // Construct a new Home using existing home values as defaults; blank input keeps current
    private static Home constructHomeWithDefaults(Scanner scanner, Home existing) {
        System.out.print("Square feet [" + existing.getSquare_feet() + "]: ");
        String sqftStr = scanner.nextLine().trim();
        int sqft = sqftStr.isEmpty() ? existing.getSquare_feet() : parseOrDefault(sqftStr, existing.getSquare_feet());

        System.out.print("Address [" + existing.getAddress() + "]: ");
        String address = scanner.nextLine().trim();
        if (address.isEmpty()) address = existing.getAddress();

        System.out.print("City [" + existing.getCity() + "]: ");
        String city = scanner.nextLine().trim();
        if (city.isEmpty()) city = existing.getCity();

        System.out.print("State [" + existing.getState() + "]: ");
        String state = scanner.nextLine().trim();
        if (state.isEmpty()) state = existing.getState();

        System.out.print("Zip code [" + existing.getZip_code() + "]: ");
        String zipStr = scanner.nextLine().trim();
        int zip = zipStr.isEmpty() ? existing.getZip_code() : parseOrDefault(zipStr, existing.getZip_code());

        System.out.print("Model name [" + existing.getModel_name() + "]: ");
        String model = scanner.nextLine().trim();
        if (model.isEmpty()) model = existing.getModel_name();

        String saleStatus = "";
        while (true) {
            System.out.print("Sale status [" + existing.getSale_status() + "] (sold / available / under contract): ");
            saleStatus = scanner.nextLine().trim();
            if (saleStatus.isEmpty()) {
                saleStatus = existing.getSale_status();
                break;
            }
            String n = saleStatus.toLowerCase();
            if (n.equals("sold") || n.equals("available") || n.equals("under contract")) break;
            System.out.println("Invalid status; try again.");
        }

        return new Home(sqft, address, city, state, zip, model, saleStatus);
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static int parseOrDefault(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
