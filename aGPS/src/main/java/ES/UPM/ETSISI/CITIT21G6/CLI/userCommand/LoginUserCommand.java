package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

public class LoginUserCommand extends UserCommand
{
    public LoginUserCommand(UserController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.loginUser(args);
    }

    @Override
    public String description()
    {
        return "Log in user.";
    }
}
