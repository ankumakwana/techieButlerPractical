package com.example.techiebutlerpractical.koin

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.techiebutlerpractical.database.AppDatabase
import com.example.techiebutlerpractical.database.PostDao
import com.example.techiebutlerpractical.repository.DatabaseRepository
import com.example.techiebutlerpractical.repository.PostsRepository
import com.example.techiebutlerpractical.utils.Api
import com.example.techiebutlerpractical.utils.URLFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApplicationModule {
    val appModule : Module = module {
        single { provideRetrofit() }
        single { provideRepository() }
        single { provideDatabaseRepository() }
        single { provideApi(get<Retrofit>()) }
        single { provideGson() }
        single {
            Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                "post_database"
            ).build()
        }

        single { get<AppDatabase>().postDao() }

    }

    private fun provideRetrofit(): Retrofit {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(80, TimeUnit.SECONDS)
            .writeTimeout(80, TimeUnit.SECONDS)
            .connectTimeout(80, TimeUnit.SECONDS)

        try {
            builder .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder =
                        originalRequest.newBuilder()
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }

        }catch (e: IOException){
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        val client = builder.build()

        return Retrofit.Builder().baseUrl(URLFactory.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }
    private fun provideRepository(): PostsRepository = PostsRepository()
    private fun provideDatabaseRepository(): DatabaseRepository = DatabaseRepository()
    private fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
    private fun provideGson():Gson=Gson()


}