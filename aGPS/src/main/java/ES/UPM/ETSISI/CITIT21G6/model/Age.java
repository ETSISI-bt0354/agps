package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;

public class Age
{
    private LocalDate birthday;

    public Age(LocalDate birthday)
    {
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

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }
}
