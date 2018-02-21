package com.example.jessicachandra.mypocketlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by Jessica Chandra on 30/01/2018.
 */

public class ListAdapterCatatan extends ArrayAdapter<String> {
    private final Context context;
    private final String[] nama,keterangan,nilai,mode,id;


    public String[] getId() {
        return id;
    }

    public ListAdapterCatatan(Context context, String[] nama, String[] keterangan, String[] nilai, String[]mode, String[] id){
     super(context, R.layout.catatan_list,nama);
     this.context =context;
     this.nama = nama;
     this.keterangan = keterangan;
     this.nilai = nilai;
     this.mode = mode;
     this.id = id;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.catatan_list, parent, false);
        TextView tvNama = (TextView) rowView.findViewById(R.id.nama);
        TextView tvKeterangan = (TextView) rowView.findViewById(R.id.keterangan);
        TextView tvNilai = (TextView) rowView.findViewById(R.id.nilai);
        tvNama.setText(nama[position]);
        tvKeterangan.setText(keterangan[position]);
        tvNilai.setText(nilai[position]);

        return rowView;
    }

    }

