package basics.library;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        Address address = new Address("Victory Street", 10);
        List<User> users = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        User user1 = User.create("Marius", "marius43", "MyPassword1", "mymail1@email.pl");
        User user2 = User.create("Darius", "darius32", "MyPassword2", "mymail2@email.pl");

        Book book1 = new Book("Sapiens", "Yuval");
        Book book2 = new Book("Homo Deus", "Yuval");

        Library library = new Library(address, users, books);

        try {
            library.addUser(user1);
            library.addUser(user2);
            library.borrowBook(user1, book1);
            library.borrowBook(user2, book2);
        } catch (UserAlreadyExistsException | BookAlreadyBorrowedException e) {
            e.printStackTrace();
        }

    }
}
