package hr.tvz.android.listapetermanec.Main.Helper

import hr.tvz.android.listapetermanec.Main.Model.Image
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("/photos/random?client_id=")
    suspend fun getImage(): Response<Image>
}
//eDlpS2Fvc0xlSzZMTWV2dGVOOThkYjdyU19HSzRaRGdfaWpfdGNvR1VGTQ==