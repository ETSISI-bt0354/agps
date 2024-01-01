package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.CLI.Command;
import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

public abstract class UserCommand implements Command
{
    protected UserController controller;

    public UserCommand(UserController controller)
    {
        this.controller = controller;
    }
}
