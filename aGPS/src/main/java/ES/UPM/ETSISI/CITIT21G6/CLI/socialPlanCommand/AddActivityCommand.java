package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class AddActivityCommand extends SocialPlanCommand
{
    public AddActivityCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.addActivity(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Add activity to social plan";
    }

    @Override
    public String help(List<String> args)
    {
        return controller.addActivityHelp(false);
    }
}
