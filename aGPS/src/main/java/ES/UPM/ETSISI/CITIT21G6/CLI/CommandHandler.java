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

    @Override
    public String exec(List<String> args)
    {
        if (args.isEmpty())
            return handlerHelp();

        String commandName = args.get(0);
        args.remove(0);

        Command command = commands.get(commandName);
        if (command == null)
        {
            StringBuilder message = new StringBuilder();
            if(!commandName.isEmpty()){
                message.append(commandName);
                message.append(" is not a command\n");
            }
            message.append(handlerHelp());
            return message.toString();
        }

        return command.exec(args);
    }

    @Override
    public String description()
    {
        return description;
    }

    @Override
    public String help(List<String> args, String prefixCommand)
    {
        if (args.isEmpty())
            return handlerHelp();

        String commandName = args.get(0);
        args.remove(0);

        Command command = commands.get(commandName);
        if (command == null)
        {
            StringBuilder message = new StringBuilder();
            if(!commandName.isEmpty()){
                message.append(CommandHelper.prefixCommandBuilder(prefixCommand, " "));
                message.append(commandName);
                message.append(" is not a command\n");
            }
            message.append(handlerHelp());
            return message.toString();
        }

        StringBuilder message = CommandHelper.prefixCommandBuilder(prefixCommand, " ");
        message.append("\033[0;32m");
        message.append(commandName);
        message.append("\033[0m");

        StringBuilder helpMessage = new StringBuilder();
        if (args.isEmpty())
        {
            helpMessage.append(command.description());
            helpMessage.append("\n");
        }

        helpMessage.append(command.help(args, message.toString()));
        return helpMessage.toString();
    }

    private String handlerHelp()
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
