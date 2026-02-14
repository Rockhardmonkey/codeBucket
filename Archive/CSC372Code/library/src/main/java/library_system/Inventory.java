package library_system;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    
    private ArrayList<Book> branchInventory;
    private ArrayList<Book> checkOutInventory;
    
    public Inventory() {
        this.branchInventory = new ArrayList<>();
        this.checkOutInventory = new ArrayList<>();
    }
    
    public void addBook(int id, String title, String author, String isbn, int numberOfPages) throws IllegalArgumentException {
        try {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty.");
            }
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be empty.");
            }
            if (isDuplicateId(id)) {
                throw new IllegalArgumentException("Book ID already exists in inventory.");
            }
            
            Book newBook = new Book(id, title, author, isbn, numberOfPages);
            branchInventory.add(newBook);
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
            throw new IllegalArgumentException("Error adding book: " + e.getMessage());
        }
    }

    private boolean isDuplicateId(int id) {
        for (Book book : branchInventory) {
            if (book != null && book.getId() != null && book.getId() == id) {
                return true;
            }
        }
        for (Book book : checkOutInventory) {
            if (book != null && book.getId() != null && book.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a list of matching books for the given title.
     * If only one match, automatically borrows it.
     * If multiple matches, returns the list for GUI selection.
     */
    public List<Book> borrowBook(String title) throws IllegalArgumentException {
        try {
            List<Book> matchingBooks = searchByTitle(title);
            
            if (matchingBooks.isEmpty()) {
                throw new IllegalArgumentException("No books found with that title.");
            }
            
            if (matchingBooks.size() == 1) {
                Book book = matchingBooks.get(0);
                branchInventory.remove(book);
                checkOutInventory.add(book);
                System.out.println("Book '" + book.getTitle() + "' borrowed successfully!");
                return new ArrayList<>(); // Empty list signals successful single borrow
            } else {
                // Multiple matches - return list for GUI to handle selection
                return matchingBooks;
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error borrowing book: " + e.getMessage());
        }
    }
    
    /**
     * Borrows a specific book from the given list of matching books
     */
    public void borrowBookSelection(List<Book> matchingBooks, int choice) throws IllegalArgumentException {
        try {
            if (choice < 0 || choice >= matchingBooks.size()) {
                throw new IllegalArgumentException("Invalid selection.");
            }
            
            Book book = matchingBooks.get(choice);
            branchInventory.remove(book);
            checkOutInventory.add(book);
            System.out.println("Book '" + book.getTitle() + "' borrowed successfully!");
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error borrowing book: " + e.getMessage());
        }
    }

    /**
     * Returns a list of matching checked-out books for the given title.
     * If only one match, automatically returns it.
     * If multiple matches, returns the list for GUI selection.
     */
    public List<Book> returnBook(String title) throws IllegalArgumentException {
        try {
            List<Book> matchingBooks = new ArrayList<>();
            
            for (Book book : checkOutInventory) {
                if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    matchingBooks.add(book);
                }
            }
            
            if (matchingBooks.isEmpty()) {
                throw new IllegalArgumentException("No checked-out books found with that title.");
            }
            
            if (matchingBooks.size() == 1) {
                Book book = matchingBooks.get(0);
                checkOutInventory.remove(book);
                branchInventory.add(book);
                System.out.println("Book '" + book.getTitle() + "' returned successfully!");
                return new ArrayList<>(); // Empty list signals successful single return
            } else {
                // Multiple matches - return list for GUI to handle selection
                return matchingBooks;
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error returning book: " + e.getMessage());
        }
    }
    
    /**
     * Returns a specific book from the given list of matching books
     */
    public void returnBookSelection(List<Book> matchingBooks, int choice) throws IllegalArgumentException {
        try {
            if (choice < 0 || choice >= matchingBooks.size()) {
                throw new IllegalArgumentException("Invalid selection.");
            }
            
            Book book = matchingBooks.get(choice);
            checkOutInventory.remove(book);
            branchInventory.add(book);
            System.out.println("Book '" + book.getTitle() + "' returned successfully!");
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error returning book: " + e.getMessage());
        }
    }
    
    /**
     * Prints all books in both branch and checkout inventories
     * Returns a combined list of all books.
     */
    public List<Book> printAll() {
        List<Book> allBooks = new ArrayList<>();
        
        System.out.println("=== BRANCH INVENTORY ===");
        if (branchInventory.isEmpty()) {
            System.out.println("No books in branch inventory.");
        } else {
            for (Book book : branchInventory) {
                if (book != null) {
                    book.printBookInfo();
                    System.out.println("---");
                    allBooks.add(book);
                }
            }
        }
        
        System.out.println("=== CHECKED OUT INVENTORY ===");
        if (checkOutInventory.isEmpty()) {
            System.out.println("No books currently checked out.");
        } else {
            for (Book book : checkOutInventory) {
                if (book != null) {
                    book.printBookInfo();
                    System.out.println("---");
                    allBooks.add(book);
                }
            }
        }
        
        return allBooks;
    }
    
    /**
     * Searches through the book list and returns all books with matching or
     * partially matching titles (case-insensitive)
     */
    public List<Book> searchByTitle(String title) throws IllegalArgumentException {
        List<Book> results = new ArrayList<>();
        
        try {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Search title cannot be empty.");
            }
            
            for (Book book : branchInventory) {
                if (book != null && book.getTitle() != null && 
                    book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    results.add(book);
                }
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Null value encountered during search.");
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error searching for book: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Retrieves the number of books available in the main inventory
     * Useful for handling edge cases such as when a user attempts to return
     * a book though no books have been lent
     */
    public int getMainInventoryCount() {
        return branchInventory.size();
    }
    
    /**
     * Retrieves the number of books currently checked out
     */
    public int getCheckOutInventoryCount() {
        return checkOutInventory.size();
    }
    
    /**
     * Saves both branch and checkout inventories to a text file in the resources folder
     * Format:
     * [BRANCH]
     * ID,Title,Author,ISBN,NumberOfPages
     * [CHECKOUT]
     * ID,Title,Author,ISBN,NumberOfPages
     */
    public void saveInventoryToFile(String filename) throws IOException {
        String resourcesPath = getResourcesPath();
        String filepath = resourcesPath + File.separator + filename;
        
        // Create file if it doesn't exist
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("[BRANCH]\n");
            for (Book book : branchInventory) {
                if (book != null) {
                    writer.write(String.format("%d,%s,%s,%s,%d%n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getISBN(),
                        book.getNumberOfPages()));
                }
            }

            writer.write("[CHECKOUT]\n");
            for (Book book : checkOutInventory) {
                if (book != null) {
                    writer.write(String.format("%d,%s,%s,%s,%d%n",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getISBN(),
                        book.getNumberOfPages()));
                }
            }
            System.out.println("Inventory saved to " + filepath);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Loads both branch and checkout inventories from a text file in the resources folder
     * Format:
     * [BRANCH]
     * ID,Title,Author,ISBN,NumberOfPages
     * [CHECKOUT]
     * ID,Title,Author,ISBN,NumberOfPages
     */
    public void loadInventoryFromFile(String filename) throws IOException {
        String resourcesPath = getResourcesPath();
        String filepath = resourcesPath + File.separator + filename;
        
        File file = new File(filepath);
        
        if (!file.exists()) {
            System.out.println("File does not exist: " + filepath);
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            branchInventory.clear();
            checkOutInventory.clear();

            List<Book> targetList = branchInventory;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.equalsIgnoreCase("[BRANCH]")) {
                    targetList = branchInventory;
                    continue;
                }
                if (line.equalsIgnoreCase("[CHECKOUT]")) {
                    targetList = checkOutInventory;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        String author = parts[2].trim();
                        String isbn = parts[3].trim();
                        int numberOfPages = Integer.parseInt(parts[4].trim());

                        Book book = new Book(id, title, author, isbn, numberOfPages);
                        targetList.add(book);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing line: " + line);
                    }
                }
            }
            System.out.println("Inventory loaded from " + filepath);
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Gets the resources directory path for inventory storage
     */
    private String getResourcesPath() {
        // Use the project resources folder directly
        String projectRoot = System.getProperty("user.dir");
        return projectRoot + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + "library_system";
    }
}
