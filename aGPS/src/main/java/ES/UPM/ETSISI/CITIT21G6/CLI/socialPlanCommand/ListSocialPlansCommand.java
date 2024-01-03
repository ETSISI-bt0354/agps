package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class ListSocialPlansCommand extends SocialPlanCommand
{
    public ListSocialPlansCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.listSocialPlans(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "List all future social plans";
    }

    @Override
    public String help(List<String> args)
    {
        return controller.listSocialPlansHelp(false);
    }
}
