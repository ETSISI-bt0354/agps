package ES.UPM.ETSISI.CITIT21G6.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
    @Test
    void createUnderAgeUser()
    {
        assertThrows(Exception.class, () ->
                new User("A", "aaa",
                        LocalDate.now().minusYears(10), "12346789"));
    }

    @Test
    void createOverAgeUser()
    {
        assertThrows(Exception.class, () ->
                new User("A", "aaa",
                        LocalDate.now().minusYears(200), "123456789"));
    }

    @Test
    void createUserWithInvalidPassword()
    {
        assertThrows(Exception.class, () ->
                new User("A", "aa",
                        LocalDate.now().minusYears(30), "123456789"));
    }

    @Test
    void createUserWithInvalidPhoneNumber()
    {
        assertThrows(Exception.class, () ->
                new User("A", "aaa",
                        LocalDate.now().minusYears(30), "43143"));

        assertThrows(Exception.class, () ->
                new User("A", "aaa",
                        LocalDate.now().minusYears(30), "1234567890124"));

        assertThrows(Exception.class, () ->
                new User("A", "aaa",
                        LocalDate.now().minusYears(30), "1234a6789"));
    }
}