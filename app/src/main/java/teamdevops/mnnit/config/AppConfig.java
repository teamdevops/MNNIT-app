package teamdevops.mnnit.config;

/**
 * Contains the configuration for the app
 *
 * @author Deepankar
 */
public class AppConfig {

    // Remote Server user login url
    public static String REMOTE_URL_LOGIN = "http://teamdevopsmnnit.3eeweb.com/login.php";

    // Local Server user login url
    public static String LOCAL_URL_LOGIN = "http://192.168.65.1/mnnit/login.php";

    // Remote Server greviance fetch url
    public static String REMOTE_GREVIANCE_FETCH = "http://teamdevopsmnnit.3eeweb.com/fetchGrievances.php";

    // Remote Server greviance add url
    public static String REMOTE_GREVIANCE_ADD = "http://teamdevopsmnnit.3eeweb.com/populateGrievances.php";

    // Local Server greviance fetch url
    public static String LOCAL_GREVIANCE_FETCH = "http://192.168.65.1/mnnit/fetchGrievances.php";

    // Local Server greviance add url
    public static String LOCAL_GREVIANCE_ADD = "http://192.168.65.1/mnnit/populateGrievances.php";

}
