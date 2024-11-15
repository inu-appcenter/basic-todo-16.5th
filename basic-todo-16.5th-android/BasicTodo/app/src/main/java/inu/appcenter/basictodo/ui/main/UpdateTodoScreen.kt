package inu.appcenter.basictodo.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import inu.appcenter.basictodo.ui.theme.fontFamilyKanit
import inu.appcenter.basictodo.ui.theme.fontFamilyRoboto

@Composable
fun UpdateTodoScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val uiState by mainViewModel.uiState.collectAsState()
    val selectedTodo = uiState.selectedTodoRes

    // selectedTodo가 null이면 이전 화면으로 돌아감
    if (selectedTodo == null) {
        navController.popBackStack()
        return
    }

    var title by remember { mutableStateOf(TextFieldValue(selectedTodo.content)) }
    var finishDate by remember { mutableStateOf(selectedTodo.deadLine) }
    var isKeyboardOpen by remember { mutableStateOf(false) }

    // 키보드 가시성 감지
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // 시스템 뒤로가기 처리
    BackHandler {
        if (isKeyboardOpen) {
            keyboardController?.hide()
            focusManager.clearFocus()
            isKeyboardOpen = false
        } else {
            navController.popBackStack()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF034A9A))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "뒤로가기",
                        tint = Color.White
                    )
                }
                Text(
                    text = "삭제하기",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                    ),
                    modifier = Modifier
                        .padding(end = 40.dp)
                        .clickable {
                            mainViewModel.deleteTodo(selectedTodoRes = selectedTodo)
                            navController.popBackStack()
                        }
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 45.dp)
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Task",
                    style = TextStyle(
                        fontSize = 26.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyKanit,
                        fontWeight = FontWeight(500),
                        color = Color.White,
                        letterSpacing = 0.25.sp,
                    )
                )
                Spacer(Modifier.height(35.dp))
                TextField(
                    value = title.text,
                    onValueChange = {
                        title = title.copy(it)
                        isKeyboardOpen = true
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp)
                        .onFocusChanged { focusState ->
                            isKeyboardOpen = focusState.isFocused
                        },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "할일을 작성해주세요",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                fontFamily = fontFamilyKanit,
                                fontWeight = FontWeight(500),
                                color = Color(0x80000000),
                                letterSpacing = 0.25.sp,
                            )
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyKanit,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        letterSpacing = 0.25.sp,
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(Modifier.height(30.dp))
                DatePickerWithDialog(
                    mainViewModel = mainViewModel,
                    modifier = Modifier.fillMaxWidth(),
                    selectDate = {
                        finishDate = it
                    },
                    finishDate = finishDate
                )
            }
        }
        AnimatedVisibility(
            visible = isKeyboardOpen,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .imePadding(),
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
            ) {
                Button(
                    onClick = {
                        if (title.text != selectedTodo.content || finishDate != selectedTodo.deadLine) {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            mainViewModel.updateTodo(
                                selectedTodoRes = selectedTodo,
                                content = title.text,
                                deadLine = finishDate,
                            )
                            navController.popBackStack()
                        } else {
                            // 변경사항이 없으면 그냥 뒤로가기
                            navController.popBackStack()
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    enabled = title.text.isNotBlank() &&
                            (title.text != selectedTodo.content || finishDate != selectedTodo.deadLine),  // 조건 추가
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF034A9A),
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFF034A9A).copy(alpha = 0.5f),  // 비활성화 색상
                        disabledContentColor = Color.White.copy(alpha = 0.5f)  // 비활성화 텍스트 색상
                    ),
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 100.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(
                        text = "수정",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 20.sp,
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.25.sp,
                        )
                    )
                }
            }
        }
    }
}