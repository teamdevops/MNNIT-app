package teamdevops.mnnit.helper;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Deepankar on 05-11-2015.
 */
public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    public static SessionManager mInstance;
    private static Context mContext;

    // Shared Preferences editor
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "MNNIT";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private SessionManager(Context context) {
        mContext = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static synchronized SessionManager getInstance(Context context) {
        if(mInstance == null)
            mInstance = new SessionManager(context);
        return  mInstance;
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
