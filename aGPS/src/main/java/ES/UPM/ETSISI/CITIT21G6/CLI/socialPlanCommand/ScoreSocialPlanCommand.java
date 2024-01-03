package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class ScoreSocialPlanCommand extends SocialPlanCommand
{
    public ScoreSocialPlanCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.scoreSocialPlan(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Score a social plan";
    }

    @Override
    public String help(List<String> args)
    {
        return controller.setScoreHelp(false);
    }
}
