package kr.tr.finance.common.item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 11:09
 */
@Composable
fun CustomListItem(subject: String, supprting : String) {
    androidx.compose.material3.ListItem(
        headlineContent = { Text("$subject") },
        supportingContent = {
            Text("$supprting")
        },
        leadingContent = {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
            )
        },
        trailingContent = { Text("회사 정보") }
    )

}