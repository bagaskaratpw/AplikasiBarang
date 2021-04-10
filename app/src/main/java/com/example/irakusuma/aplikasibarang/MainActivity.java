package com.example.irakusuma.aplikasibarang;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    Button btnImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editMarks = (EditText)findViewById(R.id.editText_marks);
        editTextId = (EditText)findViewById(R.id.editTextId);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_view);
        btnUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        btnImage = (Button)findViewById(R.id.buttonImage) ;
        AddData();
        viewAll();
        UpdateData();
        deleteData();
    }

    // MEMBUAT MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //fungsi hapus
    public void deleteData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(MainActivity.this,"Data Berhasil Dihapus",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Gagal Menghapus Data!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //fungsi update
    public void UpdateData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editTextId.getText().toString(), editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(MainActivity.this,"Data Berhasil Dirubah",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Gagal Merubah Data",Toast.LENGTH_LONG).show();
            }
        });
    }

    //fungsi tambah
    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editTextId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString() );
                if(isInserted == true)
                    Toast.makeText(MainActivity.this,"Data Berhasil Disimpan",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Gagal Menambahkan Data",Toast.LENGTH_LONG).show();
            }
        });
    }

    //fungsi menampilkan data
    public void viewAll() {
        btnViewAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();

                if(res.getCount() == 0) {
                    // show message
                    showMessage("Gagal ","Data Tidak Ada");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext() ) {
                    buffer.append("Kode : "+ res.getString(0)+"\n");
                    buffer.append("Nama Barang : " + res.getString(1)+"\n");
                    buffer.append("Jumlah Barang : "+ res.getString(2)+" Pcs\n");
                    buffer.append("Harga Barang : Rp. " + res.getString(3) +"\n\n");

                }

                // show all data
                showMessage("Data Barang :",buffer.toString());
            }
        });
    }

    //membuat alert dialog
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

