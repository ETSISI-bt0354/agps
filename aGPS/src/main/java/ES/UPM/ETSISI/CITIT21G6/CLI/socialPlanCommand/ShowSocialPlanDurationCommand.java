package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class ShowSocialPlanDurationCommand extends SocialPlanCommand
{
    public ShowSocialPlanDurationCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.showSocialPlanDuration(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Show the duration of a social plan";
    }
}
