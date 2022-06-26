package com.costcalc.calculadoradacostureira.views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.costcalc.calculadoradacostureira.R
import com.costcalc.calculadoradacostureira.domain.ModelAlert
import com.costcalc.calculadoradacostureira.views.fragment.ProdutoFragmentScreen
import com.costcalc.calculadoradacostureira.views.fragment.TecidoFragmentScreen
import com.google.android.gms.ads.*
import com.google.android.material.tabs.TabLayout
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val gson = Gson()

    private final val TAG = "errod"
    val adRequest = AdRequest.Builder().build()
    private var remoteConfigm = FirebaseRemoteConfig.getInstance()
    var alertStatus: Boolean = false
    var alertStartCorpo : Array<ModelAlert> = arrayOf(ModelAlert("titulo vazio", "corpo vazio", "texto vazio", "link vazio", "texto vazio"))

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Locale.setDefault(Locale("pt", "BR"))

        alertBox.visibility = View.GONE

        // Request Add
        MobileAds.initialize(this) {}
        adView.loadAd(adRequest)

        showFragment1(ProdutoFragmentScreen())
        tab_tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            // Handle tab select
            Log.i(TAG, "Selected: " + tab?.text)
            when (tab?.text) {
                "Produto" -> {
                    showFragment1(ProdutoFragmentScreen())
                }
                "Tecido" -> {
                    showFragment2(TecidoFragmentScreen())
                }
//                "Servico" -> {
//                    showFragment3(ServicoFragmentScreen())
//                }
                else -> {
                    showFragment1(ProdutoFragmentScreen())
                }
            }
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            // Handle tab reselect
            Log.i(TAG, "Reselected: " + tab?.text)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            // Handle tab unselect
            Log.i(TAG, "Unselected: " + tab?.text)
        }
    })

    remoteConfigm = Firebase.remoteConfig
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3000
    }
    remoteConfigm.setConfigSettingsAsync(configSettings)


    remoteConfigm.fetchAndActivate()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                remoteConfigm.fetch().addOnCompleteListener {
                    alertStatus = remoteConfigm.getBoolean("alertStartStatus")
                    if (alertStatus) {
                        Log.i(TAG, "result = $alertStatus")
                        val jsonCorpo: ModelAlert = gson.fromJson(remoteConfigm.getString("alertStartCorpo"), ModelAlert::class.java)
                        openStartAlert(jsonCorpo)
                    } else {
                        Log.i(TAG, "result M = $alertStatus")
                    }
                }
            } else {
                Log.i(TAG, "não foi successful")
            }
        }

        goSetting.setOnClickListener() {
            val intentsetting = Intent(this, Cadastro::class.java)
            startActivity(intentsetting)
        }
    } // fim OnCreate

    private fun openStartAlert(corpo: ModelAlert) {

        alertBox.visibility = View.VISIBLE
        alertTitulo.text = corpo.title
        alertCorpo.text = corpo.body
        alertButtonActionText.text = corpo.buttonActionText
        alertButtonDimissText.text = corpo.buttonDimissText
        val url = corpo.buttonActionLink

        alertButtonActionText.setOnClickListener() {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        alertButtonDimissText.setOnClickListener() {
            alertBox.visibility = View.GONE
        }

    }

    fun showFragment1(fragment: ProdutoFragmentScreen){
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.tab_viewpager, fragment)
        fram.commit()
    }

    fun showFragment2(fragment: TecidoFragmentScreen){
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.tab_viewpager, fragment)
        fram.commit()
    }

//    fun showFragment3(fragment: ServicoFragmentScreen){
//        val fram = supportFragmentManager.beginTransaction()
//        fram.replace(R.id.tab_viewpager, fragment)
//        fram.commit()
//    }

}