package ES.UPM.ETSISI.CITIT21G6.CLI;

public interface Command
{
    String exec(String[] args);
    String description();
}
