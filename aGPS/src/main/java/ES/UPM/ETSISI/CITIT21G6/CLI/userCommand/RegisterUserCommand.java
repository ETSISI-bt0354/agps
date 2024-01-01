package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

public class RegisterUserCommand extends UserCommand
{
    public RegisterUserCommand(UserController controller)
    {
        super(controller);
    }

    @Override
    public String exec(String[] args)
    {
        return controller.registerUser(args);
    }

    @Override
    public String description()
    {
        return "Register a user.";
    }
}
