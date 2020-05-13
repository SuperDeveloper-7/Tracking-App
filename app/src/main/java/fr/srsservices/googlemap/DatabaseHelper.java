package fr.srsservices.googlemap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RunningInfo.db";
    public static final String TABLE_NAME = "runninginfo_table";
    public static final String COL_1 = "DIST";
    public static final String COL_2 = "TIME";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String strSQL = " create table " + TABLE_NAME + " (" + COL_1 + " FLOAT, " + COL_2 + " FLOAT)";
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String strSQL = " DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(strSQL);
        onCreate(db);
    }

    public boolean insertData(float dist, float time) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, dist);
        contentValues.put(COL_2, time);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
}
