package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

public class logoutUserCommand extends UserCommand
{
    public logoutUserCommand(UserController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.logoutUser(args);
    }

    @Override
    public String description()
    {
        return "log out user.";
    }
}
