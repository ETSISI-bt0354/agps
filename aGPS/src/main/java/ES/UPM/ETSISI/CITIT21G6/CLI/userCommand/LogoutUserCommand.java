package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.CLI.CommandHelper;
import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

import java.util.List;

public class LogoutUserCommand extends UserCommand
{
    public LogoutUserCommand(UserController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.logoutUser(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Log out user";
    }

    @Override
    public String help(List<String> args, String prefixCommand)
    {
        StringBuilder message = CommandHelper.prefixCommandBuilder(prefixCommand, " ");
        message.append(controller.logoutUserHelp());
        return message.toString();
    }
}
