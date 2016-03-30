package kent.kentapp;

/**
 * Created by James on 30/03/2016.
 */
public class AppVariables {

    private static String currentUsername;

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }
}
