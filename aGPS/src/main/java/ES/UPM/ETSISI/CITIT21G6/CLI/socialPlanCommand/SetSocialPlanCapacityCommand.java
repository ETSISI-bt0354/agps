package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class SetSocialPlanCapacityCommand extends SocialPlanCommand
{
    public SetSocialPlanCapacityCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.setSocialPlanCapacity(args);
    }

    @Override
    public String description()
    {
        return "Set the capacity of a social plan";
    }
}
