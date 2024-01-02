package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class CreateSocialPlanCommand extends SocialPlanCommand
{
    public CreateSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.createSocialPlan(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Create social plan";
    }

    @Override
    public String help(List<String> args)
    {
        return controller.createSocialPlanHelp();
    }
}
