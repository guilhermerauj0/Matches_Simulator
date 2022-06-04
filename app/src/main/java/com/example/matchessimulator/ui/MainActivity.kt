package com.example.matchessimulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.matchessimulator.databinding.ActivityMainBinding
import com.example.matchessimulator.domain.Team

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMatchesList()
        setupMatchesRefresh()
        setupFloatActionButton()
    }

    private fun setupFloatActionButton() {
        TODO("Criar evento de adicionar partidas")
    }

    private fun setupMatchesRefresh() {
        TODO("Atuallizar as partidas com a função swipe")
    }

    private fun setupMatchesList() {
        TODO("Listar as partidas")
    }
}