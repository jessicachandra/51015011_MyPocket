package com.example.jessicachandra.mypocketlist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Input_Cash_Activity extends AppCompatActivity {

    DataHelper dbHelper = new DataHelper(this);
    private String mode;
    TextView tvMode;
    EditText etNama, etDeskripsi, etJumlah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input__cash_);

        mode = getIntent().getStringExtra("mode");

        tvMode = (TextView) findViewById(R.id.tvMode);

        etNama = (EditText) findViewById(R.id.etNamaTransaksi);
        etDeskripsi = (EditText) findViewById(R.id.etDeskripsi);
        etJumlah = (EditText) findViewById(R.id.etJumlah);

        switch (mode){
            case "pemasukan"    :   tvMode.setText("PEMASUKAN");
                tvMode.setTextColor(Color.parseColor("#4CAF50"));
                break;
            case "pengeluaran"  :   tvMode.setText("PENGELUARAN");
                tvMode.setTextColor(Color.parseColor("#f44336"));
                break;
            case "update"       :   tvMode.setText("update");
                etNama.setText(getIntent().getStringExtra("nama"));
                etDeskripsi.setText(getIntent().getStringExtra("keterangan"));
                etJumlah.setText(getIntent().getStringExtra("nilai"));
                break;
        }


    }

    public void prosesInput(View view) {

        Log.d("tahap","inputcash - 0");

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql;

        Log.d("tahap","inputcash - 1");
        switch (mode){
            case "pemasukan"    :   sql = "insert into catatan(nama,deskripsi,nilai,pemasukan) values('" +
                    etNama.getText().toString() + "', '" +
                    etDeskripsi.getText().toString() + "', '" +
                    etJumlah.getText().toString() + "', '" +
                    "1')";
                Log.d("[SQL]", ("sql inputCashFlowActivity => " + sql) );
                db.execSQL(sql);
                sql = "insert into pemasukan(nama,deskripsi,nilai,pemasukan) values('" +
                        etNama.getText().toString() + "', '" +
                        etDeskripsi.getText().toString() + "', '" +
                        etJumlah.getText().toString() + "', '" +
                        "1')";
                Log.d("[SQL]", ("sql inputCashFlowActivity => " + sql) );
                db.execSQL(sql);
                break;
            case "pengeluaran"  :   sql = "insert into catatan(nama,deskripsi,nilai,pemasukan) values('" +
                    etNama.getText().toString() + "', '" +
                    etDeskripsi.getText().toString() + "', '-" +
                    etJumlah.getText().toString() + "', '" +
                    "0')";
                Log.d("[SQL]", ("sql inputCashFlowActivity => " + sql) );
                db.execSQL(sql);
                sql = "insert into pengeluaran(nama,deskripsi,nilai,pemasukan) values('" +
                        etNama.getText().toString() + "', '" +
                        etDeskripsi.getText().toString() + "', '-" +
                        etJumlah.getText().toString() + "', '" +
                        "0')";
                Log.d("[SQL]", ("sql inputCashFlowActivity => " + sql) );
                db.execSQL(sql);
                break;
            case "update"  :   sql = "update catatan set " +
                    "nama ='" + etNama.getText().toString() + "', " +
                    "deskripsi ='" + etDeskripsi.getText().toString() + "', " +
                    "nilai ='" + etJumlah.getText().toString() +
                    "' WHERE id = '" + getIntent().getStringExtra("id") + "'";
                Log.d("[SQL]", ("sql inputCashFlowActivity => " + sql) );
                db.execSQL(sql);
                break;
            default:Log.d("tahap","inputcash - 2");
        }

        Intent i;
        switch (mode) {
            case "update" : i = new Intent(this, CatatanActivity.class);
                startActivity(i);
            default:    i = new Intent(this, MainActivity.class);
                startActivity(i);
        }
    }
}
