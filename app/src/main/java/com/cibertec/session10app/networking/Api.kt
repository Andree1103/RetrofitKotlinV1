package com.cibertec.session10app.networking

import com.cibertec.session10app.model.Pet
import com.cibertec.session10app.model.RegisterPetRequest
import com.cibertec.session10app.model.ResulApi
import com.cibertec.session10app.model.UpdatePetRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

object Api {

    private val builder : Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://cibertec-001-site1.etempurl.com/")
        .addConverterFactory(GsonConverterFactory.create())

    interface RemoteService{

        @GET("api/Mascota")
        suspend  fun getAllPets() : Response<List<Pet>>

        @POST("api/Mascota")
        suspend fun savePets(@Body request:RegisterPetRequest) : Response<ResulApi>

        @PUT("api/Mascota")
        suspend fun updatePets(@Body request: UpdatePetRequest) : Response<ResulApi>

        @DELETE("api/Mascota")
        suspend fun deletePets(@Query("codigo") code : Int) : Response<ResulApi>

        //@GET("api/masctoa")
    }

    fun build() : RemoteService{
        return builder.build().create(RemoteService::class.java)
    }
}