package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;
import java.util.OptionalInt;

public class SocialPlanController extends SessionController
{
    private SocialPlanRepository repository;
    private SocialPlanView view;

    public SocialPlanController(SocialPlanRepository repository, SocialPlanView view)
    {
        this.repository = repository;
        this.view = view;
    }

    public String createSocialPlan(String[] args)
    {
        String result = null;

        try
        {
            if(args.length < 3)
                throw new Exception("You must provide at least 3 arguments to create a social plan.");

            SocialPlan newPlan = new SocialPlan(args[0], LocalDate.parse(args[1]), args[2]);

            if(args.length == 4)
            {
                newPlan.setCapacity(OptionalInt.of(Integer.parseInt(args[3])));
            }

            repository.save(newPlan);
            result = view.create(newPlan);
        }
        catch(Exception e)
        {
            result = e.getMessage();
        }

        return result;
    }
}
