package edu.ucne.anderson_nunez_ap2_p2.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.anderson_nunez_ap2_p2.JugadorApi
import edu.ucne.anderson_nunez_ap2_p2.data.remote.remotedatasource.JugadorRemoteDataSource
import edu.ucne.anderson_nunez_ap2_p2.data.repository.JugadorRepositoryImpl
import edu.ucne.anderson_nunez_ap2_p2.domain.repository.JugadorRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): JugadorApi =
        Retrofit.Builder()
            .baseUrl("https://gestionhuacalesapi.azurewebsites.net/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(JugadorApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: JugadorApi): JugadorRemoteDataSource =
        JugadorRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: JugadorRemoteDataSource): JugadorRepository =
        JugadorRepositoryImpl(remoteDataSource)
}