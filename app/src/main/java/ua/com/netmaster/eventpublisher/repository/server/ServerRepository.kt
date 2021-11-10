package ua.com.netmaster.eventpublisher.repository.server

import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Path
import ua.com.netmaster.eventpublisher.model.Event
import ua.com.netmaster.eventpublisher.model.ResponseMock


class ServerRepository {

    private var API_BASE_URL = "https://api.github.com/"

    private var httpClient = OkHttpClient.Builder()

    private var builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private var retrofit: Retrofit = builder
        .client(httpClient.build())
        .build()

    var client: EventApi = retrofit.create(EventApi::class.java)

    suspend fun post(event: Event): Response<ResponseMock> {
        // emulate long response
        delay(5000)
        return client.post(event.subject, event.payload)
    }
}

interface EventApi {

    @POST("publish/{subject}/{payload}")
    suspend fun post(
        @Path("subject") subject: String,
        @Path("payload") payload: String
    ): Response<ResponseMock>

}