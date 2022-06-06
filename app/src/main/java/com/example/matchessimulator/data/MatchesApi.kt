package com.example.matchessimulator.data

import com.example.matchessimulator.domain.Match
import retrofit2.Call
import retrofit2.http.GET

interface MatchesApi {

    @GET("matches.json")
    fun getMatches(): Call<List<Match>>
}