package com.example.browser.SQLiteDatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.browser.objects.Page;

import java.util.ArrayList;

public class SQLiteDbManager {

    private Context context;  // контекст для SQLiteDbHelper
    private static SQLiteDbHelper myDbHelper; // объект для взаимодейсевия с бд
    private static SQLiteDatabase db; // база данных

    // конструктор
    public SQLiteDbManager(Context context){
        this.context = context;
        myDbHelper = new SQLiteDbHelper(context);
    }

    // открытие и закрытие бд
    private static void openDb(){
        db = myDbHelper.getWritableDatabase();
    }
    private static void closeDb(){
        myDbHelper.close();
    }
    // запись данных в бд
    public void insertToDb(Page page){
        ContentValues cv = new ContentValues();
        cv.put(SQLiteDbConstants.URL, page.url);
        openDb();
        db.insert(SQLiteDbConstants.TABLE_NAME, null, cv);
        closeDb();
    }

    // получение списка всех запией
    public ArrayList<Page> getDbSQLPageList(){
        openDb();
        ArrayList<Page> list = new ArrayList<>();
        Cursor cursor = db.query(SQLiteDbConstants.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            @SuppressLint("Range") String u = cursor.getString(cursor.getColumnIndex(SQLiteDbConstants.URL));
            list.add(new Page(u));
        }
        cursor.close();
        closeDb();
        return list;
    }
    // удаление записи по id в бд
    public void deleteNote(int id) {
        openDb();
        db.delete(SQLiteDbConstants.TABLE_NAME, SQLiteDbConstants._ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

}
