package kr.tr.finance

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import kr.tr.finance.data.local.LocalDataStore
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.model.request.Message
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.domain.model.UserModel
import kr.tr.finance.domain.model.UserResult
import kr.tr.finance.presentation.model.MainViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `API CHECK`() {
        val client = OkHttpClient()
        val resu = UserResult(
            result =
            UserModel(
                id = "test",
                password = "test"
            )
        )

        val apiUrl = "${BuildConfig.URL_BASE}/user/sign"
        val requestBody =
            RequestBody.create("application/json".toMediaTypeOrNull(), resu.result.toString())


        val request = Request.Builder()
            .url(apiUrl)
            .post(
                body = requestBody
            )
            .build()
        try {

            val response = client.newCall(request).execute()

            val responsebodys = response.body?.string()

            if (response != null) {
                // user 객체를 사용하여 데이터를 처리합니다.
                assertEquals("{\"result\":{\"id\":null,\"password\":null}}", responsebodys)
            } else {
                // 역직렬화에 실패한 경우
                assertEquals("2", responsebodys)
            }
        } catch (e: Exception) {
            // 네트워크 오류 또는 예외 처리
            // 오류 처리
            e.printStackTrace()
        }


        val response = client.newCall(request).execute()

//        // 응답 본문을 문자열로 읽기
//        val responseBody = response.body?.string()
//
//            if(response.isSuccessful){
//                assertEquals(resu, responseBody)
//            } else {
//                Log.e("TEST", "$responseBody")
//            }

        // 응답 출력
        response.close()
        // 데이터 호출 성공 확인
    }

    @Test
    fun `Regex 테스트`() {
        val regex = Regex("\\s\\S.[보험].")

        val word =
            "20세 이상의 남성을 위한 보험 종류는 여러 가지가 있으며 개별적인 필요에 따라 다를 수 있습니다. 일반적으로 추천할 수 있는 보험 종류는 다음과 같습니다:\n" +
                    "\n" +
                    "건강보험: 건강 상태를 보호하고 의료 비용을 충당하는데 도움을 줍니다. 건강보험은 의료 검사, 의약품, 의사 진료 및 입원 치료를 포함할 수 있습니다.\n"

        val matchResult = regex.find(word)
        val answer = "건강보험"

        assertEquals(answer, matchResult?.value?.trim())

        val word1 = "20세 이상의 여성을 위한 보험 중 하나로 생명보험을 추천드립니다.\n" +
                "\n" +
                "생명보험은 여성 또한 금전적 보호를 받을 수 있는 중요한 보험 종류 중 하나입니다. 여성이 생명보험을 가입하면 가족이나 혜택 수취자가 예상치 못한 상황에서 금전적으로 보호받을 수 있습니다. 이를 통해 가족이 급여 및 생계를 유지하거나 중대한 재정적 부담을 피할 수 있습니다. 생명보험의 종류와 금액은 개별 상황과 필요에 따라 다를 수 있으므로, 가족 구성, 금전적 상태, 생활비, 부채 상황 등을 고려하여 적절한 생명보험 상품을 선택하는 것이 중요합니다."

        val matchResult1 = regex.find(word1)
        val answer1 = "생명보험"
        val result = matchResult1?.value?.trim()
        assertEquals(answer1, result)

        val word2 = "  생명보험"

        val matchResult2 = regex.find(word2)
        val answer2 = "생명보험"
        val result1 = matchResult2?.value?.trim()
        assertEquals(answer2, result1)

    }

    class FakeInsuranceUseCase {

    }
//    @get:Rule
//     val InstantTaskExecutorRul = CoroutineR


    @Test
    fun `Insurance Api 데이터가 정상적으로 들어오는가 테스트 `() {


    }

    @Test
    fun `ViewModelDataInsuranceApiTestResult`(mainViewModel: MainViewModel): TestResult {
        val chatGpt = GptRequest(
            messages = listOf(
                Message(
                    content = "20세 이상 남성 설명이나 다른 말 하지 않고 추천하는 보험 종류 1번만 말해줘"
                )
            )
        )
        return runTest {

            assertEquals("", mainViewModel.getInsurance(chatGpt).collect())
            mainViewModel.getInsurance(chatGpt).collect()
        }
    }

    private val stringKey = "key"



    @Test
    fun `로컬데이터저장및전달확인`(dataStore: DataStore<Preferences>) {
        val store = LocalDataStore(dataStore)

        val localData = listOf(
            InsuranceGood(
                assrNm = "afafsa",
                "dada",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
            )
        )

        runBlocking {
            val testData =
                store.setChatData(localData)
            assertEquals("", testData)
        }


        val getData = store.getChatData
        assertEquals("111", getData)

    }


}
