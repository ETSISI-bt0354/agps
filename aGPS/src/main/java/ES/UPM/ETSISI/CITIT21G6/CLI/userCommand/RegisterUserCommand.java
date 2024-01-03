package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.CLI.CommandHelper;
import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

import java.util.List;

public class RegisterUserCommand extends UserCommand
{
    public RegisterUserCommand(UserController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.registerUser(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Register a user.";
    }

    @Override
    public String help(List<String> args, String prefixCommand)
    {
        StringBuilder message = CommandHelper.prefixCommandBuilder(prefixCommand, " ");
        message.append(controller.registerUserHelp());
        return message.toString();
    }
}
