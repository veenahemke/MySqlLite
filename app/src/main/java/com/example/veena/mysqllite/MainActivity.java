package com.example.veena.mysqllite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_NONE;

public class MainActivity extends AppCompatActivity {
    EditText placeId,country,place;


    MySqlLiteHelper helper=new MySqlLiteHelper(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeId= (EditText) findViewById(R.id.edplaceid);
        country=(EditText) findViewById(R.id.edcountry);
        place=(EditText) findViewById(R.id.edplace);


        findViewById(R.id.btnadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(helper);
            }
        });
        findViewById(R.id.btnupdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                update1(getUserid());
            }
        });
        findViewById(R.id.btnview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Placelist.class);
                startActivity(i);


            }
        });

    }

private void update(String oldId) {
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        String table=MySqlLiteHelper.getTABLENAME();
        ContentValues values=new ContentValues();
       // values.put("ID","2");
        values.put(MySqlLiteHelper.getPLACE(),"nagpur1");
        values.put(MySqlLiteHelper.getCOUNTRY(),"india2");
        String[] whereArgs={oldId};
        writableDatabase.update( table,  values, MySqlLiteHelper.getID()+ "=?" ,whereArgs);

        Log.i("@codekul","data updated"+values.toString());
}

    private void add(MySqlLiteHelper helper) {
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        String table=MySqlLiteHelper.getTABLENAME();
        String nullColumnHack=null;
        ContentValues values=new ContentValues();
        values.put(MySqlLiteHelper.getID(),placeId.getText().toString());
        values.put(MySqlLiteHelper.getPLACE(),country.getText().toString());
        values.put(MySqlLiteHelper.getCOUNTRY(),place.getText().toString());
        writableDatabase.insert(table,null,values);
        Log.i("@codekul","data inserted"+values.toString());



    }

    private void update1(String getUserid ){

        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        String table=MySqlLiteHelper.getTABLENAME();
        ContentValues values=new ContentValues();
        // values.put("ID","2");
        values.put(MySqlLiteHelper.getPLACE(),place.getText().toString());
        values.put(MySqlLiteHelper.getCOUNTRY(),country.getText().toString());
        String[] whereArgs={getUserid};
        writableDatabase.update( table,  values, MySqlLiteHelper.getID()+ "=?" ,whereArgs);

        Log.i("@codekul","data updated"+values.toString());
    }
    private String getUserid(){
        return placeId.getText().toString();
    }
    }










