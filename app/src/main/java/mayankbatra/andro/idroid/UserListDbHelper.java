package mayankbatra.andro.idroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserListDbHelper extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "iDI.db";
    public static final int VERSION = 3;
    public static final String TABLE = "iDi_New_SMS_Data";
    public static final String ID = "ID";
    public static final String Lead_Generated_via="Lead_Generated_via";
    public static final String Leads_Name="Leads_Name";
    public static final String Mobile_No="Mobile_No";
    public static final String Need_Name="Need_name";
    public static final String Email_id="Email_id";
    public static final String Course_Searched_For="Course_Searched_For";
    public static final String Location_Searched_for="Location_Searched_for";
    public static final String Date_Time_of_search="Date_Time_of_search";


    public UserListDbHelper(Context context, String name,
                            CursorFactory factory, int version) {
        super(context, TABLE, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            createTable();

        } catch (Exception e) {

        }
    }

    public void createTable() {


        SQLiteDatabase sdb = this.getWritableDatabase();

        String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE + " (" +ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Lead_Generated_via
                + " VARCHAR NOT NULL, " +Leads_Name+ " VARCHAR NOT NULL, "+Mobile_No+ " VARCHAR NOT NULL, "+Email_id+ " VARCHAR NOT NULL, "+Course_Searched_For+ " VARCHAR NOT NULL, "+Location_Searched_for +" VARCHAR, " + Date_Time_of_search+" VARCHAR NOT NULL );" ;

        System.out.println(sql);

        sdb.execSQL(sql);

        sdb.close();

    }

    public void insertData(String lead_generated_via,String leads_name,String mobile_no,String course_searched_for,String location_searched_for ,String email_id,String date_time_of_search)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Lead_Generated_via, lead_generated_via);
        values.put(Leads_Name,leads_name );
        values.put(Mobile_No,mobile_no );
        values.put(Email_id, email_id);
        values.put(Location_Searched_for,location_searched_for);
        values.put(Date_Time_of_search, date_time_of_search);
        values.put(Course_Searched_For, course_searched_for);



        db.insert(TABLE, null, values);
        System.out.println("Submitted...");
        Log.i("Submitted ::", " insertdata executed");
    }
    public Cursor Checkdata()
    {
        //To Return Data a Cursor and check if table is empty or not , and if not empty extract data and post to Web
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = query(db, "SELECT * From "+TABLE);
        return data;
    }


    public Cursor query(SQLiteDatabase db, String query)
    {

        Cursor cursor = db.rawQuery(query, null);
        System.out.println("Executing Query: " + query);
        return cursor;

    }


    public void delete()
    {
        SQLiteDatabase sdb = this.getWritableDatabase();

        String sql = "DELETE FROM "+TABLE+";" ;

        System.out.println(sql);

        sdb.execSQL(sql);

        sdb.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {


    }

}
