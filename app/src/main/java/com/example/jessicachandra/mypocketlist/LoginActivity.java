package com.example.jessicachandra.mypocketlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Intent i;
    DataHelper dbcenter;
    SQLiteDatabase db;
    Cursor cursor ;

    EditText etKodeAkses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         dbcenter = new DataHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cekKodeAkses();
    }

    public void prosesMasuk(View view) {
        db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM autentikasi WHERE passcode ='" + etKodeAkses.getText().toString() + "'", null);
        if (cursor.getCount() > 0) {
            i = new Intent(this, MainActivity.class);

            startActivity(i);
        } else {
            Toast.makeText(this, "kode akses salah!", Toast.LENGTH_SHORT).show();
        }

    }


    private void cekKodeAkses() {
        db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM autetikasi", null);

        if (cursor.getCount() > 0) {
            setContentView(R.layout.activity_login);
            etKodeAkses = (EditText) findViewById(R.id.etKodeAkses);
        } else {
            i = new Intent(this, RegisterActivity.class);
            startActivity(i);

        }
    }

}

