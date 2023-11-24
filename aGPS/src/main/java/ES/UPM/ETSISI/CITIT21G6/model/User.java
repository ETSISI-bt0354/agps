package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class User
{

    private final String name;
    private Age birthday;
    private Password password;
    private PhoneNumber phoneNumber;
    private List<SocialPlan> socialPlans;
    private List<Ticket> joinedEvents;
    public User(String name, String password, LocalDate birthday, String phoneNumber) throws Exception
    {
        this.name = name;
        this.password = new Password(password);
        this.birthday = new Age(birthday);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.socialPlans = new ArrayList<>();
        this.joinedEvents = new ArrayList<>();
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

    public void addSocialPlan(SocialPlan socialPlan) throws Exception
    {
        if (socialPlans.contains(socialPlan))
            throw new Exception("The plan is already added.");

        socialPlans.add(socialPlan);
    }

    public List<SocialPlan> getSocialPlans()
    {
        return socialPlans;
    }

    public List<Ticket> getJoinedEvents()
    {
        return joinedEvents;
    }

    public void addJoinedEvent(Ticket ticket) throws Exception
    {
        if (joinedEvents.contains(ticket))
            throw new Exception("The user has already joined the social plan.");

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