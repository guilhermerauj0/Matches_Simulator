package com.example.matchessimulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.matchessimulator.R
import com.example.matchessimulator.data.MatchesApi
import com.example.matchessimulator.databinding.ActivityMainBinding
import com.example.matchessimulator.domain.Match
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var matchesApi: MatchesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupMatchesList()
        setupMatchesRefresh()
        setupFloatActionButton()
    }

    private fun setupHttpClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://digitalinnovationone.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create()) //INICIALIZAÇÃO E REINICIALIZAÇÃO DO JSON
            .build()

        matchesApi = retrofit.create(MatchesApi::class.java)
    }

    private fun setupFloatActionButton() {
        TODO("Criar evento de adicionar partidas")
    }

    private fun setupMatchesRefresh() {
        TODO("Atuallizar as partidas com a função swipe")
    }

    private fun setupMatchesList() {
        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if(response.isSuccessful){
                    val matches : List<Match>
                    matches = response.body()!!
                    Log.i("SIMULADOR", "Deu tudo certo meu patrao!" + matches.size)
                } else{
                    showErrorMessage()
                }
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
            }
        })
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_SHORT).show()
    }
}