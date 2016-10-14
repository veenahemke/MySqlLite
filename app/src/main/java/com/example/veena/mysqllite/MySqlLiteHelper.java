package com.example.veena.mysqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Veena on 13/10/2016.
 */

public class MySqlLiteHelper extends SQLiteOpenHelper {
    private static final String TABLENAME = "tab_places";  // tablename
    private static final String PLACE = "col_place";  // column name
    private static final String ID = "col_uid";  // auto generated ID column
    private static final String COUNTRY = "col_country"; // column name
    private static final String DATABASENAME = "data_placesinfo"; // Dtabasename
    private static final int VERSION = 1; //versioncode of the database
    String upgradeQuery="DROP TABLE IF EXISTS"+TABLENAME;
    String query = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "(" + ID + " integer primary key, " + PLACE + " text, " + COUNTRY + " text)";


    public static String getTABLENAME() {
        return TABLENAME;
    }

    public static String getPLACE() {
        return PLACE;
    }

    public static String getID() {
        return ID;
    }

    public static String getCOUNTRY() {
        return COUNTRY;
    }

    public static String getDATABASENAME() {
        return DATABASENAME;
    }

    public static int getVERSION() {
        return VERSION;
    }

    public String getUpgradeQuery() {
        return upgradeQuery;
    }

    public void setUpgradeQuery(String upgradeQuery) {
        this.upgradeQuery = upgradeQuery;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public MySqlLiteHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(upgradeQuery);

    }


}
