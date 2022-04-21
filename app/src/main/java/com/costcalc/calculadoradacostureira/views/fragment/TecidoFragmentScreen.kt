package com.costcalc.calculadoradacostureira.views.fragment

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.costcalc.calculadoradacostureira.R
import com.costcalc.calculadoradacostureira.domain.Fomarter.DoubleToString
import com.costcalc.calculadoradacostureira.domain.counts.Area
import com.costcalc.calculadoradacostureira.domain.counts.CalcTecido
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.calcular_tecido.*

class TecidoFragmentScreen : Fragment() {

    var areaComprada: Double = 0.0
    var areaUsada: Double = 0.0
    var pagou: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.calcular_tecido, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yes.isChecked = true
        yes_us.isChecked = true
        area_box.visibility = View.GONE
        area_box_us.visibility = View.GONE
        finalView.visibility = View.GONE

        yes.setOnClickListener() {
            if (yes.isChecked) {
                no.isChecked = false
                area.visibility = View.VISIBLE
                area_box.visibility = View.GONE
            } else {
                yes.isChecked = true
            }
        }

        no.setOnClickListener() {
            if (yes.isChecked) {
                yes.isChecked = false
                area.visibility = View.GONE
                area_box.visibility = View.VISIBLE
            } else {
                no.isChecked = true
            }
        }

        yes_us.setOnClickListener() {
            if (yes_us.isChecked) {
                no_us.isChecked = false
                area_us.visibility = View.VISIBLE
                area_box_us.visibility = View.GONE
            } else {
                yes_us.isChecked = true
            }
        }

        no_us.setOnClickListener() {
            if (yes_us.isChecked) {
                yes_us.isChecked = false
                area_us.visibility = View.GONE
                area_box_us.visibility = View.VISIBLE
            } else {
                no_us.isChecked = true
            }
        }

        close1.setOnClickListener() {
            finalView.visibility = View.GONE
        }

        close2.setOnClickListener() {
            finalView.visibility = View.GONE
        }


        CalcularTecido.setOnClickListener() { view ->

            Validations(view)
        }

        // inflar spinner 1
        val adapterSpinner1 = ArrayAdapter.createFromResource(requireContext() as Activity, R.array.medidas, R.layout.sample_spinner)
        adapterSpinner1.setDropDownViewResource(R.layout.sample_spinner)
        // Apply the adapter to the spinner
        medida_spinner1.adapter = adapterSpinner1
        //---> PARA SELECIONAR // medida_spinner1.selectedItem.toString() == "cm"...

        // inflar spinner 2
        val adapterSpinner2 = ArrayAdapter.createFromResource(requireContext() as Activity, R.array.medidas, R.layout.sample_spinner)
        adapterSpinner2.setDropDownViewResource(R.layout.sample_spinner)
        medida_spinner2.adapter = adapterSpinner2

        // inflar spinner 3
        val adapterSpinner3 = ArrayAdapter.createFromResource(requireContext() as Activity, R.array.medidas, R.layout.sample_spinner)
        adapterSpinner3.setDropDownViewResource(R.layout.sample_spinner)
        medida_spinner3.adapter = adapterSpinner3

        // inflar spinner 4
        val adapterSpinner4 = ArrayAdapter.createFromResource(requireContext() as Activity, R.array.medidas, R.layout.sample_spinner)
        adapterSpinner4.setDropDownViewResource(R.layout.sample_spinner)
        medida_spinner4.adapter = adapterSpinner4

    }// fim onCreate

  private fun Validations(view: View) {
      // Area Comprada
      if(yes.isChecked) {
          if(largura.text.toString() == "" || altura.text.toString() == "") {
              Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                  .setAction("Action", null)
                  .show()
          } else {
              var larguram2: Double = largura.text.toString().toDouble()
              var alturam2: Double = altura.text.toString().toDouble()

              if(medida_spinner1.selectedItem.toString() == "cm") {
                  larguram2 = largura.text.toString().toDouble() / 100
                  alturam2 = altura.text.toString().toDouble() / 100
              }
              areaComprada = Area(larguram2, alturam2).CalcularArea()
          }
      } else {
          var aream2: Double = area_input.text.toString().toDouble()
          if (medida_spinner2.selectedItem.toString() == "cm") {
              aream2 = area_input.text.toString().toDouble() / 100
          }
          areaComprada = aream2
      }

      // Area Usada
      if(yes_us.isChecked) {
          if(largura_us.text.toString() == "" || altura_us.text.toString() == "") {
              Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                  .setAction("Action", null)
                  .show()
          } else {
              var larguram2_us: Double = largura_us.text.toString().toDouble()
              var alturam2_us: Double = altura_us.text.toString().toDouble()
              if(medida_spinner3.selectedItem.toString() == "cm") {
                  larguram2_us = largura_us.text.toString().toDouble() / 100
                  alturam2_us = altura_us.text.toString().toDouble() / 100
              }
              areaUsada = Area(larguram2_us, alturam2_us).CalcularArea()
          }
      } else {
          var aream2_us: Double = area_input_us.text.toString().toDouble()
          if (medida_spinner4.selectedItem.toString() == "cm") {
              aream2_us = area_input_us.text.toString().toDouble() / 100
          }
          areaUsada = aream2_us
      }

      if(pago.text.toString() == "") {
          Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
              .setAction("Action", null)
              .show()
      } else {
          pagou = pago.text.toString().toDouble()
      }

      // chamar calcular tecido

      if (areaComprada <= 0 || areaUsada <= 0 || pagou <= 0) {
          Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
              .setAction("Action", null)
              .show()
      } else if(areaUsada > areaComprada) {
          Snackbar.make(view, "você não pode usar mais do que comprou", Snackbar.LENGTH_LONG)
              .setAction("Action", null)
              .show()
      } else {
          CalcularTecido(pagou, areaComprada, areaUsada)
          Log.i(TAG, "espiner 1 = $medida_spinner1 || espiner 2 $medida_spinner2 || espiner 3 = $medida_spinner3 || espiner 4 = $medida_spinner4")
      }
  }


    fun CalcularTecido(pago1: Double, areaComprada1: Double, areaUsada1: Double) {
       val resultado = CalcTecido(pago1, areaComprada1, areaUsada1).ContaFinalTecido()

        finalView.visibility = View.VISIBLE
        finalResult.text = "O gasto no tecido é " + resultado
    }
}