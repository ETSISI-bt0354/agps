package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class ShowSocialPlanDurationCommand extends SocialPlanCommand
{
    public ShowSocialPlanDurationCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.showSocialPlanDuration(args);
    }

    @Override
    public String description()
    {
        return "Show the duration of a social plan";
    }
}
