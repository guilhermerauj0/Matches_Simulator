package com.example.matchessimulator.domain

import com.google.gson.annotations.SerializedName

public class Place (
    @SerializedName("nome")
    private val name: String,
    @SerializedName("imagem")
    private val image: String
        ){
}