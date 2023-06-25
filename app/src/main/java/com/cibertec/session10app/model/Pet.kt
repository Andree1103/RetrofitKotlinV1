package com.cibertec.session10app.model

import com.google.gson.annotations.SerializedName

data class Pet(

    @SerializedName("codigo")
    val code:Int,
    @SerializedName("cliente")
    val customer:String,
    @SerializedName("mascota")
    val petName:String,
    @SerializedName("edad")
    val age:Int
)
