package kr.tr.finance.common.item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kr.tr.finance.R

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 11:21
 */
sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String ) {
    object home : NavigationItem("home",  Icons.Default.Home,"금융")
    object login : NavigationItem("login",  Icons.Default.AccountCircle,"로그인")

}