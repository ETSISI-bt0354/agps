package ES.UPM.ETSISI.CITIT21G6.CLI;

import java.util.List;

public interface Command
{
    String exec(List<String> args);
    String description();
}
