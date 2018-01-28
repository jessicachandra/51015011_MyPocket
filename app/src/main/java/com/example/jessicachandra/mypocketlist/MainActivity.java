package com.example.jessicachandra.mypocketlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    Intent i;
    DataHelper dbcenter = new DataHelper(this);
    TextView tvNumPemasukan,tvNumPengeluaran, tvNumTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumPemasukan = (TextView) findViewById(R.id.tvNumPemasukan);
        tvNumPengeluaran = (TextView) findViewById(R.id.tvNumPengeluaran);
        tvNumTotal = (TextView) findViewById(R.id.tvNumTotal);
    }

    @Override
    protected void onResume(){
        super.onResume();

        SQLiteDatabase db = dbcenter.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(nilai) FROM catatan WHERE pemasukan ='1'",null);
        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                tvNumPemasukan.setText(cursor.getString(0).toString());
            }
        }catch (NullPointerException e){
            tvNumPemasukan.setText("0");
        }

        cursor = db.rawQuery("SELECT sum(nilai) FROM catatan WHERE pemasukan = '0'",null);
    try{
    if (cursor.getCount() > 0){
    cursor.moveToFirst();
    tvNumPengeluaran.setText(cursor.getString(0).toString());
    }
    }catch (NullPointerException e){
        tvNumPengeluaran.setText("0");
    }

        tvNumTotal.setText(String.valueOf(Integer.parseInt(tvNumPemasukan.getText().toString()) + Integer.parseInt(tvNumPengeluaran.getText().toString())));
    }

    @Override
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionItemSelect(MenuItem item){
        int menupilihan = item.getItemId();
        if (menupilihan= R.id.lock){
            keluar();
            return true;
        }

        return super.onOptionsItemSelected(item);

        }

        public void keluar(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
    public void prosesInput (View view) {
        i = new Intent(this, Input_Cash_Activity.class);
        switch (view.getId()) {
            case R.id.bPemasukan:
                i.putExtra("mode", "pemasukan");
                break;
            case R.id.bPengeluaran:
                i.putExtra("mode", "pengeluaran");
                break;

        }

        startActivityForResult(i, 1);
    }

    public void lihatCatatan(View view){
            Intent i = new Intent(this,CatatanActivity.class);
            startActivity(i);
    }
}
