package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;
import java.time.Period;

public class Age
{
    private static final int MINIMUM_AGE = 14;
    private static final int MAXIMUM_AGE = 100;
    private LocalDate birthday;

    public Age(LocalDate birthday) throws Exception
    {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthday, currentDate);

        if (age.getYears() < MINIMUM_AGE)
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Minimum age is ");
            errorMessage.append(MINIMUM_AGE);
            errorMessage.append(" years old.");

            throw new Exception(errorMessage.toString());
        }

        if (age.getYears() > MAXIMUM_AGE)
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Maximum age is ");
            errorMessage.append(MAXIMUM_AGE);
            errorMessage.append(" years old.");

            throw new Exception(errorMessage.toString());
        }
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