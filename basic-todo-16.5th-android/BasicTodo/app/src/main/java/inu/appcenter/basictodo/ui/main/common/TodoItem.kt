package inu.appcenter.basictodo.ui.main.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import inu.appcenter.basictodo.model.TodoRes
import inu.appcenter.basictodo.ui.main.MainViewModel
import inu.appcenter.basictodo.ui.theme.fontFamilyFaunaOne
import inu.appcenter.basictodo.ui.theme.fontFamilyKanit

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    todoRes: TodoRes,
    mainViewModel: MainViewModel,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable {
                onClick()
            },
        border = BorderStroke(width = 1.dp, color = Color(0xFFF5F5F5)),
        color = Color.White  // 배경색 지정
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VerticalDivider(
                color = Color(0xFFFBAE17),
                thickness = 6.dp,
                modifier = Modifier
                    .padding(start = 4.dp, top = 5.dp, bottom = 5.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 15.dp, top = 21.dp, bottom = 18.dp, end = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = todoRes.content,
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyKanit,
                        fontWeight = FontWeight(500),
                        color = if (todoRes.isCompleted) Color(0x80000000) else Color.Black,
                        letterSpacing = 0.25.sp,
                        textDecoration = if (todoRes.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                    )
                )
                Text(
                    text = todoRes.deadLine,
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyFaunaOne,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
            TodoCheckBox(
                checked = todoRes.isCompleted,
                onCheckedChange = {
                    mainViewModel.updateTodoCompleted(todoRes)
                },
                modifier = Modifier
                    .padding(end = 16.dp)
            )
        }
    }
}