package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class JoinSocialPlanCommand extends SocialPlanCommand
{
    public JoinSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.joinSocialPlan(args);
    }

    @Override
    public String description()
    {
        return "Join to a social plan";
    }
}
