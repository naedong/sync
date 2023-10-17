package kr.tr.finance.data.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.model.request.Message
import kr.tr.finance.domain.model.InsuranceBodyX
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.domain.model.InsuranceGoodCnt
import kr.tr.finance.domain.model.InsuranceKyoboReseponse
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-16(016)
 * Time: 오전 10:12
 */
class LocalDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object DataStoreKeys {
        const val CHAT_DATA_KEY = "CHAT_DATA_KEY"
        const val DATA_KEY = "DATA_KEY"
        val chatDataKey = stringPreferencesKey(CHAT_DATA_KEY)
        val dataKey = stringPreferencesKey(DATA_KEY)
    }
    suspend fun setChatData(name: InsuranceBodyX) {
        dataStore.edit { it[DataStoreKeys.chatDataKey] = Json.encodeToString(name) } }

    val getChatData : Flow<InsuranceBodyX> = dataStore.data.map {
        val stringKey = it[DataStoreKeys.chatDataKey] ?: "" +
//        "${Json.encodeToString(InsuranceBodyX(
//            listOf(InsuranceKyoboReseponse("","","","","") ), listOf(
//                InsuranceGood("","","","","","","","","","","",
//                    "","","","","","","","","","","",)
//            ), listOf( InsuranceGoodCnt("",)))) } " +
        ""
        Json.decodeFromString(stringKey)
    }


    suspend fun clearData() {
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun setRequestData(name : GptRequest) { dataStore.edit { it[DataStoreKeys.dataKey] = name.messages?.first()?.content ?: ""}}
    val getRequestData : Flow<String> = dataStore.data.map {  it[DataStoreKeys.dataKey] ?: "" }



}