package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class User
{
    private static final int MINIMUM_AGE = 14;
    private static final int MAXIMUM_AGE = 100;
    private static final int MINIMUM_PASSWORD_LENGTH = 3;

    private static void validateAge(LocalDate birthday) throws Exception
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

    private static void validatePassword(String password) throws Exception
    {
        if (password.length() < MINIMUM_PASSWORD_LENGTH)
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("The password must have at least ");
            errorMessage.append(MINIMUM_PASSWORD_LENGTH);
            errorMessage.append(" characters.");

            throw new Exception(errorMessage.toString());
        }
    }

    private static void validatePhoneNumber(String phoneNumber) throws Exception
    {
        if (Pattern.matches("^[0-9]{9}$", phoneNumber))
            throw new Exception("Invalid phone number");
    }

    private final String name;
    private String password;
    private final LocalDate birthday;
    private String phoneNumber;
    private List<SocialPlan> socialPlans;
    private List<Ticket> joinedEvents;

    public User(String name, String password, LocalDate birthday, String phoneNumber) throws Exception
    {
        validateAge(birthday);
        validatePassword(password);
        validatePassword(phoneNumber);

        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.socialPlans = new ArrayList<>();
        this.joinedEvents = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void addSocialPlan(SocialPlan socialPlan) throws Exception
    {
        if (socialPlans.contains(socialPlan))
            throw new Exception("The plan is already added.");

        socialPlans.add(socialPlan);
    }

    public List<SocialPlan> getSocialPlans(int index)
    {
        return socialPlans;
    }

    public List<Ticket> getJoinedEvents() {
        return joinedEvents;
    }

    public void addJoinedEvent(Ticket ticket) throws Exception
    {
        if (joinedEvents.contains(ticket))
            throw  new Exception("The user has already joined the social plan.");

        joinedEvents.add(ticket);
    }

    protected void removeJoinedEvent(Ticket ticket)
    {
        joinedEvents.remove(ticket);
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