package com.example.skpi_cps_umam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class LihatData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data)

        val db = Firebase.firestore
        var arrayAdapter: ArrayAdapter<*>
        var listView = findViewById<ListView>(R.id.listView)

        db.collection("Inventori")
            .get()
            .addOnSuccessListener { result ->
                Toast.makeText(applicationContext,"Berhasil",
                    Toast.LENGTH_SHORT).show()
                var databarang = ArrayList<String>()
                for (document in result) {
                    var barang = document.id+" => "+document.data["nama"]+
                            " => "+ document.data["jumlah"]
                    databarang.add(barang)
                }
                arrayAdapter = ArrayAdapter(applicationContext,
                    android.R.layout.simple_list_item_1,databarang)
                listView.adapter = arrayAdapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(applicationContext,"Gagal - " +
                        exception.toString(), Toast.LENGTH_SHORT).show()
            }
        var btnKembali = findViewById<Button>(R.id.btnKembali)
        btnKembali.setOnClickListener {
            finish()
        }
    }
}