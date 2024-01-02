package ES.UPM.ETSISI.CITIT21G6.CLI;

import java.util.List;

public class ExitCommand implements Command
{

    @Override
    public String exec(List<String> args)
    {
        return "Exiting agps";
    }

    @Override
    public String description()
    {
        return "Exit agps";
    }
}
