package ES.UPM.ETSISI.CITIT21G6.model;

public class PhoneNumber
{
    private static final int PHONE_NUMBER_LENGTH = 9;
    private String phoneNumber;
    public PhoneNumber(String phoneNumber) throws Exception
    {
        if(phoneNumber.length() != PHONE_NUMBER_LENGTH)
        {
            StringBuilder errorMessage = new StringBuilder();

            errorMessage.append("The phone number must have ");
            errorMessage.append(PHONE_NUMBER_LENGTH);
            errorMessage.append(" digits");

            throw new Exception(errorMessage.toString());
        }

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
