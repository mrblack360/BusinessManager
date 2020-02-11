package com.bms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bms.ui.purchases.Particular;

public class DBManager extends SQLiteOpenHelper {
    SQL sql = new SQL();
    public DBManager (Context context){
        super(context, "BMS.db", null, 1);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sql.user);
        db.execSQL(sql.manager);
        db.execSQL(sql.manager_phone_number);
        db.execSQL(sql.worker);
        db.execSQL(sql.worker_phone_number);
        db.execSQL(sql.business);
        db.execSQL(sql.business_owner);
        db.execSQL(sql.particular);
        db.execSQL(sql.purchase);
    }
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public void addParticular(Particular particular){
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put ("particular_id", particular.particular_id);
        contentValues.put ("description", particular.description);
        contentValues.put ("unit_price", particular.unit_price);
        db.insert ("particular", null, contentValues);
        db.close ();
    }

    public Cursor getParticularCursor(){
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor particularCursor = db.rawQuery ("SELECT particular_id as _id, description, unit_price FROM " +
                        "particular",null);
        return particularCursor;
    }

    public void updateParticular(Particular particular){
        SQLiteDatabase db = this.getWritableDatabase ();
        db.execSQL ("UPDATE particular SET particular_id='"+particular.particular_id+
                "', description='"+particular.description+
                "', unit_price="+particular.unit_price+
                " WHERE particular_id='"+particular.particular_id+"'");
        db.close ();
    }

    public void deleteParticular(Particular particular){
        SQLiteDatabase db = this.getWritableDatabase ();
        db.execSQL ("DELETE FROM particular WHERE particular_id='"+particular.particular_id+"'");
        db.close ();
    }
    public String getParticularId(int recordId){
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor= db.rawQuery ("SELECT * FROM particular LIMIT 1 OFFSET "+recordId, null);
        cursor.moveToFirst ();
        return cursor.getString (1);
    }

    public Cursor readParticularDetails(int recordId){
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor= db.rawQuery ("SELECT * FROM particular LIMIT 1 OFFSET "+recordId, null);
        return  cursor;
    }
}
