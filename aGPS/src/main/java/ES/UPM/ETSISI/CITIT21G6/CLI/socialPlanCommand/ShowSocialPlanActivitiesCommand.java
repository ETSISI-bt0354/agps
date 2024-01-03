package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class ShowSocialPlanActivitiesCommand extends SocialPlanCommand
{
    public ShowSocialPlanActivitiesCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.showSocialPlanActivities(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Show activities of a social plan";
    }

    @Override
    public String help(List<String> args)
    {
        return controller.showActivitiesHelp(false);
    }
}
