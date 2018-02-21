package com.example.jessicachandra.mypocketlist;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    DataHelper dbHelper = new DataHelper(this);
    EditText etKodeAkses;
    EditText etKodeAksesRetype;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etKodeAkses = (EditText) findViewById(R.id.etKodeAkses);
        etKodeAksesRetype = (EditText) findViewById(R.id.etKodeAksesRetype);
    }

    public void prosesDaftar(View view) {

        if(etKodeAkses.getText().toString().equals(etKodeAksesRetype.getText().toString())){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into autentikasi(passcode) values('" + etKodeAkses.getText().toString() + "')");

            finish();
        }else{
            Toast.makeText(this, "penulisan ulang kode akses salah!",Toast.LENGTH_SHORT).show();
        }

    }
}
