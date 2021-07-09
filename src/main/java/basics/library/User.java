package basics.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String login;
    private String password;
    private String email;
    private LocalDate created;
    private List<Book> borrowedBooks;

    private User(String name, String login, String password, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.created = LocalDate.now();
        this.borrowedBooks = new ArrayList<>();
    }

    public static User create(String name, String login, String password, String email) {
        if (name == null || login == null || password == null || email == null) {
            throw new NullPointerException();
        }

        if (login.length() <= 5) {
            throw new IllegalArgumentException("Login needs to be at least 6 characters long");
        }

        return new User(name, login, password, email);
    }

    public void addBook(Book book) throws BookAlreadyBorrowedException {
        if (borrowedBooks.stream()
                .map(Book::getTitle)
                .anyMatch(b -> b.equals(book.getTitle()))) {
            throw new BookAlreadyBorrowedException("You already have this book");
        }
        borrowedBooks.add(book);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreated() {
        return created;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return name + " " + login + " " + password + " " + email + " " + created;
    }
}
