package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class UnjoinSocialPlanCommand extends SocialPlanCommand
{
    public UnjoinSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.unjoinSocialPlan(args);
    }

    @Override
    public String description()
    {
        return "Unjoin from a social plan";
    }
}
