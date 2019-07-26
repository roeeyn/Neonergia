package dev.roeeyn.neonergia

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

object DeviceServiceFactory {
    fun createService(): UserApiService {

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
            .baseUrl("https://us-central1-neoenergia3-a687d.cloudfunctions.net/devices/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }

}

interface UserApiService {
    @POST("entry")
    fun postDemoEntry(@Body demoUser: DeviceDemoResponse): Single<DeviceDemoResponse>
}
