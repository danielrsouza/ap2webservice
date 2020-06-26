package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_brasil.*
import kotlinx.android.synthetic.main.activity_paises.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


var listaDadosBrasil = mutableListOf<DadosBrasil>()
var dadosBrasil = ArrayList<DadosBrasil>()

class BrasilActivity : AppCompatActivity() {

    val API_URL = "https://covid19-brazil-api.now.sh/api/report/v1/brazil"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brasil)
        buscaBrasil()
        var brasilAdapter = BrasilAdapter(this)
        brasilAdapter.addAll(dadosBrasil)
        lista_view_brasil.adapter = brasilAdapter
    }


    fun buscaBrasil() {
        doAsync {
            val resposta = URL(API_URL).readText()

            uiThread {
                val jsonObject  = JSONObject(resposta)
                val jsonGetData = jsonObject.get("data").toString()

                val jsonObj = JSONObject(jsonGetData)
                Log.e("t",jsonObj.getString("country"))
                val pais = jsonObj.getString("country")
                val casos = jsonObj.getInt("cases")
                val confirmados = jsonObj.getInt("confirmed")
                val mortes = jsonObj.getInt("deaths")
                val recuperado =  jsonObj.getString("recovered")
                val atualizado = jsonObj.getString("updated_at")


                    val dados = DadosBrasil(pais, casos, confirmados, mortes, recuperado, atualizado)
                    listaDadosBrasil.add(dados)

                dadosBrasil.addAll(listaDadosBrasil)
            }
        }
    }
}