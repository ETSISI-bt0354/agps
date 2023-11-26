package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private final String name;
    private Age birthday;
    private Password password;
    private PhoneNumber phoneNumber;

    public User(String name, String password, LocalDate birthday, String phoneNumber)
            throws InvalidPasswordException, InvalidAgeException, InvalidPhoneNumberException
    {
        this.name = name;
        this.password = new Password(password);
        this.birthday = new Age(birthday);
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }


    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password.getPassword();
    }

    public LocalDate getBirthday()
    {
        return birthday.getBirthday();
    }

    public String getPhoneNumber()
    {
        return phoneNumber.getPhoneNumber();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getName().equals(user.getName());
    }

    @Override
    public int hashCode()
    {
        return getName().hashCode();
    }
}