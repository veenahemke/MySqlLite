package com.example.veena.mysqllite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Veena on 13/10/2016.
 */

public class Placelist extends AppCompatActivity {
    MySqlLiteHelper helper=new MySqlLiteHelper(this);
    ListView lv;
    TextView tv;
    MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placelist);
        lv= (ListView) findViewById(R.id.placeslist);
        tv= (TextView) findViewById(R.id.txtresulttext);

        try {

            List<HashMap<String, String>> data = getAllPlace();
            if (data.size() != 0) {

                SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.rows, new String[]{"col_uid", "col_place", "col_country"}, new int[]{
                        R.id.txtplaceid, R.id.txtplacename, R.id.txtcountry});
                lv.setAdapter(adapter);
                String length = String.valueOf(data.size());
                tv.setText(length + " places");
            } else {
                tv.setText("No data in database");
            }
        } catch (Exception ex) {

            //tv.setText(ex.getMessage().toString());
        }

    }

    public  List<HashMap<String,String>> getAllPlace() {
        List<HashMap<String,String> > worldList;
        worldList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        String table=MySqlLiteHelper.getTABLENAME();
        String selectQuery = "SELECT  * FROM " + table;
        Cursor cursor = writableDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(MySqlLiteHelper.getID(), cursor.getString(0));
                map.put(MySqlLiteHelper.getPLACE(), cursor.getString(1));
                map.put(MySqlLiteHelper.getCOUNTRY(), cursor.getString(2));
                worldList.add(map);

            } while (cursor.moveToNext());
        }


        // return contact list
        return worldList;

    }
}


