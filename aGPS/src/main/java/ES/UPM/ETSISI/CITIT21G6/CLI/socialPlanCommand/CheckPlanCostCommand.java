package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class CheckPlanCostCommand extends SocialPlanCommand
{
    public CheckPlanCostCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.checkPlanCost(args);
    }

    @Override
    public String description()
    {
        return "Check cost of social plan";
    }
}
