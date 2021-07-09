package basics.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    @Test
    void shouldAddUserWhenUserDoesNotExist() throws UserAlreadyExistsException {
        // given
        Library library = new Library(new Address("TestStreet", 10), new ArrayList<>(), new ArrayList<>());
        User user = User.create("TestName", "TestLogin", "TestPassword", "test@email.pl");
        // when
        library.addUser(user);
        // then
        assertTrue(library.getUsers()
                .stream()
                .map(User::getEmail)
                .anyMatch(e -> e.equals("test@email.pl")));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        // given
        Address address = new Address("Victory Street", 10);
        List<User> users = new ArrayList<>(List.of(User.create ("TestName", "TestLogin", "TestPassword", "test@email.pl")));
        List<Book> books = new ArrayList<>();
        Library library = new Library(address, users, books);
        User user = User.create("TestName", "TestLogin", "TestPassword", "test@email.pl");
        // when
//        UserAlreadyExistsException exception =
        // then
        assertThrows(UserAlreadyExistsException.class, () -> library.addUser(user));
//        assertEquals(UserAlreadyExistsException.class, exception.getClass());
    }

    @Test
    void shouldReturnUserListIfUsersWereCreatedBeforeSpecifiedDate() throws UserAlreadyExistsException {
        // given
        Library library = new Library(new Address("TestStreet", 10), new ArrayList<>(), new ArrayList<>());
        User user1 = User.create("TestName1", "TestLogin1", "TestPassword1", "test1@email.pl");
        User user2 = User.create("TestName2", "TestLogin2", "TestPassword2", "test2@email.pl");
        // when
        library.addUser(user1);
        library.addUser(user2);
        // then
        assertEquals(2, library.retrieveCreatedUsersBefore(LocalDate.now().plusDays(1)).stream()
                .count());
    }

    @Test
    void shouldReturnEmptyListIfNoUsersWereCreatedBeforeSpecifiedDate() throws UserAlreadyExistsException {
        // given
        Library library = new Library(new Address("TestStreet", 10), new ArrayList<>(), new ArrayList<>());
        User user1 = User.create("TestName1", "TestLogin1", "TestPassword1", "test1@email.pl");
        User user2 = User.create("TestName2", "TestLogin2", "TestPassword2", "test2@email.pl");
        // when
        library.addUser(user1);
        library.addUser(user2);
        // then
        assertEquals(0, (long) library.retrieveCreatedUsersBefore(LocalDate.now().minusDays(1)).size());
    }

    @Test
    void shouldThrowExceptionIfUserDoesNotExistInTheLibrary() {
        // given
        Library library = new Library(new Address("TestStreet", 10), new ArrayList<>(), new ArrayList<>());
        User user = User.create("Marius", "marius35", "MyPassword", "mymail@email.pl");
        Book book = new Book("Sapiens", "Yuval");
        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook(user, book));
    }

}