package com.example.skpi_cps_umam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.*
class TambahData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)

        val idBarang = UUID.randomUUID().toString()
            .replace("-","")
            .uppercase()
            .substring(0,10)

        var editIdBarang = findViewById<EditText>(R.id.idBarang)
        editIdBarang.setText(idBarang)
        var editNamaBarang = findViewById<EditText>(R.id.editNamaBarang)
        var editJumlahBarang = findViewById<EditText>(R.id.editJumlahBarang)
        var editDeskripsiBarang = findViewById<EditText>(R.id.editDeskripsiBarang)
        var btnTambahkan = findViewById<Button>(R.id.btnTambahkan)
        var btnBersihkan = findViewById<Button>(R.id.btnBersihkan)
        var btnBatalkan = findViewById<Button>(R.id.btnBatalkan)

        val db = Firebase.firestore

        btnTambahkan.setOnClickListener {
            val namaBarang = editNamaBarang.getText().toString()
            val jumlahBarang = editJumlahBarang.getText().toString()
            val deskripsiBarang = editDeskripsiBarang.getText().toString()
// Enkapsulasi Data
            val barang = hashMapOf(
                "id" to idBarang,
                "nama" to namaBarang,
                "jumlah" to jumlahBarang,
                "deskripsi" to deskripsiBarang
            )
// Query Tambah Data
            db.collection("Inventori").document("Doc-"+idBarang)
                .set(barang)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"Berhasil",
                        Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext,"Gagal - " + exception.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            finish()
        }
        btnBersihkan.setOnClickListener {
            editNamaBarang.setText("")
            editJumlahBarang.setText("")
            editDeskripsiBarang.setText("")
        }
        btnBatalkan.setOnClickListener {
            finish()
        }
    }
}