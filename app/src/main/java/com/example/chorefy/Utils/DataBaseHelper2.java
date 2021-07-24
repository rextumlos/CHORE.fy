package com.example.chorefy.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.chorefy.Model.HomeTaskModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper2 extends SQLiteOpenHelper {

    public SQLiteDatabase db2;

    private static final String DATABASE_NAME = "ITEM_DATABASE2";
    private static final String TABLE_NAME = "ITEM_TABLE2";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TASK";
    private static final String COL_3 = "STATUS";
    private static final String COL_4 = "DATE";
    private static final String COL_5 = "TIME";
    private static final String COL_6 = "MEMBER";


    public DataBaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , TASK TEXT, STATUS INTEGER, DATE TEXT, TIME TEXT, MEMBER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db2);
    }

    public void insertTask2(HomeTaskModel model){
        db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, model.getTask());
        values.put(COL_3, 0);
        values.put(COL_4, model.getDate());
        values.put(COL_5, model.getTime());
        values.put(COL_6, model.getMember());
        db2.insert(TABLE_NAME, null, values);
    }

    public void updateTask2(int id, String task){
        db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, task);
        db2.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void updateStatus2(int id, int status){
        db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3, status);
        db2.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void updateDate(int id, String date){
        db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_4, date);
        db2.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void updateTime (int id, String time){
        db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_5, time);
        db2.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void updateMember(int id, String member){
        db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_6, member);
        db2.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask2(int id){
        db2 = this.getWritableDatabase();
        db2.delete(TABLE_NAME,"ID=?", new String[]{String.valueOf(id)});
    }

    public List<HomeTaskModel> getAllTasks2(){
        db2 = this.getWritableDatabase();
        Cursor cursor = null;
        List<HomeTaskModel> modelList = new ArrayList<>();

        db2.beginTransaction();
        try {
            cursor = db2.query(TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do{
                        HomeTaskModel task = new HomeTaskModel();
                        task.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
                        task.setTask(cursor.getString(cursor.getColumnIndex(COL_2)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndex(COL_3)));
                        task.setDate(cursor.getString(cursor.getColumnIndex(COL_4)));
                        task.setTime(cursor.getString(cursor.getColumnIndex(COL_5)));
                        task.setMember(cursor.getString(cursor.getColumnIndex(COL_6)));
                        modelList.add(task);

                    }while (cursor.moveToNext());
                }
            }
        } finally {
            db2.endTransaction();
            cursor.close();
        }
        return modelList;
    }
}
