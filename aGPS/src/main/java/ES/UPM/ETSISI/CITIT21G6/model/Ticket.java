package ES.UPM.ETSISI.CITIT21G6.model;

abstract class Ticket
{
    private int score;

    public void setScore(int score)
    {
        this.score = score;
    }

    public abstract void delete();

}
