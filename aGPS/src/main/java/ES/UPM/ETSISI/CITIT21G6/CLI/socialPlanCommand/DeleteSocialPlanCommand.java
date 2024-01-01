package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class DeleteSocialPlanCommand extends SocialPlanCommand
{
    public DeleteSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.deleteSocialPlan(args);
    }

    @Override
    public String description()
    {
        return "Delete social plan";
    }
}
