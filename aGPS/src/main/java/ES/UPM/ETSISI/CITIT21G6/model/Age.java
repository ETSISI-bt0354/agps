package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAge;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;

import java.time.LocalDate;
import java.time.Period;

public class Age
{
    private static final int MINIMUM_AGE = 14;
    private static final int MAXIMUM_AGE = 100;
    private LocalDate birthday;

    public Age(LocalDate birthday) throws InvalidAgeException
    {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthday, currentDate);

        if (age.getYears() < MINIMUM_AGE)
            throw new InvalidAgeException(InvalidAge.TOOYOUNG, MINIMUM_AGE, age.getYears());

        if (age.getYears() > MAXIMUM_AGE)
            throw new InvalidAgeException(InvalidAge.TOOOLD, MAXIMUM_AGE, age.getYears());

        this.birthday = birthday;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public int getAge()
    {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - birthday.getYear();
    }
}