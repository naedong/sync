package kr.tr.finance.common.util

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오전 11:11
 */

fun insuranceRegex( originWord : String ) : String {
    val regex = Regex("\\s\\S.[보험].")
    val matchResult = regex.find(originWord)
    val result = matchResult?.value?.trim()
    return result ?: ""
}