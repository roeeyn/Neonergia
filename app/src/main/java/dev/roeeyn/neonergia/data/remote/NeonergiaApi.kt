package dev.roeeyn.neonergia.data.remote

import com.google.gson.GsonBuilder
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
import dev.roeeyn.neonergia.utils.AppConstants.FIREBASE_URL
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface NeonergiaApi {

    @POST("entry")
    fun postDemoEntry(@Body demoUser: DeviceDemoResponse): Single<DeviceDemoResponse>

    companion object {
        fun create(): NeonergiaApi {

            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor {
                    val original = it.request()
                    val requestBuilder = original.newBuilder().method(original.method(), original.body())
                    val request = requestBuilder.build()
                    it.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(FIREBASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NeonergiaApi::class.java)
        }
    }
}