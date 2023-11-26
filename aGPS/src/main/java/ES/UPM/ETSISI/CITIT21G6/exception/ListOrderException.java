package ES.UPM.ETSISI.CITIT21G6.exception;

public class ListOrderException extends Exception
{
    private final String input;

    public ListOrderException(String input)
    {
        this.input = input;
    }

    public String getInput()
    {
        return input;
    }
}
