package com.t2t.trip2thai;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseHelper object;

    private DatabaseHelper(Context context)
    {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseHelper getObject(Context context) {
        if (object == null) {
            object = new DatabaseHelper(context);
        }
        return object;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }//db doesnt need to be upgraded after this.

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public String databaseTostring(int key, int a) {
        String data = "";
        String s = "";
        Cursor c = database.rawQuery("SELECT * FROM thailand", null);
        if (a == 2) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(2);
            return data;
        } else if (a == 1) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(1);
            return data;

        } else if (a == 3) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(3);
            return data;
        } else if (a == 4) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(4);
            return data;
        } else if (a == 5) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(5);
            return data;
        } else
            return s;
    }

    public double dataTodouble(int key, int a)
    {
        double d=0;
        Cursor c = database.rawQuery("SELECT * FROM thailand", null);
        if (a == 3) {   //lar
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            d = c.getDouble(3);
            return d;
        }
        if (a == 4) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            d = c.getDouble(4);
            return d;
        }
        else
            return 0;
    }
}