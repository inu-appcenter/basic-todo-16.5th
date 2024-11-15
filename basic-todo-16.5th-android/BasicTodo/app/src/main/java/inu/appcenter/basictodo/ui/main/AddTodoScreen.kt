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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import inu.appcenter.basictodo.R
import inu.appcenter.basictodo.ui.main.common.DateUtils
import inu.appcenter.basictodo.ui.theme.fontFamilyKanit
import inu.appcenter.basictodo.ui.theme.fontFamilyRoboto
import java.time.format.DateTimeFormatter

@Composable
fun AddTodoScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {


    var title by remember { mutableStateOf(TextFieldValue("")) }
    var finishDate by remember { mutableStateOf("종료 일자") }


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
    ){
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
//            Text(
//                text = "추가하기",
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    lineHeight = 20.sp,
//                    fontFamily = fontFamilyRoboto,
//                    fontWeight = FontWeight(400),
//                    color = Color(0xFFFFFFFF),
//                    textAlign = TextAlign.Center,
//                    textDecoration = TextDecoration.Underline,
//                ),
//                modifier = Modifier
//                    .padding(end = 40.dp)
//                    .clickable {
//
//                    }
//            )
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
                    text = "New Task",
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
                    modifier = Modifier
                        .fillMaxWidth(),
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
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
            ) {
                Button(
                    onClick = {
                        // TODO: 추가 로직
                        if (title.text.isNotBlank() && finishDate != "종료 일자") {  // 내용과 날짜가 모두 설정되었는지 확인
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            mainViewModel.addTodo(
                                content = title.text,
                                deadLine = finishDate
                            )
                            navController.popBackStack()
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    enabled = title.text.isNotBlank() && finishDate != "종료 일자",  // 조건 추가
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
                        text = "추가",
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier,
    selectDate: (String) -> Unit,
    finishDate: String,
) {
    val dateState = rememberDatePickerState()
    val formattedDate = dateState.selectedDateMillis?.let {
        val localDate = DateUtils().convertMillisToLocalDate(it)
        localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    } ?: "종료 일자"

    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .height(50.dp)
            .background(color = Color.White, shape = RoundedCornerShape(5.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = finishDate,  // uiState.selectedDate 대신 finishDate 사용
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = fontFamilyKanit,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000).copy(alpha = if (finishDate == "종료 일자") 0.5f else 1f),
                    letterSpacing = 0.25.sp,
                )
            )
            IconButton(
                onClick = {
                    showDialog = true
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = "달력",
                    tint = Color(0xFF2E2E2E)
                )
            }
        }

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            formattedDate.let {
                                selectDate(it)
                            }
                        }
                    ) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                },
            ) {
                DatePicker(
                    state = dateState,
                    showModeToggle = true
                )
            }
        }
    }
}