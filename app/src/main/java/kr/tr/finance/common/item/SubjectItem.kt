package kr.tr.finance.common.item

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 11:07
 */
@Composable
fun SubjectItem(sub : String) {
    Text(
        modifier = Modifier
            .padding(15.dp),
        text = sub,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    )
}