package darryl.esteva.com.nutritionistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Nutritionist.db";
    public static final String TABLE_NAME = "USERTABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "BMI";
    public static final String COL_5 = "DAY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //  SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USERNAME TEXT, BMI DOUBLE, DAY INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name, String username, String bmi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, username);
        contentValues.put(COL_4, bmi);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
        {
            return false;
        }

        else
        {
            return true;
        }
    }


    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return res;

    }

    public Cursor getDataOnID()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int GetID = 1;
        Cursor res = db.rawQuery("Select * from USERTABLE where ID=" + GetID + "", null);
        return res;

    }

    /*public boolean updateData(String id, String name, String username, String bmi){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, username);
        contentValues.put(COL_4, bmi);
//        db.update(TABLE_NAME, contentValues, "id = ?", new String[] { id } );
//        return true;

        int result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });

        if (result == 0)
        {
            return false;
        }
        else
        {
            return true;
        }


    }*/

    public boolean updateData(String id, String bmi){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_4, bmi);


        int result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });

        if (result == 0)
        {
            return false;
        }
        else
        {
            return true;
        }


    }

    public Integer deleteData (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[] { id });

    }



}
