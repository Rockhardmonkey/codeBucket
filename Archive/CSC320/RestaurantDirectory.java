public class RestaurantDirectory {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String cuisineType;

    public RestaurantDirectory(String name, String address, String city, String state, String zipCode, String phoneNumber, String cuisineType) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.cuisineType = cuisineType;
    }
    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    /**
     * Returns the full address: address, city, state ZIP
     */
    public String getFullAddress() {
        return address + ", " + city + ", " + state + " " + zipCode;
    }

    /**
     * Prints the restaurant name and full address to stdout.
     */
    public void printNameAndAddress() {
        System.out.println(name);
        System.out.println(getFullAddress());
    }

    @Override
    public String toString() {
        return "RestaurantDirectory{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                '}';
    }
}
class RestaurantDirectoryApp {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        java.util.List<RestaurantDirectory> list = new java.util.ArrayList<>();
        System.out.println("Commands: add, list, edit, exit");

        while (true) {
            System.out.print("> ");
            String cmd = sc.nextLine().trim();
            if (cmd.equalsIgnoreCase("add")) {
                addRestaurant(sc, list);
            } else if (cmd.equalsIgnoreCase("list")) {
                if (list.isEmpty()) {
                    System.out.println("No restaurants.");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        RestaurantDirectory r = list.get(i);
                        System.out.println((i + 1) + ". " + r.getName() + " - " + r.getFullAddress());
                    }
                }
            } else if (cmd.toLowerCase().startsWith("edit")) {
                // allow "edit" or "edit <number>"
                String[] parts = cmd.split("\\s+");
                if (parts.length == 2) {
                    try {
                        int idx = Integer.parseInt(parts[1]);
                        editRestaurant(sc, list, idx - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index.");
                    }
                } else {
                    editRestaurant(sc, list);
                }
            } else if (cmd.equalsIgnoreCase("exit") || cmd.equalsIgnoreCase("quit")) {
                break;
            } else if (cmd.isEmpty()) {
                // ignore
            } else {
                System.out.println("Unknown command.");
            }
        }

        sc.close();
    }

    private static void addRestaurant(java.util.Scanner sc, java.util.List<RestaurantDirectory> list) {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Address: ");
        String address = sc.nextLine().trim();
        System.out.print("City: ");
        String city = sc.nextLine().trim();
        System.out.print("State: ");
        String state = sc.nextLine().trim();
        System.out.print("ZIP Code: ");
        String zip = sc.nextLine().trim();
        System.out.print("Phone Number: ");
        String phone = sc.nextLine().trim();
        System.out.print("Cuisine Type: ");
        String cuisine = sc.nextLine().trim();

        RestaurantDirectory r = new RestaurantDirectory(name, address, city, state, zip, phone, cuisine);
        list.add(r);
        System.out.println("Restaurant added.");
    }

    private static void editRestaurant(java.util.Scanner sc, java.util.List<RestaurantDirectory> list) {
        if (list.isEmpty()) {
            System.out.println("No restaurants to edit.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getName());
        }
        System.out.print("Enter number to edit (or 'cancel'): ");
        String line = sc.nextLine().trim();
        if (line.equalsIgnoreCase("cancel") || line.isEmpty()) {
            System.out.println("Edit cancelled.");
            return;
        }
        try {
            int idx = Integer.parseInt(line) - 1;
            editRestaurant(sc, list, idx);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    private static void editRestaurant(java.util.Scanner sc, java.util.List<RestaurantDirectory> list, int index) {
        if (index < 0 || index >= list.size()) {
            System.out.println("Index out of range.");
            return;
        }
        RestaurantDirectory r = list.get(index);
        System.out.println("Editing restaurant #" + (index + 1) + " (leave blank to keep current value)");

        System.out.print("Name [" + r.getName() + "]: ");
        String line = sc.nextLine();
        if (!line.trim().isEmpty()) r.setName(line.trim());

        System.out.print("Address [" + r.getAddress() + "]: ");
        line = sc.nextLine();
        if (!line.trim().isEmpty()) r.setAddress(line.trim());

        System.out.print("City [" + r.getCity() + "]: ");
        line = sc.nextLine();
        if (!line.trim().isEmpty()) r.setCity(line.trim());

        System.out.print("State [" + r.getState() + "]: ");
        line = sc.nextLine();
        if (!line.trim().isEmpty()) r.setState(line.trim());

        System.out.print("ZIP Code [" + r.getZipCode() + "]: ");
        line = sc.nextLine();
        if (!line.trim().isEmpty()) r.setZipCode(line.trim());

        System.out.print("Phone Number [" + r.getPhoneNumber() + "]: ");
        line = sc.nextLine();
        if (!line.trim().isEmpty()) r.setPhoneNumber(line.trim());

        System.out.print("Cuisine Type [" + r.getCuisineType() + "]: ");
        line = sc.nextLine();
        if (!line.trim().isEmpty()) r.setCuisineType(line.trim());

        System.out.println("Restaurant updated.");
    }
}

class GrocerySorter {
    public static String categorize(String item) {
        if (item == null) return "Unknown";
        switch (item.trim().toLowerCase()) {
            case "apple": case "banana": case "orange": case "grapes":
                return "Fruit";
            case "carrot": case "lettuce": case "spinach": case "potato":
                return "Vegetable";
            case "milk": case "cheese": case "yogurt":
                return "Dairy";
            case "chicken": case "beef": case "pork":
                return "Meat";
            case "bread": case "bagel": case "croissant":
                return "Bakery";
            case "rice": case "pasta": case "beans":
                return "Pantry";
            default:
                return "Other";
        }
    }

    public static void sortAndPrint(java.util.List<String> items) {
        java.util.Map<String, java.util.List<String>> buckets = new java.util.LinkedHashMap<>();
        for (String it : items) {
            String cat = categorize(it);
            buckets.computeIfAbsent(cat, k -> new java.util.ArrayList<>()).add(it);
        }
        for (java.util.Map.Entry<String, java.util.List<String>> e : buckets.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    // Small demo you can run independently
    public static void main(String[] args) {
        java.util.List<String> sample = java.util.Arrays.asList(
            "Apple", "Milk", "Bread", "Carrot", "Chicken", "Rice", "Yogurt", "Banana", "Bagel"
        );
        sortAndPrint(sample);
    }
}