package com.example.matchessimulator.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchessimulator.databinding.MatchItemBinding
import com.example.matchessimulator.domain.Match

class MatchesAdapter (private var matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    @SuppressLint("NotConstructor")
    fun MatchesAdapter(matches : List<Match>){
        this.matches = matches
    }

    fun getMatches() : List<Match>{
        return matches
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match: Match = matches.get(position)

        // Adapta os dados da partida (recuperada da API) para o nosso layout
        holder.binding.txtNameHome.setText(match.homeTeam.name)
        holder.binding.txtNameVisitor.setText(match.awayTeam.name)

    }
    override fun getItemCount(): Int {
        return matches.size
    }
}