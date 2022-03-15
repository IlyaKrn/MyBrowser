package com.example.browser.SQLiteDatabase;

/**
 * класс с константами базы данных
 * */

public class SQLiteDbConstants {

    // название базы данных и таблицы
    public static final String DB_NAME = "my_db.db";
    public static final String TABLE_NAME ="table_name";
    // столбцы таблицы
    public static final String _ID = "_id";
    public static final String URL = "url";

    // версия бд
    public static final int DB_VERSION = 1;
    // структура и сброс таблицы
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String TABLE_STRUCTURE = 
    "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
            URL + " TEXT)";

    
}
