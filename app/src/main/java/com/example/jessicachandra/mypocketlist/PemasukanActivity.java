package com.example.jessicachandra.mypocketlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PemasukanActivity extends AppCompatActivity {
    String[] listCatatanNama, listActionCatatanKeterangan, listActionCatatanNilai, listActionCatatanMode, id;

    ListView lvPengeluaran;
    protected Cursor cursor;
    DataHelper dbcenter = new DataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);

        refreshList();
    }

    private void refreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pemasukan", null);

        listCatatanNama = new String[cursor.getCount()];
        listActionCatatanKeterangan = new String[cursor.getCount()];
        listActionCatatanNilai = new String[cursor.getCount()];
        listActionCatatanMode = new String[cursor.getCount()];
        id = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            listCatatanNama[cc] = cursor.getString(1).toString();
            listActionCatatanKeterangan[cc] = cursor.getString(2).toString();
            listActionCatatanNilai[cc] = cursor.getString(3).toString();
            listActionCatatanMode[cc] = cursor.getString(4).toString();
            id[cc] = cursor.getString(0).toString();
        }

        ListAdapterCatatan adapterCatatan = new ListAdapterCatatan(this,
                listCatatanNama,
                listActionCatatanKeterangan,
                listActionCatatanNilai,
                listActionCatatanMode,
                id);

        lvPengeluaran = (ListView) findViewById(R.id.lvpemasukan);

        lvPengeluaran.setAdapter(adapterCatatan);

        lvPengeluaran.setSelected(true);
        lvPengeluaran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final int pilihan = arg2;
                final String selection = id[arg2];
                //Toast.makeText(CatatanActivity.this, selection, Toast.LENGTH_SHORT).show();

                final CharSequence[] dialogitem = {"Ubah", "Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(PemasukanActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        SQLiteDatabase db = dbcenter.getWritableDatabase();

                        switch (item) {
                            case 0:
                                Intent i = new Intent(PemasukanActivity.this, Input_Cash_Activity.class);
                                i.putExtra("mode", "update");
                                i.putExtra("id", selection);
                                i.putExtra("nama", listCatatanNama[pilihan]);
                                i.putExtra("keterangan", listActionCatatanKeterangan[pilihan]);
                                i.putExtra("nilai", listActionCatatanNilai[pilihan]);

                                startActivity(i);
                                refreshList();
                                break;
                            case 1:
                                db.execSQL("delete from catatan where id = '" + selection + "'");
                                refreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) lvPengeluaran.getAdapter()).notifyDataSetInvalidated();


    }
}
