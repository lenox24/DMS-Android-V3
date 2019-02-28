package dsm.android.v3.connecter

import com.google.gson.JsonObject
import dsm.android.v3.model.MealModel
import retrofit2.Call
import retrofit2.http.*

interface API {
    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<MealModel>

    @POST("/apply/music")
    fun postMusic(@Header("Authorization") token: String, @Body body: JsonObject): Call<Void>

    @GET("/apply/music")
    fun getMusic(@Header("Authorization") token: String): Call<JsonObject>

    @DELETE("/apply/music")
    fun deleteMusic(@Header("Authorization") id: Int): Call<Void>
}