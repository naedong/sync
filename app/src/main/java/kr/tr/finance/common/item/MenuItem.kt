package kr.tr.finance.common.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-05
 * Time: 오전 9:41
 */
@Composable
fun MenuItem(menuClickable: MutableState<Boolean>, navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .padding(10.dp),
            horizontalAlignment = Alignment.End
        ) {

            Icon(imageVector = Icons.Default.Close,
                tint = Color.Black,
                modifier = Modifier
                    .clickable {
                        menuClickable.value = menuClickable.value.not()
                    }, contentDescription = null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
        ) {
            MenuListItem(
                NavigationItem.login.title,
                NavigationItem.login.route,
                navController)
            MenuListItem(
                text = "메뉴",
                router = NavigationItem.login.route,
                navController = navController
            )
            MenuListItem(
                text = "아이템",
                router = NavigationItem.login.route,
                navController = navController
            )
            MenuListItem(
                text = "테스트",
                router = NavigationItem.login.route,
                navController = navController
            )
        }
    }
}

@Composable
fun MenuListItem(text: String, router : String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                navController.navigate(router)
            }
            .padding(16.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }


}