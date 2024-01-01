package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class AddActivityCommand extends SocialPlanCommand
{
    public AddActivityCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.addActivity(args);
    }

    @Override
    public String description()
    {
        return "Add activity to social plan";
    }
}
