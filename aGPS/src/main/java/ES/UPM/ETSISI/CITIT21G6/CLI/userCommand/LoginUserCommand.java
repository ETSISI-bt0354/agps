package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

import java.util.List;

public class LoginUserCommand extends UserCommand
{
    public LoginUserCommand(UserController controller)
    {
        super(controller);
    }

    @Override
    public String exec(List<String> args)
    {
        return controller.loginUser(args.toArray(new String[0]));
    }

    @Override
    public String description()
    {
        return "Log in user.";
    }

    @Override
    public String help(List<String> args)
    {
        return controller.loginUserHelp(false);
    }
}
