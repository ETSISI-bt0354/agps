package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView;

import java.time.LocalDate;
import java.util.OptionalInt;

public class SocialPlanController extends SessionController
{
    private static final int MINIMUM_CREATION_ARGUMENT_LENGTH = 3;
    private static final int MINIMUM_DELETE_ARGUMENT_LENGTH = 1;
    private SocialPlanRepository repository;
    private SocialPlanView view;

    public SocialPlanController(SocialPlanRepository repository, SocialPlanView view)
    {
        super();
        this.repository = repository;
        this.view = view;
    }

    public String createSocialPlan(String[] args)
    {
        String result;
        User loggedUser = getLoggedUser();

        try
        {
            if(args.length < MINIMUM_CREATION_ARGUMENT_LENGTH)
            {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("You must provide at least ");
                errorMessage.append(MINIMUM_CREATION_ARGUMENT_LENGTH);
                errorMessage.append(" arguments to create a social plan.");

                throw new Exception(errorMessage.toString());
            }

            if(loggedUser == null)
                throw new Exception("You must be logged in to create a social plan");

            SocialPlan newPlan = new SocialPlan(args[0], LocalDate.parse(args[1]), args[2]);

            if(args.length == MINIMUM_CREATION_ARGUMENT_LENGTH + 1)
            {
                newPlan.setCapacity(OptionalInt.of(Integer.parseInt(args[3])));
            }

            loggedUser.addSocialPlan(newPlan);
            repository.save(newPlan);
            result = view.create(newPlan);
        }
        catch(Exception e)
        {
            result = e.getMessage();
        }

        return result;
    }

    public String deleteSocialPlan(String[] args)
    {
        String result;

        try
        {
            if(args.length < MINIMUM_DELETE_ARGUMENT_LENGTH)
            {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("You must provide at least ");
                errorMessage.append(MINIMUM_CREATION_ARGUMENT_LENGTH);
                errorMessage.append(" arguments to delete a social plan.");

                throw new Exception(errorMessage.toString());
            }

            if(getLoggedUser() == null)
                throw new Exception("You must be logged in to delete a social plan");

            SocialPlan planToDelete = repository.findByName(args[0]);
            User loggedUser = getLoggedUser();
            loggedUser.getSocialPlans().remove(planToDelete);
            repository.delete(planToDelete);
            result = view.delete(planToDelete);
        }
        catch (Exception e)
        {
            result = e.getMessage();
        }
        return result;
    }
}
