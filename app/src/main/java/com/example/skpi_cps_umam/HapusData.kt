package com.example.skpi_cps_umam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HapusData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hapus_data)

        val db = Firebase.firestore
        var editIdBarang = findViewById<EditText>(R.id.editIdBarang)
        var btnHapus = findViewById<Button>(R.id.btnHapus)
        var btnBatalkan = findViewById<Button>(R.id.btnBatalkan)

        btnHapus.setOnClickListener {
            val idBarang = editIdBarang.text.toString()
            db.collection("Inventori").document(idBarang).delete()
                .addOnSuccessListener { result ->
                    Toast.makeText(applicationContext,"Berhasil",
                        Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext,"Gagal - " +
                            exception.toString(), Toast.LENGTH_SHORT).show()
                }
            finish()
        }
        btnBatalkan.setOnClickListener {
            finish()
        }

    }
}