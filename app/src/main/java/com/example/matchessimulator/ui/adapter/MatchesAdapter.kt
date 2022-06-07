package com.example.matchessimulator.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchessimulator.databinding.MatchItemBinding
import com.example.matchessimulator.domain.Match

class MatchesAdapter (private var matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    /*
    @SuppressLint("NotConstructor")
    fun MatchesAdapter(matches : List<Match>){
        this.matches = matches
    }

    fun getMatches() : List<Match>{
        return matches
    }

     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context: Context = holder.itemView.context
        val match: Match = matches.get(position)


        // ADD IMAGE FOR MATCHES
        Glide.with(context).load(match.homeTeam.image).circleCrop().into(holder.binding.imgTeamHome)
        Glide.with(context).load(match.awayTeam.image).circleCrop().into(holder.binding.imgTeamVisitor)

        // Adapta os dados da partida (recuperada da API) para o nosso layout
        holder.binding.txtNameHome.setText(match.homeTeam.name)
        holder.binding.txtNameVisitor.setText(match.awayTeam.name)

    }
    override fun getItemCount(): Int {
        return matches.size
    }
}