package ES.UPM.ETSISI.CITIT21G6.CLI.userCommand;

import ES.UPM.ETSISI.CITIT21G6.CLI.CommandHelper;
import ES.UPM.ETSISI.CITIT21G6.controller.UserController;

import java.util.List;

public class WhoAmICommand extends UserCommand{
    public WhoAmICommand(UserController controller){super(controller);}

    @Override
    public String exec(List<String> args) {
        return controller.whoIsUser(args.toArray(new String[0]));
    }

    @Override
    public String description() {
        return "Indicates what user is logged in";
    }

    @Override
    public String help(List<String> args, String prefixCommand) {
        StringBuilder message = CommandHelper.prefixCommandBuilder(prefixCommand, " ");
        message.append(controller.whoIsUserHelp());
        return message.toString();
    }
}
