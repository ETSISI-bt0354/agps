package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class ShowSocialPlanActivitiesCommand extends SocialPlanCommand
{
    public ShowSocialPlanActivitiesCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.showSocialPlanActivities(args);
    }

    @Override
    public String description()
    {
        return "Show activities of a social plan";
    }
}
