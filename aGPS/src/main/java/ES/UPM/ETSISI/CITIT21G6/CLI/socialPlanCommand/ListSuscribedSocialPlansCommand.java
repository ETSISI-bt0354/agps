package ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand;

import ES.UPM.ETSISI.CITIT21G6.CLI.CommandHelper;
import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;

import java.util.List;

public class ListSuscribedSocialPlansCommand extends SocialPlanCommand
{
    public ListSuscribedSocialPlansCommand(SocialPlanController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.listSubscribedSocialPlans(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "List subscribed social plans";
    }

    @Override
    public String help(List<String> args, String prefixCommand)
    {
        StringBuilder message = CommandHelper.prefixCommandBuilder(prefixCommand, " ");
        message.append(controller.listSubscribedSocialPlansHelp());
        return message.toString();
    }
}
