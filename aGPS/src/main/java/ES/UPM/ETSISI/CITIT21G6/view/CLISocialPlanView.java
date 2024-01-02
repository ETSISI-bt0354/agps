package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.ListOrderException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.FutureSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.PastSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.SocialPlanCollisionException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.model.Activity;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.model.Ticket;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.OptionalInt;

public class CLISocialPlanView implements SocialPlanView
{

    @Override
    public String create(SocialPlan socialPlan)
    {
        StringBuilder message = new StringBuilder();
        message.append("The socialPlan ");
        message.append(socialPlan.getName());
        message.append(" has been created successfully.");
        return message.toString();
    }

    @Override
    public String delete(SocialPlanId id)
    {
        StringBuilder message = new StringBuilder();
        message.append("The socialPlan with id ");
        message.append(id);
        message.append(" has been deleted successfully.");
        return message.toString();
    }

    @Override
    public String addActivity(Activity activity)
    {
        StringBuilder message = new StringBuilder();
        message.append("The activity ");
        message.append(activity.getName());
        message.append(" has been added successfully.");
        return message.toString();
    }

    @Override
    public String insufficientArguments(int requiredArguments)
    {
        StringBuilder error = new StringBuilder();
        error.append("You must provide at least ");
        error.append(requiredArguments);
        error.append(" arguments.");
        return error.toString();
    }

    @Override
    public String noLoggedUser()
    {
        return "You must be logged in to perform this action.";
    }

    @Override
    public String listPlans(List<SocialPlan> socialPlans)
    {
        StringBuilder message = new StringBuilder();
        message.append("Plans: ");
        for (SocialPlan socialPlan : socialPlans)
        {
            message.append("\n");
            message.append(listPlan(socialPlan));
        }

        return message.toString();
    }

