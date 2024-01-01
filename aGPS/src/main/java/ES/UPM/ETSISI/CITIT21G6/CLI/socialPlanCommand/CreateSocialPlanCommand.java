package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class CreateSocialPlanCommand extends SocialPlanCommand
{
    public CreateSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.createSocialPlan(args);
    }

    @Override
    public String description()
    {
        return "Create social plan";
    }
}
