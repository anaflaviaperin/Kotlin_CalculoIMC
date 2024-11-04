package com.example.aplicativoimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso : EditText
    private lateinit var etAltura : EditText
    private lateinit var tvResultado : TextView
    private lateinit var btCalcular : Button
    private lateinit var btLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById( R.id.etPeso)
        etAltura = findViewById( R.id.etAltura)
        tvResultado = findViewById( R.id.tvResultado)
        btCalcular = findViewById( R.id.btCalcular)
        btLimpar = findViewById( R.id.btLimpar)

        btCalcular.setOnClickListener {
            btCalcularOnClick()
        }
    }

    private fun btCalcularOnClick() {

        if (etPeso.text.isEmpty()) {
            etPeso.error = getString(R.string.erro_peso)
            return
        }

        if (etAltura.text.isEmpty()) {
            etAltura.error = getString(R.string.erro_altura)
            return
        }

        val resultado:Double

        val peso = etPeso.text.toString().toDouble()

        val altura = etAltura.text.toString().toDouble()

        resultado = if (!Locale.getDefault().language.equals("en")) {
            val calcula = Calcula()
            calcula.calculaIMC(peso, altura)
        } else {
            703 * (peso / (altura.pow(2)))
        }

        val numberFormat = NumberFormat.getNumberInstance(Locale.US)

        tvResultado.text = numberFormat.format(resultado)
    }

    private fun btLimparOnClick() {
        etPeso.setText("")
        etAltura.setText("")
        tvResultado.text = getString(R.string.zeros)
        etPeso.requestFocus()
    }
}

class Calcula {
    fun calculaIMC( peso : Double, altura : Double) : Double {
        return peso / (altura.pow(2))
    }
}