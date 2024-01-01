package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class ListSocialPlandCommand extends SocialPlanCommand
{
    public ListSocialPlandCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.listSocialPlans(args);
    }

    @Override
    public String description()
    {
        return "List all future social plans";
    }
}
