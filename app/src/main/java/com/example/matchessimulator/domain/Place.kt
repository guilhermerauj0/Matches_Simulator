package com.example.matchessimulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Place(
    @SerializedName("nome") internal val name: String,
    @SerializedName("imagem") internal val image: String
        ) : Parcelable