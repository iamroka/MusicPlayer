package arcanine.library;

/**
 * Created by Rohan on 28/04/2016.
 */
public class ExceptionTracker {

    public static void track(Exception exception) {
        //Crashlytics.logException(exception);
        exception.printStackTrace();
    }

    public static void track(String message) {
        //Crashlytics.log(message);
        //Log.e();
    }
}