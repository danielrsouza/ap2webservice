package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class EstadosAdapter(contexto: Context) : ArrayAdapter<DadosEstados>(contexto, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v:View

        if (convertView != null) {
            v = convertView
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.layout_estados, parent, false)
        }

        val item = getItem(position)

        val uf = v.findViewById<TextView>(R.id.text_uf)
        val estado = v.findViewById<TextView>(R.id.text_estado)
        val casos = v.findViewById<TextView>(R.id.text_casos)
        val mortes = v.findViewById<TextView>(R.id.text_mortes)
        val suspeitos = v.findViewById<TextView>(R.id.text_suspeitos)
        val data = v.findViewById<TextView>(R.id.text_data)


        uf.text = item?.uf.toString()
        estado.text = item?.estado.toString()
        casos.text = item?.casos.toString()
        mortes.text = item?.mortes.toString()
        suspeitos.text = item?.suspeitos.toString()
        data.text = item?.data.toString()

        return v

    }
}