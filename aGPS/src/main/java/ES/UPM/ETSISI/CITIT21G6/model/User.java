package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private static final int MINIMUMAGE = 14;
    private static final int MAXIMUMAGE = 100;

    private static void validateAge(LocalDate birthday) throws Exception
    {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthday, currentDate);

        if (age.getYears() < MINIMUMAGE)
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Minimum age is ");
            errorMessage.append(MINIMUMAGE);
            errorMessage.append("years old");

            throw new Exception(errorMessage.toString());
        }

        if (age.getYears() > MAXIMUMAGE)
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Maximum age is ");
            errorMessage.append(MAXIMUMAGE);
            errorMessage.append("years old");

            throw new Exception(errorMessage.toString());
        }
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

        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.socialPlans = new ArrayList<>();
        this.joinedEvents = new ArrayList<>();
    }


    public void addSocialPlan(SocialPlan socialPlan)
    {
        if (socialPlans.contains(socialPlan))
            throw new IllegalArgumentException("The plan is already added");

        socialPlans.add(socialPlan);
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

    public SocialPlan getSocialPlan(int index) throws Exception
    {
        SocialPlan socialPlan = null;
        try
        {
            socialPlan = socialPlans.get(index);
        } catch (IndexOutOfBoundsException e)
        {
            throw new Exception("There is no social plan in that index");
        }

        return socialPlan;
    }

    public List<Ticket> getJoinedEvents() {
        return joinedEvents;
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

    public void addTicket(Ticket ticket) throws Exception
    {
        if (joinedEvents.contains(ticket))
            throw  new Exception("The user already has that ticket");

        joinedEvents.add(ticket);
    }

    protected void deleteTicket(Ticket ticket)
    {
        joinedEvents.remove(ticket);
    }
}