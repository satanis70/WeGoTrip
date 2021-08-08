package example.wegotrip.services

import example.wegotrip.models.Excursion
import example.wegotrip.models.Excursions
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("ce9e3e03b097d584428d")
    suspend fun getStages() : Response<Excursions>

    companion object{
        fun create(): RetrofitService{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.npoint.io/")
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }
}