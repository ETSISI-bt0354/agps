package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class SetSocialPlanCapacityCommand extends SocialPlanCommand
{
    public SetSocialPlanCapacityCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.setSocialPlanCapacity(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Set the capacity of a social plan";
    }
}
