package com.example.matchessimulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchessimulator.R
import com.example.matchessimulator.data.MatchesApi
import com.example.matchessimulator.databinding.ActivityMainBinding
import com.example.matchessimulator.domain.Match
import com.example.matchessimulator.ui.adapter.MatchesAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var matchesAdapter: MatchesAdapter
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
        binding.fabSimulate.setOnClickListener {
            val random = Random()
            for (i in 0 until matchesAdapter.itemCount) {
                val match: Match = matchesAdapter.getMatches().get(i)
                match.homeTeam.score = random.nextInt(match.homeTeam.stars + 1)
                match.awayTeam.score = random.nextInt(match.awayTeam.stars + 1)
                matchesAdapter.notifyItemChanged(i)
            }
        }
    }

    private fun setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromApi)
    }


    private fun setupMatchesList() {
        binding.rvMatches.setHasFixedSize(true)
        binding.rvMatches.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        matchesAdapter = MatchesAdapter(Collections.emptyList())
        binding.rvMatches.adapter = (matchesAdapter)

        findMatchesFromApi()
    }



    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_SHORT).show()
    }

    private fun findMatchesFromApi() {
        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if(response.isSuccessful){
                    val matches : List<Match> = response.body()!!

                    matchesAdapter = MatchesAdapter(matches)
                    binding.rvMatches.adapter = (matchesAdapter)

                } else{
                    showErrorMessage()
                }
                binding.srlMatches.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
                binding.srlMatches.isRefreshing = false
            }
        })
    }
}