    private String listPlan(SocialPlan socialPlan)
    {
        StringBuilder message = new StringBuilder();
        message.append("\tOwner: ");
        message.append(socialPlan.getOwnerName());
        message.append("\n\tName: ");
        message.append(socialPlan.getName());
        message.append("\n\tDate: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        message.append(socialPlan.getDate().format(formatter));
        message.append("\n\tLocation: ");
        message.append(socialPlan.getLocation());
        message.append("\n\tCapacity: ");
        if(socialPlan.getCapacity().isEmpty())
            message.append("Unlimited");
        else
        {
            message.append(socialPlan.getCapacity().getAsInt());
            message.append("\n\tFree spots: ");
            message.append(socialPlan.getCapacity().getAsInt() - socialPlan.getParticipants().size());
        }
        return message.toString();
    }
    @Override
    public String unjoinSocialPlan(String participantName)
    {
        StringBuilder message = new StringBuilder();
        message.append("The participant ");
        message.append(participantName);
        message.append(" has been removed successfully.");
        return message.toString();
    }

    @Override
    public String joinSocialPlan(String participantName)
    {
        StringBuilder message = new StringBuilder();
        message.append("The participant ");
        message.append(participantName);
        message.append(" has been added successfully.");
        return message.toString();
    }

    @Override
    public String socialPlanNotFound(SocialPlanNotFoundException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The social plan ");
        error.append(e.getId());
        error.append(" does not exist, please try again.");
        return error.toString();
    }

    @Override
    public String price(double price)
    {
        StringBuilder message = new StringBuilder();
        message.append("Price: ");
        message.append(price);
        return message.toString();
    }

    @Override
    public String invalidCapacity(InvalidCapacityException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The capacity provided is ");
        switch (e.getReason())
        {
            case TOOBIG -> error.append("too big.");
            case TOOSMALL -> error.append("too small.");
            case NEGATIVEORZERO ->
            {
                if(e.getCapacity().getAsInt() == 0)
                    error.append("zero");
                else
                    error.append("negative");
            }
        }
        return error.toString();
    }

    @Override
    public String socialPlanAlreadyAdded(SocialPlanAlreadyAddedException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The social plan ");
        error.append(e.getSocialPlan().getId());
        error.append(" already exists.");
        return error.toString();
    }

    @Override
    public String activityAlreadyInSocialPlan(ActivityAlreadyInSocialPlanException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The activity ");
        error.append(e.getActivity().getName());
        error.append(" is already added to the social plan.");
        return error.toString();
    }

    @Override
    public String fullSocialPlan(FullSocialPlanException e)
    {
        return "The social plan is full.";
    }

    @Override
    public String ParticipantAlreadyInSocialPlan(ParticipantAlreadyInSocialPlanException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The participant ");
        error.append(e.getParticipantName());
        error.append(" is already added to the social plan.");
        return error.toString();
    }

    @Override
    public String wrongListOrder(ListOrderException e)
    {
        StringBuilder error = new StringBuilder();
        error.append(e.getInput());
        error.append(" is not a valid option for sorting.");
        return error.toString();
    }

    @Override
    public String invalidScore(InvalidScoreException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The score is ");
        if(e.getScore() < e.getMinimumScore())
            error.append("too small");
        else
            error.append("too big");
        error.append(" ( ");
        error.append(e.getMinimumScore());
        error.append(" - ");
        error.append(e.getMaximumScore());
        error.append(").");
        return error.toString();
    }

    @Override
    public String setScore(OptionalInt score)
    {
        StringBuilder message = new StringBuilder();
        message.append("Score: ");
        if(score.isEmpty())
            message.append("none.");
        else
            message.append(score.getAsInt());
        return message.toString();
    }

    @Override
    public String collisionWithOtherSocialPlan(SocialPlanCollisionException e)
    {
        StringBuilder message = new StringBuilder();
        message.append("This social plan collides with teh social plan ");
        message.append(e.getSocialPlanCollision().getId());
        message.append(" on date ");
        message.append(e.getSocialPlanCollision().getDate());
        message.append(".\nYou cannot join this social plan.");
        return message.toString();
    }

    @Override
    public String pastSocialPlan(PastSocialPlanException e)
    {
        return "The social plan has already taken place";
    }

    @Override
    public String setScoreFutureSocialPlan(FutureSocialPlanException e)
    {
        return "Hey! Unless you're a time traveller, you can't set a score just yet. The social plan is in the future.";
    }

    @Override
    public String participantNotFound(ParticipantNotFoundException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The user ");
        error.append(e.getParticipantName());
        error.append(" is not in this social plan, please try again.");
        return error.toString();
    }

    @Override
    public String createSocialPlanPastDate(PastDateException e)
    {
        StringBuilder error = new StringBuilder("Woah there, this social plan is set for the past (");
        error.append(e.getDate());
        error.append("). Check your clock if you think this is a mistake (or change that CMOS battery...)");
        return error.toString();
    }

    @Override
    public String showActivities(List<Activity> activities)
    {
        StringBuilder message = new StringBuilder();
        message.append("Activities:");
        for (Activity activity : activities)
        {
            message.append("\n");
            message.append(showActivity(activity));
        }
        return message.toString();
    }

    private String showActivity(Activity activity)
    {
        StringBuilder message = new StringBuilder();
        message.append("\tName: ");
        message.append(activity.getName());
        message.append("\n\tDescription: ");
        message.append(activity.getDescription());
        message.append("\n\tDuration: ");
        message.append(activity.getDuration());
        message.append("\n\tCapacity: ");
        if(activity.getCapacity().isEmpty())
            message.append("Unlimited");
        else
            message.append(activity.getCapacity().getAsInt());
        message.append("\n\tPrice: ");
        message.append(activity.getPrice());
        message.append("\n\tType: ");
        String type = switch (activity.getType()) {
            case GENERIC -> "generic";
            case CINEMA -> "cinema";
            case THEATRE -> "theatre";
        };
        message.append(type);
        return message.toString();

    }

    @Override
    public String showParticipants(List<Ticket> participants)
    {
        StringBuilder message = new StringBuilder();
        message.append("Participants:");
        for (Ticket participant : participants)
        {
            message.append("\n\t");
            message.append(participant.getUserName());
        }
        return message.toString();
    }

    @Override
    public String showDuration(int duration)
    {
        StringBuilder message = new StringBuilder();
        message.append("Duration: ");
        message.append(duration);
        message.append(".");
        return message.toString();
    }

    @Override
    public String setSocialPlanCapacity(OptionalInt capacity)
    {
        StringBuilder message = new StringBuilder();
        message.append("Capacity: ");
        if (capacity.isEmpty())
            message.append("Unlimited");
        else
            message.append(capacity.getAsInt());
        message.append(".");
        return message.toString();
    }

    @Override
    public String invalidLocalDateTimeFormat(DateTimeParseException e)
    {
        StringBuilder message = new StringBuilder();
        message.append(e.getParsedString());
        message.append(" is not a valid date or it is in a wrong format (Format: yyyy-mm-dd hh-mm example: 2023-04-03 03:05).");
        return message.toString();
    }

    @Override
    public String invalidNumber(NumberFormatException e)
    {
        return e.getMessage();
    }
}
