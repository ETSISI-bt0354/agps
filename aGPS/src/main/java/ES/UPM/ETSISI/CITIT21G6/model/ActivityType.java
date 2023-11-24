package ES.UPM.ETSISI.CITIT21G6.model;

public enum ActivityType
{
    GENERIC,
    CINEMA,
    THEATRE;

    public static ActivityType parse(String s)
    {
        return switch (s.toUpperCase())
        {
            case "CINEMA" -> ActivityType.CINEMA;
            case "THEATRE" -> ActivityType.THEATRE;
            default -> ActivityType.GENERIC;
        };
    }
}
