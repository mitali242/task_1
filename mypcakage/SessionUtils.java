package mypcakage;
public class SessionUtils {
    private static boolean isLoggedIn = false;

    public static void login() {
        isLoggedIn = true;
    }

    public static void logout() {
        isLoggedIn = false;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
}

