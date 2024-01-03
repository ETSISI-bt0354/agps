package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class CheckPlanCostCommand extends SocialPlanCommand
{
    public CheckPlanCostCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.checkPlanCost(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Check cost of social plan";
    }

    @Override
    public String help(List<String> args)
    {
        return controller.checkPlanCostHelp(false);
    }
}
