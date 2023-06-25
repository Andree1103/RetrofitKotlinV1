package com.cibertec.session10app.model

import com.google.gson.annotations.SerializedName

data class ResulApi(

    @SerializedName("codigoMensaje")
    val code:Int,

    @SerializedName("resultadoMensaje")
    val result:String
)
