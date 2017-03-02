package mytwistedidea.wordpress.com.dailyclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Nishant on 02-03-2017.
 */

public class DatabaseHelper {

    static MyHelper helper;

    public long insertPeriod(String table, int periodNo, String subject, String startTime, String endTime,
                             String assignment, String teacher, String deadline){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyHelper.PERIOD,periodNo);
        contentValues.put(MyHelper.SUBJECT,subject);
        contentValues.put(MyHelper.START_TIME,startTime);
        contentValues.put(MyHelper.END_TIME,endTime);
        contentValues.put(MyHelper.ASSIGNMENT,assignment);
        contentValues.put(MyHelper.TEACHER,teacher);
        contentValues.put(MyHelper.DEADLINE_ASSINGMENT,deadline);
        long id;
        if (table.equals("monday"))
            id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_MONDAY,null,contentValues);
        else if (table.equals("tuesday"))
            id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_TUESDAY,null,contentValues);
        else if (table.equals("wednesday"))
            id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_WEDNESDAY,null,contentValues);
        else if (table.equals("thrusday"))
            id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_THRUSDAY,null,contentValues);
        else if (table.equals("friday"))
            id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_FRIDAY,null,contentValues);
        else if (table.equals("saturday"))
            id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_SATURDAY,null,contentValues);
        else
            id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_SUNDAY,null,contentValues);

        Log.d("id from insAss"," "+id);
        sqLiteDatabase.close();
        return id;
    }


    public static ArrayList<String> getAllPeriod(String table){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<Integer> previousRoll = new ArrayList<Integer>();
        String columns[] = {
                MyHelper.PERIOD,
                MyHelper.SUBJECT,
                MyHelper.START_TIME,
                MyHelper.END_TIME,
                MyHelper.ASSIGNMENT,
                MyHelper.TEACHER,
                MyHelper.DEADLINE_ASSINGMENT };

        String TABLE_NAME;

        if (table.equals("monday"))
            TABLE_NAME =MyHelper.TABLE_NAME_MONDAY;
        else if (table.equals("tuesday"))
            TABLE_NAME = MyHelper.TABLE_NAME_TUESDAY;
        else if (table.equals("wednesday"))
            TABLE_NAME = MyHelper.TABLE_NAME_WEDNESDAY;
        else if (table.equals("thrusday"))
            TABLE_NAME = MyHelper.TABLE_NAME_THRUSDAY;
        else if (table.equals("friday"))
            TABLE_NAME = MyHelper.TABLE_NAME_FRIDAY;
        else if (table.equals("saturday"))
            TABLE_NAME = MyHelper.TABLE_NAME_SATURDAY;
        else
            TABLE_NAME = MyHelper.TABLE_NAME_SUNDAY;


        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor  cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PERIOD)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBJECT)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.START_TIME)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.END_TIME)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TEACHER)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.DEADLINE_ASSINGMENT)));

        return arrayList;
    }


    class MyHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME="Dailyclass.db";

        /*table definitions for storing user information*/

        private static final String TABLE_NAME_MONDAY="monday_table";
        private static final String TABLE_NAME_TUESDAY="tuesday_table";
        private static final String TABLE_NAME_WEDNESDAY="wednesday_table";
        private static final String TABLE_NAME_THRUSDAY="thrusday_table";
        private static final String TABLE_NAME_FRIDAY="friday_table";
        private static final String TABLE_NAME_SATURDAY="saturday_table";
        private static final String TABLE_NAME_SUNDAY="sunday_table";

        private static final int DATABASE_VERSION=1;

        private static final String PERIOD="period_no";
        private static final String SUBJECT="subject";
        private static final String START_TIME="start_time";
        private static final String END_TIME="end_time";
        private static final String ASSIGNMENT="assignment";
        private static final String TEACHER="teacher";
        private static final String DEADLINE_ASSINGMENT="deadline_assignment";

        private static final String CREATE_TABLE_MONDAY= "CREATE TABLE "+TABLE_NAME_MONDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_TUESDAY= "CREATE TABLE "+TABLE_NAME_TUESDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_WEDNESDAY= "CREATE TABLE "+TABLE_NAME_WEDNESDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_THRUSDAY= "CREATE TABLE "+TABLE_NAME_THRUSDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_FRIDAY= "CREATE TABLE "+TABLE_NAME_FRIDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_SATURDAY= "CREATE TABLE "+TABLE_NAME_SATURDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_SUNDAY= "CREATE TABLE "+TABLE_NAME_SUNDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255));";


        private static final String DROP_TABLE_NAME_MONDAY="DROP TABLE IF EXISTS " +TABLE_NAME_MONDAY;
        private static final String DROP_TABLE_NAME_TUESDAY="DROP TABLE IF EXISTS " +TABLE_NAME_TUESDAY;
        private static final String DROP_TABLE_NAME_WEDNESDAY="DROP TABLE IF EXISTS " +TABLE_NAME_WEDNESDAY;
        private static final String DROP_TABLE_NAME_THRUSDAY="DROP TABLE IF EXISTS " +TABLE_NAME_THRUSDAY;
        private static final String DROP_TABLE_NAME_FRIDAY="DROP TABLE IF EXISTS " +TABLE_NAME_FRIDAY;
        private static final String DROP_TABLE_NAME_SATURDAY="DROP TABLE IF EXISTS " +TABLE_NAME_SATURDAY;
        private static final String DROP_TABLE_NAME_SUNDAY="DROP TABLE IF EXISTS " +TABLE_NAME_SUNDAY;


        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        private Context context;

        public MyHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if(db!=null){
                try {
                    db.execSQL(CREATE_TABLE_MONDAY);
                    db.execSQL(CREATE_TABLE_TUESDAY);
                    db.execSQL(CREATE_TABLE_WEDNESDAY);
                    db.execSQL(CREATE_TABLE_THRUSDAY);
                    db.execSQL(CREATE_TABLE_FRIDAY);
                    db.execSQL(CREATE_TABLE_SATURDAY);
                    db.execSQL(CREATE_TABLE_SUNDAY);
                } catch(SQLException e){
//                    Message.message(context ,"" +e);
                    e.printStackTrace();
                }}
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
//                Message.message(context,"onDowngrade() called");
                db.execSQL(DROP_TABLE_NAME_MONDAY);
                db.execSQL(DROP_TABLE_NAME_TUESDAY);
                db.execSQL(DROP_TABLE_NAME_WEDNESDAY);
                db.execSQL(DROP_TABLE_NAME_THRUSDAY);
                db.execSQL(DROP_TABLE_NAME_FRIDAY);
                db.execSQL(DROP_TABLE_NAME_SATURDAY);
                db.execSQL(DROP_TABLE_NAME_SUNDAY);
                onCreate(db);
            } catch(SQLException e){
//                Message.message(context , "" +e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
//                Message.message(context,"onDowngrade() called");
                db.execSQL(DROP_TABLE_NAME_MONDAY);
                db.execSQL(DROP_TABLE_NAME_TUESDAY);
                db.execSQL(DROP_TABLE_NAME_WEDNESDAY);
                db.execSQL(DROP_TABLE_NAME_THRUSDAY);
                db.execSQL(DROP_TABLE_NAME_FRIDAY);
                db.execSQL(DROP_TABLE_NAME_SATURDAY);
                db.execSQL(DROP_TABLE_NAME_SUNDAY);
                onCreate(db);
            } catch(SQLException e){
//                Message.message(context , "" +e);
            }
        }
    }
}