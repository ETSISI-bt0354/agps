package ES.UPM.ETSISI.CITIT21G6.CLI;

public class CommandHelper
{
    public static StringBuilder prefixCommandBuilder(String prefixCommand, String separator)
    {
        StringBuilder message = new StringBuilder();
        if (!prefixCommand.isBlank())
        {
            message.append(prefixCommand);
            message.append(separator);
        }

        return message;
    }
}
