package com.cibertec.session10app.model

data class RegisterPetRequest(
    val cliente:String,
    val mascota:String,
    val edad:Int
)
