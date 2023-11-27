package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;

import java.time.LocalDate;

public class User
{
    private final String name;
    private Age age;
    private Password password;
    private PhoneNumber phoneNumber;

    public User(String name, String password, LocalDate birthday, String phoneNumber)
            throws InvalidPasswordException, InvalidAgeException, InvalidPhoneNumberException
    {
        this.name = name;
        this.password = new Password(password);
        this.age = new Age(birthday);
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
        return age.getBirthday();
    }

    public String getPhoneNumber()
    {
        return phoneNumber.getPhoneNumber();
    }
    public int getAge()
    {
        return age.getAge();
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