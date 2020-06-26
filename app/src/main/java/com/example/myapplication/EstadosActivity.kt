package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_estados.*
import org.jetbrains.anko.doAsync
import java.net.URL
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject

var listaDadosEstados = mutableListOf<DadosEstados>()
var dadosEstados = ArrayList<DadosEstados>()

class EstadosActivity : AppCompatActivity() {

    val API_URL = "https://covid19-brazil-api.now.sh/api/report/v1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estados)
        buscaEstado()

        var estadosAdapter = EstadosAdapter(this)
        estadosAdapter.addAll(dadosEstados)

        Log.e("Dados Estados", dadosEstados.toString())
        Log.e("Dados Estados", listaDadosEstados.toString())

        lista_view.adapter = estadosAdapter

    }

    override fun onResume() {
        super.onResume()
        buscaEstado()

        var estadosAdapter = EstadosAdapter(this)
        estadosAdapter.addAll(dadosEstados)

        Log.e("Dados Estados", dadosEstados.toString())
        Log.e("Dados Estados", listaDadosEstados.toString())

        lista_view.adapter = estadosAdapter

    }

    fun buscaEstado() {
        doAsync {
            val resposta = URL(API_URL).readText()

            uiThread {
                val jsonObject  = JSONObject(resposta)
                val jsonGetData = jsonObject.get("data").toString()

                val jsonArray = JSONArray(jsonGetData)

                Log.e("teste2", jsonArray.toString())

                for (i in 0..jsonArray.length() - 1) {
                    val jsonObjectList = jsonArray.getJSONObject(i)
                    val uf = jsonObjectList.getString("uf")
                    val estado = jsonObjectList.getString("state")
                    val casos = jsonObjectList.getInt("cases")
                    val mortes = jsonObjectList.getInt("deaths")
                    val suspeitos =  jsonObjectList.getInt("suspects")
                    val data = jsonObjectList.getString("datetime")

                    val dados = DadosEstados(uf, estado, casos, mortes, suspeitos, data)
                    listaDadosEstados.add(dados)

                }
                dadosEstados.addAll(listaDadosEstados)

                Log.e("Dados Estados", dadosEstados.toString())
                Log.e("Dados Estados", listaDadosEstados.toString())
            }
        }
    }
}
