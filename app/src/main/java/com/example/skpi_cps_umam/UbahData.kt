package com.example.skpi_cps_umam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UbahData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_data)

        val db = Firebase.firestore
        var editIdBarang = findViewById<EditText>(R.id.editIdBarang)
        var btnAmbil = findViewById<Button>(R.id.btnAmbil)
        var editNamaBarang = findViewById<EditText>(R.id.editNamaBarang)
        var editJumlahBarang = findViewById<EditText>(R.id.editJumlahBarang)
        var editDeskripsiBarang = findViewById<EditText>(R.id.editDeskripsiBarang)

        btnAmbil.setOnClickListener {
            val idBarang = editIdBarang.text.toString()
            db.collection("Inventori").document(idBarang)
                .get()
                .addOnSuccessListener { result ->
                    Toast.makeText(applicationContext,"Berhasil",
                        Toast.LENGTH_SHORT).show()
                    editIdBarang.isEnabled = false
                    val namaBarang = result["nama"].toString()
                    val jumlahBarang = result["jumlah"].toString()
                    val deskripsiBarang = result["deskripsi"].toString()
                    editNamaBarang.setText(namaBarang)
                    editJumlahBarang.setText(jumlahBarang)
                    editDeskripsiBarang.setText(deskripsiBarang)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext,"Gagal - " +
                            exception.toString(),Toast.LENGTH_SHORT).show()
                }
        }
        var btnPerbarui = findViewById<Button>(R.id.btnPerbarui)
        var btnBatalkan = findViewById<Button>(R.id.btnBatalkan)

        btnPerbarui.setOnClickListener {
            val idBarang = editIdBarang.text.toString()
            val namaBaru = editNamaBarang.text.toString()
            val jumlahBaru = editJumlahBarang.text.toString()
            val deskripsiBaru = editDeskripsiBarang.text.toString()
// Enkapsulasi Data
            val barang = hashMapOf(
                "id" to idBarang.replace("Doc-",""),
                "nama" to namaBaru,
                "jumlah" to jumlahBaru,
                "deskripsi" to deskripsiBaru
            )
            db.collection("Inventori").document(idBarang)
                .set(barang)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"Berhasil",
                        Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext,"Gagal - " +
                            exception.toString(),Toast.LENGTH_SHORT).show()
                }
            finish()
        }
        btnBatalkan.setOnClickListener {
            finish()
        }
    }
}