package library_system;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.application.Platform;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.image.Image;

public class PrimaryController {

    private Inventory inventory;

    @FXML
    private FlowPane rootPane;
    
    @FXML
    private TextArea outputArea;

    @FXML
    private Button addBookButton;

    @FXML
    private Button borrowBookButton;

    @FXML
    private Button returnBookButton;

    @FXML
    private Button searchByTitleButton;

    @FXML
    private Button printAllBooksButton;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        // Initialize inventory
        inventory = new Inventory();
        
        // Load inventory from file on startup
        try {
            inventory.loadInventoryFromFile("inventory.txt");
        } catch (IOException e) {
            System.out.println("No existing inventory file. Starting with empty inventory.");
        }
        
        // Redirect System.out to TextArea
        System.setOut(new PrintStream(new OutputStream() {
            private StringBuilder buffer = new StringBuilder();
            
            @Override
            public void write(int b) throws IOException {
                char c = (char) b;
                buffer.append(c);
                
                // Only update UI when we hit a newline
                if (c == '\n') {
                    String text = buffer.toString();
                    buffer.setLength(0);
                    Platform.runLater(() -> outputArea.appendText(text));
                }
            }
        }));
        
        // Set background image programmatically
        Image image = new Image(getClass().getResourceAsStream("/library_system/BookShelf.jpg"));
        BackgroundImage bgImage = new BackgroundImage(
            image,
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );
        rootPane.setBackground(new Background(bgImage));
    }

    @FXML
    private void handleAddBook() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Add Book");
        idDialog.setHeaderText("Enter book details:");
        idDialog.setContentText("Book ID:");

        idDialog.showAndWait().ifPresent(id -> {
            try {
                int bookId = Integer.parseInt(id);
                
                TextInputDialog titleDialog = new TextInputDialog();
                titleDialog.setTitle("Add Book");
                titleDialog.setContentText("Title:");
                
                titleDialog.showAndWait().ifPresent(title -> {
                    if (!title.trim().isEmpty()) {
                        TextInputDialog authorDialog = new TextInputDialog();
                        authorDialog.setTitle("Add Book");
                        authorDialog.setContentText("Author:");
                        
                        authorDialog.showAndWait().ifPresent(author -> {
                            if (!author.trim().isEmpty()) {
                                TextInputDialog isbnDialog = new TextInputDialog();
                                isbnDialog.setTitle("Add Book");
                                isbnDialog.setContentText("ISBN:");
                                
                                isbnDialog.showAndWait().ifPresent(isbn -> {
                                    if (isbn.length() != 13) {
                                        System.out.println("Error: ISBN must be exactly 13 characters long.");
                                        printStatus("Add book", false);
                                        return;
                                    }
                                    try {
                                        Long.parseLong(isbn);

                                        TextInputDialog pagesDialog = new TextInputDialog();
                                        pagesDialog.setTitle("Add Book");
                                        pagesDialog.setContentText("Number of Pages:");
                                        
                                        pagesDialog.showAndWait().ifPresent(pages -> {
                                            try {
                                                int numPages = Integer.parseInt(pages);
                                                inventory.addBook(bookId, title, author, isbn, numPages);
                                                printStatus("Add book", true);
                                            } catch (NumberFormatException e) {
                                                System.out.println("Pages must be a valid number.");
                                                printStatus("Add book", false);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Error: " + e.getMessage());
                                                printStatus("Add book", false);
                                            }
                                        });
                                    } catch (NumberFormatException e) {
                                        System.out.println("ISBN must contain only numeric characters.");
                                        printStatus("Add book", false);
                                    }
                                });
                            } else {
                                System.out.println("Author cannot be empty.");
                                printStatus("Add book", false);
                            }
                        });
                    } else {
                        System.out.println("Title cannot be empty.");
                        printStatus("Add book", false);
                    }
                });
            } catch (NumberFormatException e) {
                System.out.println("Book ID must be a valid number.");
                printStatus("Add book", false);
            }
        });
    }

    @FXML
    private void handleBorrowBook() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Borrow Book");
        dialog.setHeaderText("Enter the title of the book to borrow:");
        dialog.setContentText("Title:");
        
        dialog.showAndWait().ifPresent(title -> {
            if (!title.trim().isEmpty()) {
                try {
                    List<Book> matchingBooks = inventory.borrowBook(title);
                    
                    // If empty list, single book was borrowed successfully
                    if (matchingBooks.isEmpty()) {
                        printStatus("Borrow book", true);
                        return;
                    }
                    
                    // Multiple matches - show options to user
                    System.out.println("Multiple books found with similar titles:");
                    for (int i = 0; i < matchingBooks.size(); i++) {
                        System.out.println((i + 1) + ". " + matchingBooks.get(i).getTitle() + 
                                        " by " + matchingBooks.get(i).getAuthor());
                    }
                    
                    TextInputDialog selectionDialog = new TextInputDialog();
                    selectionDialog.setTitle("Select Book");
                    selectionDialog.setHeaderText("Multiple books found:");
                    selectionDialog.setContentText("Select book number:");
                    
                    selectionDialog.showAndWait().ifPresent(choice -> {
                        try {
                            int selection = Integer.parseInt(choice) - 1;
                            inventory.borrowBookSelection(matchingBooks, selection);
                            printStatus("Borrow book", true);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid selection. Please enter a number.");
                            printStatus("Borrow book", false);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                            printStatus("Borrow book", false);
                        }
                    });
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    printStatus("Borrow book", false);
                }
            } else {
                System.out.println("Title cannot be empty.");
                printStatus("Borrow book", false);
            }
        });
    }

    @FXML
    private void handleReturnBook() {
        if (inventory.getCheckOutInventoryCount() == 0) {
            System.out.println("No books are currently checked out.");
            printStatus("Return book", false);
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Return Book");
        dialog.setHeaderText("Enter the title of the book to return:");
        dialog.setContentText("Title:");
        
        dialog.showAndWait().ifPresent(title -> {
            if (!title.trim().isEmpty()) {
                try {
                    List<Book> matchingBooks = inventory.returnBook(title);

                    // If empty list, single book was returned successfully
                    if (matchingBooks.isEmpty()) {
                        printStatus("Return book", true);
                        return;
                    }

                    System.out.println("Multiple checked-out books found with similar titles:");
                    for (int i = 0; i < matchingBooks.size(); i++) {
                        System.out.println((i + 1) + ". " + matchingBooks.get(i).getTitle() +
                                        " by " + matchingBooks.get(i).getAuthor());
                    }

                    TextInputDialog selectionDialog = new TextInputDialog();
                    selectionDialog.setTitle("Select Book");
                    selectionDialog.setHeaderText("Multiple books found:");
                    selectionDialog.setContentText("Select book number:");

                    selectionDialog.showAndWait().ifPresent(choice -> {
                        try {
                            int selection = Integer.parseInt(choice) - 1;
                            inventory.returnBookSelection(matchingBooks, selection);
                            printStatus("Return book", true);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid selection. Please enter a number.");
                            printStatus("Return book", false);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                            printStatus("Return book", false);
                        }
                    });
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    printStatus("Return book", false);
                }
            } else {
                System.out.println("Title cannot be empty.");
                printStatus("Return book", false);
            }
        });
    }

    @FXML
    private void handleSearchByTitle() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search by Title");
        dialog.setHeaderText("Enter the title to search for:");
        dialog.setContentText("Title:");
        
        dialog.showAndWait().ifPresent(title -> {
            if (!title.trim().isEmpty()) {
                List<Book> results = inventory.searchByTitle(title);
                if (results.isEmpty()) {
                    System.out.println("No books found with title: " + title);
                    printStatus("Search", false);
                } else {
                    System.out.println("=== SEARCH RESULTS ===");
                    for (Book book : results) {
                        book.printBookInfo();
                        System.out.println("---");
                    }
                    printStatus("Search", true);
                }
            } else {
                System.out.println("Title cannot be empty.");
                printStatus("Search", false);
            }
        });
    }

    @FXML
    private void handlePrintAllBooks() {
        inventory.printAll();
        printStatus("Print all", true);
    }

    @FXML
    public void handleExit() {
        // Save inventory before exiting
        try {
            inventory.saveInventoryToFile("inventory.txt");
            printStatus("Save inventory", true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Exiting the program, Goodbye");
            alert.setContentText("Click OK to exit the application.");
            alert.showAndWait();
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
            printStatus("Save inventory", false);
        }
        Platform.exit();
    }

    private void printStatus(String operation, boolean successful) {
        System.out.println(operation + ": " + (successful ? "Successful" : "Unsuccessful"));
    }
}
