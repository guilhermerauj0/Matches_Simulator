package com.example.matchessimulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    @SerializedName("descricao") // Função para atribuir os valores do json no nosso código
    var description: String,
    @SerializedName("local")
    var place: Place,
    @SerializedName("mandante")
    var homeTeam: Team,
    @SerializedName("visitante")
    var awayTeam: Team
) : Parcelable
