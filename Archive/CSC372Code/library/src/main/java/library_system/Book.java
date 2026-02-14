package library_system;

public class Book {

private Integer id;
private String title;
private String author;
private String ISBN;
private Integer numberOfPages;

public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getAuthor() {
    return author;
}
public void setAuthor(String author) {
    this.author = author;
}
public String getISBN() {
    return ISBN;
}
public void setISBN(String iSBN) {
    ISBN = iSBN;
}
public Integer getNumberOfPages() {
    return numberOfPages;
}
public void setNumberOfPages(Integer numberOfPages) {
    this.numberOfPages = numberOfPages;
}

public Book(Integer id, String title, String author, String iSBN, Integer numberOfPages) {
    super();
    this.id = id;
    this.title = title;
    this.author = author;
    ISBN = iSBN;
    this.numberOfPages = numberOfPages;
}
public void printBookInfo() {
    System.out.println("ID: " + id);
    System.out.println("Title: " + title);
    System.out.println("Author: " + author);
    System.out.println("ISBN: " + ISBN);
    System.out.println("Number of Pages: " + numberOfPages);
}
}
