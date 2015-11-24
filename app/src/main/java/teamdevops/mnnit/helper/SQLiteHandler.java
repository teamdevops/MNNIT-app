package teamdevops.mnnit.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import teamdevops.mnnit.entities.Grievance;

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

    // Table names
    private static final String TABLE_USER = "user";
    private static final String TABLE_GREIVANCE = "grievance";

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

    // Grievance Table column names
    public static final String KEY_COMPID = "compid";
    public static final String KEY_SUBJECT = "subject";
    //public static final String KEY_REGNO = "regno";
    public static final String KEY_TYPE = "type";
    public static final String KEY_GRIEVANCE = "grievance";
    public static final String KEY_STATUS = "status";
    public static final String KEY_CREATEDAT = "created_at";


    // Queries for Creation of table
    public static final String CREATE_LOGIN_TABLE =
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

    public static final String CREATE_GRIEVANCE_TABLE =
            "CREATE TABLE " + TABLE_GREIVANCE + "("
                    + KEY_COMPID + " INTEGER PRIMARY KEY NOT NULL,"
                    + KEY_SUBJECT + " TEXT ,"
                    + KEY_REGNO + " INTEGER NOT NULL,"
                    + KEY_TYPE + " TEXT NOT NULL,"
                    + KEY_GRIEVANCE + " TEXT ,"
                    + KEY_STATUS + " INTEGER NOT NULL,"
                    + KEY_CREATEDAT + " TEXT NOT NULL "
                    + ")";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_GRIEVANCE_TABLE);
        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
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
     * Getting Grievance data from database
     */
    public ArrayList<Grievance> getGrievanceDetails() {
        ArrayList<Grievance> grievancesData = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_GREIVANCE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Grievance entry = new Grievance();
            entry.setSubject(cursor.getString(1));
            entry.setType(cursor.getString(3));
            entry.setGrievance(cursor.getString(4));
            entry.setStatus(cursor.getString(5));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                entry.setDate(df.parse(cursor.getString(6)));
            } catch (ParseException e) {
                Log.d(TAG, "Date Parse Error " + e.getMessage());
            }
            grievancesData.add(entry);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        // return user
        return grievancesData;
    }

    /**
     * Delete all the grievances table from DB
     */
    public void deleteGrievances() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_GREIVANCE, null, null);
        db.close();
        Log.d(TAG, "Deleted all grievances info from sqlite");
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