package ES.UPM.ETSISI.CITIT21G6.CLI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements Command
{
    private Map<String, Command> commands;
    private String description;

    public CommandHandler(String description)
    {
        this.commands = new HashMap<>();
        this.description = description;
    }

    public void addCommand(String commandName, Command command)
    {
        commands.put(commandName, command);
    }

    public String exec(List<String> args)
    {
        if (args.isEmpty())
            return help();

        String commandName = args.get(0);
        args.remove(0);

        Command command = commands.get(commandName);
        if (command == null)
        {
            StringBuilder message = new StringBuilder();
            message.append(commandName);
            message.append(" is not a command\n");
            message.append(help());
            return message.toString();
        }

        return command.exec(args);
    }

    public String description()
    {
        return description;
    }

    private String help()
    {
        StringBuilder message = new StringBuilder();
        commands.forEach((commandName, command) -> {
            StringBuilder aux = new StringBuilder();
            aux.append(commandName);
            aux.append(" ".repeat(5));
            if (aux.length() < 30)
                aux.append(" ".repeat(30 - aux.length()));
            aux.append(command.description());

            message.append(aux);
            message.append("\n");
        });

        message.deleteCharAt(message.length() - 1);
        return message.toString();
    }
}
