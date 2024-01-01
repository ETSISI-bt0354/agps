package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

public class loginUserCommand extends UserCommand
{
    public loginUserCommand(UserController controller)
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
