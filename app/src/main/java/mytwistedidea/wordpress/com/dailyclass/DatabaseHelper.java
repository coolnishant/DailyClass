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

    MyHelper helper;

    public DatabaseHelper(Context applicationContext) {
        helper = new MyHelper(applicationContext);
    }

    public long insertPeriod(String table, String subject, String startTime, String endTime,
                             String teacher){

        Log.e("s","here insertP");
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



//        contentValues.put(MyHelper.PERIOD,periodNo);
        contentValues.put(MyHelper.SUBJECT,subject);
        contentValues.put(MyHelper.START_TIME,startTime);
        contentValues.put(MyHelper.END_TIME,endTime);
        contentValues.put(MyHelper.TEACHER,teacher);
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

    public long insertAssignment(String subject,String assignment,String deadline,String weekday){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyHelper.ASSIGNMENT,assignment);
        contentValues.put(MyHelper.SUBJECT,subject);
//        contentValues.put(MyHelper.ASSIGNMENT,assignment);
//        contentValues.put(MyHelper.TEACHER,teacher);
        contentValues.put(MyHelper.DEADLINE_ASSINGMENT,deadline);
        contentValues.put(MyHelper.WEEKDAY,weekday);

        long id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_ASSIGNMENT,null,contentValues);

        Log.d("id from insAss"," "+id);
        sqLiteDatabase.close();
        return id;
    }

    public ArrayList<String> getAllPeriod(String table){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<Integer> previousRoll = new ArrayList<Integer>();
        String columns[] = {
//                MyHelper.PERIOD,
                MyHelper.SUBJECT,
                MyHelper.START_TIME,
                MyHelper.END_TIME,
//                MyHelper.ASSIGNMENT,
                MyHelper.TEACHER
//                MyHelper.DEADLINE_ASSINGMENT
        };

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


//        String query = "SELECT * FROM "+ TABLE_NAME;
//
//        Cursor  cursor = sqLiteDatabase.rawQuery(query,null);

        ArrayList<String> arrayList = new ArrayList<String>();
//
//        arrayList = null;
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        else {
//            return arrayList;
//        }

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                //        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PERIOD)).toString());
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBJECT)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.START_TIME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.END_TIME)));
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TEACHER)));
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.DEADLINE_ASSINGMENT)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
//
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PERIOD)).toString());
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBJECT)));
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.START_TIME)));
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.END_TIME)));
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TEACHER)));
//        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.DEADLINE_ASSINGMENT)));

//        return arrayList;
    }

    public ArrayList<String> getAllAssignment(){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String columns[] = {
//                MyHelper.PERIOD,
                MyHelper.SUBJECT,
//                MyHelper.START_TIME,
//                MyHelper.END_TIME,
                MyHelper.ASSIGNMENT,
//                MyHelper.TEACHER,
                MyHelper.DEADLINE_ASSINGMENT,
                MyHelper.WEEKDAY
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_ASSIGNMENT;

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                //        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PERIOD)).toString());
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBJECT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.START_TIME)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.END_TIME)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TEACHER)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.DEADLINE_ASSINGMENT)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.WEEKDAY)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
    }

    public ArrayList<String> getAllAssignmentDeadline(String deadline){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<Integer> previousRoll = new ArrayList<Integer>();
        String columns[] = {
//                MyHelper.PERIOD,
                MyHelper.SUBJECT,
//                MyHelper.START_TIME,
//                MyHelper.END_TIME,
                MyHelper.ASSIGNMENT,
//                MyHelper.TEACHER
                MyHelper.DEADLINE_ASSINGMENT,
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_ASSIGNMENT;

        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE "+MyHelper.DEADLINE_ASSINGMENT+"='"+deadline+"'";

        Cursor  cursor = sqLiteDatabase.rawQuery(query,null);

        ArrayList<String> arrayList = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                //        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PERIOD)).toString());
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBJECT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.START_TIME)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.END_TIME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TEACHER)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.DEADLINE_ASSINGMENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.WEEKDAY)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
    }

    public ArrayList<String> getAllAssignmentWeekday(String weekday){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String columns[] = {
//                MyHelper.PERIOD,
                MyHelper.SUBJECT,
//                MyHelper.START_TIME,
//                MyHelper.END_TIME,
                MyHelper.ASSIGNMENT,
//                MyHelper.TEACHER
                MyHelper.DEADLINE_ASSINGMENT,
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_ASSIGNMENT;

//        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE "+MyHelper.WEEKDAY+"='"+weekday+"'";
//
//        Cursor  cursor = sqLiteDatabase.rawQuery(query,null);
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,MyHelper.WEEKDAY+" = "+weekday,null,null,null, null);

        if(cursor.moveToFirst()){
            do{
                //        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PERIOD)).toString());
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBJECT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.START_TIME)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.END_TIME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TEACHER)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.DEADLINE_ASSINGMENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.WEEKDAY)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
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

        private static final String TABLE_NAME_ASSIGNMENT="assignment_table";

        private static final int DATABASE_VERSION=1;

        private static final String PERIOD="period_no";
        private static final String SUBJECT="subject";
        private static final String START_TIME="start_time";
        private static final String END_TIME="end_time";
        private static final String ASSIGNMENT="assignment";
        private static final String TEACHER="teacher";
        private static final String DEADLINE_ASSINGMENT="deadline_assignment";
        private static final String ASSIGNMENTNO="assignment_no";
        private static final String WEEKDAY="weekday";

        private static final String CREATE_TABLE_MONDAY= "CREATE TABLE "+TABLE_NAME_MONDAY+" ("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) ,"+
                START_TIME+" VARCHAR(255)  UNIQUE,"+
                END_TIME+" VARCHAR(255) UNIQUE,"+
