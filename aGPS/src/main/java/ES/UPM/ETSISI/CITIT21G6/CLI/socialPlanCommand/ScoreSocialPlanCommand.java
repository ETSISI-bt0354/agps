package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class ScoreSocialPlanCommand extends SocialPlanCommand
{
    public ScoreSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.scoreSocialPlan(args);
    }

    @Override
    public String description()
    {
        return "Score a social plan";
    }
}
