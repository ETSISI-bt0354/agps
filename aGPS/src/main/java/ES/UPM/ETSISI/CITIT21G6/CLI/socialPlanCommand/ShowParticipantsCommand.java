package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public class ShowParticipantsCommand extends SocialPlanCommand
{
    public ShowParticipantsCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.showParticipants(args);
    }

    @Override
    public String description()
    {
        return "Show participants of a social plan";
    }
}
