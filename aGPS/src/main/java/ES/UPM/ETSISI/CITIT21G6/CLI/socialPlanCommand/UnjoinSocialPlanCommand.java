package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class UnjoinSocialPlanCommand extends SocialPlanCommand
{
    public UnjoinSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.unjoinSocialPlan(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Unjoin from a social plan";
    }
}
