package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class ListSuscribedSocialPlanCommand extends SocialPlanCommand
{
    public ListSuscribedSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.listSubscribedSocialPlans(args);
    }

    @Override
    public String description()
    {
        return "List subscribed social plans.";
    }
}
