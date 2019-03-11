package dsm.android.v3.connecter

import com.google.gson.JsonObject
import dsm.android.v3.model.MealModel
import dsm.android.v3.ui.musicApply.MusicApplyModel
import retrofit2.Call
import retrofit2.http.*

interface API {
    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<MealModel>

    @POST("/apply/music")
    @Headers("Content-Type: application/json")
    fun postMusic(@Header("Authorization") token: String, @Body body: JsonObject): Call<Void>

    @GET("/apply/music")
    @Headers("Content-Type: application/json")
    fun getMusic(@Header("Authorization") token: String): Call<MusicApplyModel>

    @HTTP(method = "DELETE", path = "/apply/music", hasBody = true)
    @Headers("Content-Type: application/json")
    fun deleteMusic(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>
}