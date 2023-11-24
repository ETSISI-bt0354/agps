package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.regex.Pattern;

public class PhoneNumber
{
    private static final int PHONE_NUMBER_LENGTH = 9;
    private String phoneNumber;
    public PhoneNumber(String phoneNumber) throws Exception
    {
        if (!Pattern.matches(String.format("^[0-9]{%d}$", PHONE_NUMBER_LENGTH), phoneNumber))
            throw new Exception("Invalid phone number");

        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
