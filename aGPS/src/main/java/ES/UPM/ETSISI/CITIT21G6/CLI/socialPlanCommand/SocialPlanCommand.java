package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.CLI.Command;
import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

public abstract class SocialPlanCommand implements Command
{
    protected SocialPlanController controller;

    public SocialPlanCommand(SocialPlanController controller)
    {
        this.controller = controller;
    }
}
