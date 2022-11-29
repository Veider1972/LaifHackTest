package ru.veider.lifehacktest.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.veider.lifehacktest.entities.CompaniesData
import ru.veider.lifehacktest.entities.CompanyData
import java.io.IOException

const val WEB_PATH = "https://lifehack.studio/test_task/"

interface ApiService {

    companion object {
        var apiService: ApiService? = null

        var gson: Gson = GsonBuilder()
            .serializeNulls()
            .create()

        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(WEB_PATH)
                    .addConverterFactory(GsonConverterFactory.create(
                        gson
                    ))
                    .client(createOkHttpClient(DebugInterceptor()))
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }

        private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            return okHttpClient.build()
        }

        class DebugInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d("TAG", chain.request().toString())
                return chain.proceed(chain.request())
            }
        }
    }

    @GET("test.php")
    suspend fun getCompanies():List<CompaniesData>

    @GET("test.php")
    suspend fun getCompany(
        @Query("id") id: Int
    ): List<CompanyData>
}