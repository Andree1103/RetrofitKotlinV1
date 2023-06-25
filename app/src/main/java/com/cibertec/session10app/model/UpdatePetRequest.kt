package com.cibertec.session10app.model

data class UpdatePetRequest(
    val codigo:Int,
    val cliente:String,
    val mascota:String,
    val edad:Int
)
