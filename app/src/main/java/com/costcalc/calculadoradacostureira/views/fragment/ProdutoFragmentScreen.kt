package com.costcalc.calculadoradacostureira.views.fragment

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.costcalc.calculadoradacostureira.R
import com.costcalc.calculadoradacostureira.data.sqlite.ReturnName
import com.costcalc.calculadoradacostureira.data.sqlite.ReturnValorHora
import com.costcalc.calculadoradacostureira.views.MainActivity
import com.costcalc.calculadoradacostureira.views.Resultados
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.android.synthetic.main.calcular_produto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Double.parseDouble

class ProdutoFragmentScreen : Fragment() {

    var AddResponse: Boolean = false
    var AddId: String = ""

    val adRequest = AdRequest.Builder().build()
    var mRewardedAd: RewardedAd? = null
    var madicionais: Double = 0.0
    private final val TAG = "errod"

    private var remoteConfig = FirebaseRemoteConfig.getInstance()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.calcular_produto, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CalcularCalc.setOnClickListener { view ->
            Validations(view)
        }
        loadRewardedAd()

        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3000
        }
        remoteConfig.setConfigSettingsAsync(configSettings)


        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    remoteConfig.fetch().addOnCompleteListener {
                        AddResponse = remoteConfig.getBoolean("contemAdd")
                        Log.i(TAG, "result produto = $AddResponse")
                    }
                } else {
                    Log.i(TAG, "não foi successful")
                }
            }
    }

    fun Validations(view: View) {
        if (tempo.text.toString() == ""
            || pagou.text.toString() == ""
            || comprado.text.toString() == ""
            || usado.text.toString() == ""
            || lucro.text.toString() == ""
        ) {
            Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        } else if (parseDouble(comprado.text.toString()) < parseDouble(usado.text.toString())) {
            Snackbar.make(view, "Você não pode usar mais do que comprou", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        } else {
            FirebaseRemoteAddCall(view)
        }
    }

    fun FirebaseRemoteAddCall(view: View) {
        if (AddResponse) {
            showAdd(view)
        } else {
            CalcularSucess()
        }
    }

    fun CalcularSucess() {
        val mtempo: Double = tempo.text.toString().toDouble()
        val mpagou: Double = pagou.text.toString().toDouble()
        val mcomprado: Double = comprado.text.toString().toDouble()
        val musado: Double = usado.text.toString().toDouble()
        if (!adicionais.text.toString().equals("")) {
            madicionais = adicionais.text.toString().toDouble()
        }
        val mlucro: Double = lucro.text.toString().toDouble()
        val dadosCalc: DoubleArray = doubleArrayOf(mtempo, mpagou, mcomprado, musado, madicionais, mlucro)
        val int = Intent(activity, Resultados::class.java)
        int.putExtra("dadosCalc", dadosCalc)
        activity?.startActivity(int)
    }

    fun showAdd(view: View) {
        if (mRewardedAd != null) {
            mRewardedAd?.show(requireContext() as Activity, OnUserEarnedRewardListener() {
                Log.d(TAG, "User earned the reward.")
                CalcularSucess()
            })

        } else {
            loadRewardedAd()
            Log.i(TAG, "The rewarded ad wasn't ready yet.")
            Snackbar.make(view, "tivemos um problema na conexão, tente novamente daqui a 10 segundos",
                Snackbar.LENGTH_LONG)
                .show()
        }
    }

    fun loadRewardedAd() {
        RewardedAd.load(requireContext() as Activity, AddId, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.message.let { Log.i(TAG, it) }
                mRewardedAd = null
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                Log.i(TAG, "Ad was loaded.")
                mRewardedAd = rewardedAd
                mRewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.i(TAG, "Ad was shown.")
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        Log.i(TAG, "Ad failed to show.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        loadRewardedAd()
                        Log.i(TAG, "Ad was dismissed.")
                        mRewardedAd = null
                    }
                }
            }
        })
    }
}