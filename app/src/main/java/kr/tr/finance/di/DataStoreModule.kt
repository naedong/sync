package kr.tr.finance.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-16(016)
 * Time: 오전 10:08
 */
private const val DATA_STORE_NAME = "DATA_STORE_NAME"

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext context : Context
    ) : DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(DATA_STORE_NAME)
    }

}