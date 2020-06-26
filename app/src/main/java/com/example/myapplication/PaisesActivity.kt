package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_paises.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

var listaDadosPaises = mutableListOf<DadosPaises>()
var dadosPaises = ArrayList<DadosPaises>()

class PaisesActivity : AppCompatActivity() {

    val API_URL = "https://covid19-brazil-api.now.sh/api/report/v1/countries"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paises)
        buscaPaises()
        var paisAdapter = PaisesAdapter(this)
        paisAdapter.addAll(dadosPaises)
        lista_view_paises.adapter = paisAdapter
    }



    fun buscaPaises() {
        doAsync {
            val resposta = URL(API_URL).readText()

            uiThread {
                val jsonObject  = JSONObject(resposta)
                val jsonGetData = jsonObject.get("data").toString()

                val jsonArray = JSONArray(jsonGetData)


                for (i in 0..jsonArray.length() - 1) {
                    val jsonObjectList = jsonArray.getJSONObject(i)
                    val pais = jsonObjectList.getString("country")
                    val casos = jsonObjectList.getInt("cases")
                    val confirmados = jsonObjectList.getInt("confirmed")
                    val mortes = jsonObjectList.getInt("deaths")
                    val recuperado =  jsonObjectList.getString("recovered")
                    val atualizado = jsonObjectList.getString("updated_at")

                    val dados = DadosPaises(pais, casos, confirmados, mortes, recuperado, atualizado)
                    listaDadosPaises.add(dados)

                }
                dadosPaises.addAll(listaDadosPaises)
            }
        }
    }
}