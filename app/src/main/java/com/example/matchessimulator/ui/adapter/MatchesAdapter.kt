package com.example.matchessimulator.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchessimulator.databinding.MatchItemBinding
import com.example.matchessimulator.domain.Match
import com.example.matchessimulator.ui.DetailActivity
import okhttp3.internal.Internal

class MatchesAdapter (private var matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    fun getMatches() : List<Match>{
        return matches
    }

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
        match.homeTeam.score?.let { holder.binding.txtScoreHome.text = it.toString() }
        match.awayTeam.score?.let { holder.binding.txtScoreVisitor.text = it.toString() }
        holder.binding.txtNameHome.text = match.homeTeam.name
        holder.binding.txtNameVisitor.text = match.awayTeam.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.Extras.MATCH, match)
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return matches.size
    }
}