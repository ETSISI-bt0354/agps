package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class DeleteSocialPlanCommand extends SocialPlanCommand
{
    public DeleteSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.deleteSocialPlan(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Delete social plan";
    }
}
