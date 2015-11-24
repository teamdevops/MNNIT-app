package teamdevops.mnnit.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import teamdevops.mnnit.activity.TimeTableActivity;
import teamdevops.mnnit.entities.TimeTableData;

/**
 * This class includes all the SQLite Database connection and functions
 *
 * @author Deepankar
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mnnit";
    // Login table name
    private static final String TABLE_USER = "user";
    // Login Table Columns names
    public static final String KEY_REGNO = "regno";
    public static final String KEY_NAME = "name";
    public static final String KEY_FATHER = "fathername";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phoneno";
    public static final String KEY_DOB = "dob";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_BLOODGROUP = "bloodgroup";
    public static final String KEY_HOSTEL = "hostel";
    public static final String KEY_ROOMNO = "roomno";
    public static final String KEY_IMAGE = "image";

    // Table Names
    private static String MON_TABLE = TimeTableActivity.DAY[0];
    private static String TUES_TABLE = TimeTableActivity.DAY[1];
    private static String WED_TABLE = TimeTableActivity.DAY[2];
    private static String THURS_TABLE = TimeTableActivity.DAY[3];
    private static String FRI_TABLE = TimeTableActivity.DAY[4];
    private static String SAT_TABLE = TimeTableActivity.DAY[5];
    private static String SUN_TABLE = TimeTableActivity.DAY[6];

    // ALL DAY Column Names
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_CODE = "code";
    private static final String KEY_VENUE = "venue";
    private static final String KEY_INSTRUCTOR = "instructor";
    private static final String KEY_FROM = "fromTime";
    private static final String KEY_TO = "toTime";

    private static final String CREATE_MON_TABLE =
            "CREATE TABLE " + MON_TABLE + "("

                    + KEY_SUBJECT + " TEXT, "
                    + KEY_CODE + " TEXT, "
                    + KEY_VENUE + " TEXT, "
                    + KEY_INSTRUCTOR + " TEXT, "
                    + KEY_FROM + " INTEGER, "
                    + KEY_TO + " INTEGER "
                    + ")";
    private static final String CREATE_TUES_TABLE =
            "CREATE TABLE " + TUES_TABLE + "("
                    + KEY_SUBJECT + " TEXT, "
                    + KEY_CODE + " TEXT, "
                    + KEY_VENUE + " TEXT, "
                    + KEY_INSTRUCTOR + " TEXT, "
                    + KEY_FROM + " INTEGER, "
                    + KEY_TO + " INTEGER "
                    + ")";
    private static final String CREATE_WED_TABLE =
            "CREATE TABLE " + WED_TABLE + "("
                    + KEY_SUBJECT + " TEXT, "
                    + KEY_CODE + " TEXT, "
                    + KEY_VENUE + " TEXT, "
                    + KEY_INSTRUCTOR + " TEXT, "
                    + KEY_FROM + " INTEGER, "
                    + KEY_TO + " INTEGER "
                    + ")";
    private static final String CREATE_THURS_TABLE =
            "CREATE TABLE " + THURS_TABLE + "("
                    + KEY_SUBJECT + " TEXT, "
                    + KEY_CODE + " TEXT, "
                    + KEY_VENUE + " TEXT, "
                    + KEY_INSTRUCTOR + " TEXT, "
                    + KEY_FROM + " INTEGER, "
                    + KEY_TO + " INTEGER "
                    + ")";
    private static final String CREATE_FRI_TABLE =
            "CREATE TABLE " + FRI_TABLE + "("
                    + KEY_SUBJECT + " TEXT, "
                    + KEY_CODE + " TEXT, "
                    + KEY_VENUE + " TEXT, "
                    + KEY_INSTRUCTOR + " TEXT, "
                    + KEY_FROM + " INTEGER, "
                    + KEY_TO + " INTEGER "
                    + ")";
    private static final String CREATE_SAT_TABLE =
            "CREATE TABLE " + SAT_TABLE + "("
                    + KEY_SUBJECT + " TEXT, "
                    + KEY_CODE + " TEXT, "
                    + KEY_VENUE + " TEXT, "
                    + KEY_INSTRUCTOR + " TEXT, "
                    + KEY_FROM + " INTEGER, "
                    + KEY_TO + " INTEGER "
                    + ")";

    private static final String CREATE_SUN_TABLE =
            "CREATE TABLE " + SUN_TABLE + "("
                    + KEY_SUBJECT + " TEXT, "
                    + KEY_CODE + " TEXT, "
                    + KEY_VENUE + " TEXT, "
                    + KEY_INSTRUCTOR + " TEXT, "
                    + KEY_FROM + " INTEGER, "
                    + KEY_TO + " INTEGER "
                    + ")";

    private Context context;


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE =
                "CREATE TABLE " + TABLE_USER + "("
                        + KEY_REGNO + " INTEGER PRIMARY KEY NOT NULL,"
                        + KEY_NAME + " TEXT NOT NULL,"
                        + KEY_FATHER + " TEXT NOT NULL,"
                        + KEY_GENDER + " TEXT NOT NULL,"
                        + KEY_EMAIL + " TEXT NOT NULL,"
                        + KEY_PHONE + " INTEGER NOT NULL,"
                        + KEY_DOB + " TEXT NOT NULL,"
                        + KEY_ADDRESS + " TEXT NOT NULL,"
                        + KEY_BLOODGROUP + " TEXT,"
                        + KEY_HOSTEL + " TEXT,"
                        + KEY_ROOMNO + " INTEGER,"
                        + KEY_IMAGE + " BLOB "
                        + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_MON_TABLE);
        db.execSQL(CREATE_TUES_TABLE);
        db.execSQL(CREATE_WED_TABLE);
        db.execSQL(CREATE_THURS_TABLE);
        db.execSQL(CREATE_FRI_TABLE);
        db.execSQL(CREATE_SAT_TABLE);
        db.execSQL(CREATE_SUN_TABLE);
        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MON_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TUES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + WED_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + THURS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FRI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SAT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SUN_TABLE);

        // Create tables again
        onCreate(db);
    }


    /**
     * Storing timetable entry
     */
    public void addTimeTableEntry(String database_table , TimeTableData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT, data.getSubject());
        values.put(KEY_CODE, data.getCode());
        values.put(KEY_VENUE, data.getVenue());
        values.put(KEY_INSTRUCTOR, data.getInstructor());
        values.put(KEY_FROM, data.getFrom_time());
        values.put(KEY_TO, data.getTo_time());

        // Inserting Row
        long id = db.insert(database_table, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "New user inserted into sqlite: " + id + " in table " + database_table);
        Toast.makeText(context, "ENTRY ADDED!", Toast.LENGTH_SHORT).show();
    }
    public void deleteTimeTableEntry(String database_table,TimeTableData data){
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE_QUERY = " DELETE from " + database_table + " WHERE "
                + KEY_CODE + " = '" + data.getCode()
                + "' and " +KEY_SUBJECT+ " = '" + data.getSubject()
                + "' and " +KEY_VENUE+ " = '" + data.getVenue()
                + "' and " +KEY_INSTRUCTOR+ " = '" + data.getInstructor() + "'";
        db.execSQL(DELETE_QUERY);

    }


    /**
     * Get timetable data from tables
     */
    public ArrayList<TimeTableData> getTimeTableData(String database_table) {
        ArrayList<TimeTableData> timeTableData = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + database_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount() ; i++) {
            TimeTableData entry = new TimeTableData();
            entry.setSubject(cursor.getString(0));
            entry.setCode(cursor.getString(1));
            entry.setVenue(cursor.getString(2));
            entry.setInstructor(cursor.getString(3));
            entry.setFrom_time(cursor.getInt(4));
            entry.setTo_time(cursor.getInt(5));
            timeTableData.add(entry);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        // return user
        return timeTableData;

    }



    /**
     * Storing user details in database
     */
    public void addUser(int regno, String name, String fathername, String gender, String email, long phoneno, String dob, String address, String bloodgroup, String hostel, int roomno, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_REGNO, regno);
        values.put(KEY_NAME, name);
        values.put(KEY_FATHER, fathername);
        values.put(KEY_GENDER, gender);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phoneno);
        values.put(KEY_DOB, dob);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_BLOODGROUP, bloodgroup);
        values.put(KEY_HOSTEL, hostel);
        values.put(KEY_ROOMNO, roomno);
        values.put(KEY_IMAGE, image);

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Add profile image into DB
     */
    public void addImage(int regno, byte[] image) {

    }

    /**
     * Getting user data from database
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(KEY_REGNO, cursor.getString(0));
            user.put(KEY_NAME, cursor.getString(1));
            user.put(KEY_FATHER, cursor.getString(2));
            user.put(KEY_GENDER, cursor.getString(3));
            user.put(KEY_EMAIL, cursor.getString(4));
            user.put(KEY_PHONE, cursor.getString(5));
            user.put(KEY_DOB, cursor.getString(6));
            user.put(KEY_ADDRESS, cursor.getString(7));
            user.put(KEY_BLOODGROUP, cursor.getString(8));
            user.put(KEY_HOSTEL, cursor.getString(9));
            user.put(KEY_ROOMNO, cursor.getString(10));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
        return user;
    }

    /**
     * Get image from the db
     */
    public byte[] getImage() {
        String selectQuery = "SELECT " + KEY_IMAGE + " FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return cursor.getBlob(0);
        } else
            return null;
    }

    /**
     * Re-create database Delete all tables \
     */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user info from sqlite");
    }
}