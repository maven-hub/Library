package basics.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWhenArgumentsAreCorrect() {
        // given
        String name = "Name";
        String login = "Login6";
        String password = "something";
        String email = "example@test.pl";
        // when
        User user = User.create(name, login, password, email);
        // then
        assertEquals("Name", user.getName());
        assertEquals("Login6", user.getLogin());
        assertEquals("something", user.getPassword());
        assertEquals("example@test.pl", user.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenLoginIsTooShort() {
        // given
        String name = "Name";
        String login = "Log";
        String password = "something";
        String email = "example@test.pl";
        // when
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> User.create(name, login, password, email));
        // then
        assertEquals("Login needs to be at least 6 characters long", expectedException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAnyArgumentIsNull() {
        // given
        String name = null;
        String login = "Login6";
        String password = "something";
        String email = "example@test.pl";
        // when

        // then
        assertThrows(RuntimeException.class, () -> User.create(name, login, password, email));
//        assertEquals(RuntimeException.class, expectedException.getClass());
    }
}