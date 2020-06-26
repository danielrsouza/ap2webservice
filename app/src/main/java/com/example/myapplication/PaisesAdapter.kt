package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PaisesAdapter(contexto: Context) : ArrayAdapter<DadosPaises>(contexto, 0)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View

        if (convertView != null) {
            v = convertView
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.layout_paises, parent, false)
        }

        val item = getItem(position)

        val paises = v.findViewById<TextView>(R.id.text_paises_pais)
        val casos = v.findViewById<TextView>(R.id.text_paises_casos)
        val confirmados = v.findViewById<TextView>(R.id.text_paises_confirmados)
        val mortes = v.findViewById<TextView>(R.id.text_paises_mortes)
        val recuperado = v.findViewById<TextView>(R.id.text_paises_recuperados)
        val atualizacao = v.findViewById<TextView>(R.id.text_paises_atualizacao)


        paises.text = item?.pais.toString()
        casos.text = item?.casos.toString()
        confirmados.text = item?.confirmados.toString()
        mortes.text = item?.mortes.toString()
        recuperado.text = item?.recuperado.toString()
        atualizacao.text = item?.atualizacao.toString()

        return v
    }
}