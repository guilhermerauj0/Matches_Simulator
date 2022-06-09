package com.example.matchessimulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Place(
    @SerializedName("nome")
    private val name: String,
    @SerializedName("imagem")
    private val image: String
        ) : Parcelable