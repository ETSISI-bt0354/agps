package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.ListOrderException;
public enum ListOrder
{
    DATE, SCORE;

    public static ListOrder parse(String s) throws ListOrderException
    {
        return switch (s.toUpperCase())
        {
            case "DATE" -> ListOrder.DATE;
            case "SCORE" -> SCORE;
            default -> throw new ListOrderException(s);
        };
    }
}
