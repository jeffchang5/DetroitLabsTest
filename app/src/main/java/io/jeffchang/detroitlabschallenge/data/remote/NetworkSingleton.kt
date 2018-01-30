package io.jeffchang.detroitlabschallenge.data.remote


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient



/**
 * Created by jeffreychang on 1/30/18.
 */
class NetworkSingleton private constructor(url: String) {

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(url)
            .build()

    fun <T> getRetrofitService(service: Class<T>): T {
        return retrofit.create(service)
    }
    companion object {
        private var instance: NetworkSingleton? = null

        fun getInstance(url: String): NetworkSingleton {
            if (instance == null)
                instance = NetworkSingleton(url)
            return instance!!
        }
    }
}