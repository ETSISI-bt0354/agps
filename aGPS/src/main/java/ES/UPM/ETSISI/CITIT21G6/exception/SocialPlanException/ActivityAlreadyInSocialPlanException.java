package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

import ES.UPM.ETSISI.CITIT21G6.model.Activity;

public class ActivityAlreadyInSocialPlanException extends Exception
{
    private Activity activity;

    public ActivityAlreadyInSocialPlanException(Activity activity)
    {
        this.activity = activity;
    }

    public Activity getActivity()
    {
        return activity;
    }
}
