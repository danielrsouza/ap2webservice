package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.net.URL
import org.jetbrains.anko.alert
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscaEstado()
        buscaPaises()
        buscaBrasil()



    }

    fun buscaEstado()
    {
        button_buscaEstado.setOnClickListener {
            val intent = Intent(this, EstadosActivity::class.java)
            startActivity(intent)
        }
    }

    fun buscaPaises()
    {
        button_buscaPaises.setOnClickListener {
            val intent = Intent(this, PaisesActivity::class.java)
            startActivity(intent)
        }
    }

    fun buscaBrasil()
    {
        button_buscaBrasil.setOnClickListener {
            val intent = Intent(this, BrasilActivity::class.java)
            startActivity(intent)
        }
    }


}
