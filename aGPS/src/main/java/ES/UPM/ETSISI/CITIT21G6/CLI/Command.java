package ES.UPM.ETSISI.CITIT21G6.CLI;

import java.util.List;

public interface Command
{
    String exec(List<String> args);
    String description();
    String help(List<String> args, String prefixCommand);
}
