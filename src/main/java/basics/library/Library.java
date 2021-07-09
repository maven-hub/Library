package basics.library;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private Address address;
    private List<User> users;
    private List<Book> books;

    public Library(Address address, List<User> users, List<Book> books) {
        this.address = address;
        this.users = users;
        this.books = books;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addUser(User user) throws UserAlreadyExistsException {
        if (users.stream()
                .map(User::getEmail)
                .anyMatch(e -> e.equals(user.getEmail()))) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        users.add(user);
    }

    public List<User> retrieveCreatedUsersBefore(LocalDate date) {
        return users.stream()
                .filter(u -> u.getCreated().isBefore(date))
                .collect(Collectors.toList());
    }

    public void borrowBook(User user, Book book) throws BookAlreadyBorrowedException {
        if (users.stream()
                .map(User::getEmail)
                .noneMatch(u -> u.equals(user.getEmail()))) {
            throw new IllegalArgumentException("User doesn't exist.");
        }
        user.borrowBook(book);
        System.out.println("User " + user.getName() + " has borrowed the book " + book.getTitle());
        System.out.println("Book has been borrowed on the date of: " + LocalDate.now());
    }
}