//                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) );";
//                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_TUESDAY= "CREATE TABLE "+TABLE_NAME_TUESDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
//                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL);";
//                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_WEDNESDAY= "CREATE TABLE "+TABLE_NAME_WEDNESDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
//                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL);";
//                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_THRUSDAY= "CREATE TABLE "+TABLE_NAME_THRUSDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
//                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL);";
//                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_FRIDAY= "CREATE TABLE "+TABLE_NAME_FRIDAY+" ("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
//                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL);";
//                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_SATURDAY= "CREATE TABLE "+TABLE_NAME_SATURDAY+" ("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
//                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL);";
//                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        private static final String CREATE_TABLE_SUNDAY= "CREATE TABLE "+TABLE_NAME_SUNDAY+"("+
                PERIOD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
//                ASSIGNMENT+" VARCHAR(255),"+
                TEACHER+" VARCHAR(255) NOT NULL);";
//                DEADLINE_ASSINGMENT+" VARCHAR(255));";

        //TODO inplementing Foreign key SUBJECT
        private static final String CREATE_TABLE_ASSIGNMENT= "CREATE TABLE "+TABLE_NAME_ASSIGNMENT+"("+
                ASSIGNMENTNO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SUBJECT+" VARCHAR(255) NOT NULL,"+
//                START_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
//                END_TIME+" VARCHAR(255) NOT NULL UNIQUE,"+
                ASSIGNMENT+" VARCHAR(255),"+
//                TEACHER+" VARCHAR(255) NOT NULL,"+
                DEADLINE_ASSINGMENT+" VARCHAR(255),"+
                WEEKDAY+" VARCHAR(255));";


        private static final String DROP_TABLE_NAME_MONDAY="DROP TABLE IF EXISTS " +TABLE_NAME_MONDAY;
        private static final String DROP_TABLE_NAME_TUESDAY="DROP TABLE IF EXISTS " +TABLE_NAME_TUESDAY;
        private static final String DROP_TABLE_NAME_WEDNESDAY="DROP TABLE IF EXISTS " +TABLE_NAME_WEDNESDAY;
        private static final String DROP_TABLE_NAME_THRUSDAY="DROP TABLE IF EXISTS " +TABLE_NAME_THRUSDAY;
        private static final String DROP_TABLE_NAME_FRIDAY="DROP TABLE IF EXISTS " +TABLE_NAME_FRIDAY;
        private static final String DROP_TABLE_NAME_SATURDAY="DROP TABLE IF EXISTS " +TABLE_NAME_SATURDAY;
        private static final String DROP_TABLE_NAME_SUNDAY="DROP TABLE IF EXISTS " +TABLE_NAME_SUNDAY;
        private static final String DROP_TABLE_NAME_ASSIGNMENT="DROP TABLE IF EXISTS " +TABLE_NAME_ASSIGNMENT;


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
                    db.execSQL(CREATE_TABLE_ASSIGNMENT);
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
                db.execSQL(DROP_TABLE_NAME_ASSIGNMENT);
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
                db.execSQL(DROP_TABLE_NAME_ASSIGNMENT);
                onCreate(db);
            } catch(SQLException e){
//                Message.message(context , "" +e);
            }
        }
    }
}
