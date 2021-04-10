package com.example.irakusuma.aplikasibarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //nama database
    public static final String DATABASE_NAME = "barang.db";
    //nama table
    public static final String TABLE_NAME = "table_barang";
    //database version
    private static final int DATABASE_VERSION = 1;
    //field database
    public static final String COL_1 = "kode";
    public static final String COL_2 = "nama_barang";
    public static final String COL_3 = "jumlah_barang";
    public static final String COL_4 = "harga_barang";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table table_barang(kode integer primary key," + "nama_barang text null," + "jumlah_barang integer null," + "harga_barang integer null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //tambah data
    public boolean insertData(String kode, String nama_barang, String jumlah_barang, String harga_barang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, nama_barang);
        contentValues.put(COL_3, jumlah_barang);
        contentValues.put(COL_4, harga_barang);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //mengambil data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM table_barang", null);
        return res;
    }

    //merubah data
    public boolean updateData(String kode, String nama_barang, String jumlah_barang, String harga_barang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kode);
        contentValues.put(COL_2, nama_barang);
        contentValues.put(COL_3, jumlah_barang);
        contentValues.put(COL_4, harga_barang);
        db.update(TABLE_NAME, contentValues, "kode = ?", new String[]{kode});
        return true;
    }

    //menghapus data
    public int deleteData(String kode) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "kode = ?", new String[]{kode});
    }
}

