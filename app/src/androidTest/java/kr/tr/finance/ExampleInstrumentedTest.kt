package kr.tr.finance


import android.content.Context
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.IOException
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kr.tr.finance.data.local.LocalDataStore
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.model.request.Message
import kr.tr.finance.di.DataStoreModule
import kr.tr.finance.di.NetworkModule
import kr.tr.finance.di.RepoModule
import kr.tr.finance.domain.model.InsuranceBodyX
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.presentation.model.MainViewModel
import kr.tr.finance.presentation.view.main.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("kr.tr.finance", appContext.packageName)
    }

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Test
    fun `ViewModelDataInsuranceApiTest`(mainViewModel: MainViewModel) = runBlocking {
        val chatGpt = GptRequest(
            messages = listOf(
                Message(
                    content = "20세 이상 남성 설명이나 다른 말 하지 않고 추천하는 보험 종류 1번만 말해줘"
                )
            )
        )

        assertEquals("", mainViewModel.getInsurance(chatGpt).collect())

    }


}

@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [DataStoreModule::class]
//)
@InstallIn(SingletonComponent::class)
object TestModule {

    private val SET_TEST_KEY = "SET_TEST_KEY"

    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(SET_TEST_KEY)
    }
}

@UninstallModules(DataStoreModule::class)
@HiltAndroidTest
class AndroidTest {


    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private val SET_TEST_KEY = "SET_TEST_KEY"


    @Inject
    lateinit var dataStore: DataStore<Preferences>

    @Before
    fun setup() {
        hiltTestRule.inject()
//        composeTestRule.setContent {
//            composeTestRule.activity.viewModels<MainViewModel>().value
//        }
    }

    @Test
    fun testWriteAndReadData() {
        runBlocking {
            val key = stringPreferencesKey("example_key")
            dataStore.edit { preferences ->
                preferences[key] = "example_value"
            }
            val result = dataStore.data.first()[key]

            assertEquals("example_value", result)
        }
    }

    @Test
    fun `테스트리스트데이터확인`() {
        runBlocking {
            val list =
                InsuranceBodyX(
                    goodList = listOf(
                        InsuranceGood(
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",
                            "12",

                            ),
                    ),
                    kyoboResponse = emptyList(),
                    goodListCnt = emptyList()
                )

            val key = stringPreferencesKey("example_key")
            dataStore.edit { it[key] = Json.encodeToString(list) }

            val getChatData: Flow<InsuranceBodyX> =
                dataStore.data.map { Json.decodeFromString(it[key] ?: "") }
            assertEquals(list, getChatData.first())
        }
    }

    @Test
    fun `뷰에서API호출확인`(): TestResult {
        val chatGpt = GptRequest(
            messages = listOf(
                Message(
                    content = "20세 이상 남성 설명이나 다른 말 하지 않고 추천하는 보험 종류 1번만 말해줘"
                )
            )
        )

        return runBlocking {
            val test =
                composeTestRule.activity.viewModels<MainViewModel>().value.insuranceUseCase.getInsuranceData(
                    chatGpt
                ).collect()

            assertEquals("", test)

        }
    }

}

