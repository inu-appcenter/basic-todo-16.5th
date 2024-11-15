package inu.appcenter.basictodo.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import inu.appcenter.basictodo.navigate.AllDestination
import inu.appcenter.basictodo.ui.main.common.TodoItem
import inu.appcenter.basictodo.ui.theme.fontFamilyKanit

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navController: NavController
){
    val uiState by mainViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF034A9A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column{
                Text(
                    text = "Today",
                    style = TextStyle(
                        fontSize = 36.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyKanit,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 0.25.sp,
                    )
                )
                Spacer(Modifier.height(25.dp))
                Text(
                    text = "${uiState.todoRes.count { !it.isCompleted }} tasks",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyKanit,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 0.25.sp,
                    )
                )

            }

            Button(
                onClick = {
                    navController.navigate(AllDestination.AddTodo.route)
                },
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
                )
            ) {
                Text(
                    text = "Add New",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontFamily = fontFamilyKanit,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
        }
        Spacer(Modifier.height(39.dp))
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 60.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 84.dp, start = 28.dp, end = 28.dp)
            ) {
                if (uiState.todoRes.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 150.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(AllDestination.AddTodo.route)
                            },
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 50.dp, vertical = 15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFBAE17),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Add New",
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = fontFamilyKanit,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 0.25.sp,
                                )
                            )
                        }
                    }
                } else {
                    LazyColumn{
                        items(uiState.todoRes){ todo ->
                            TodoItem(
                                modifier = Modifier,
                                todoRes = todo ,
                                mainViewModel = mainViewModel,
                                onClick = {
                                    mainViewModel.selectTodo(todo.memberId)
                                    navController.navigate(AllDestination.EditTodo.route)
                                }
                            )
                            Spacer(Modifier.height(16.dp))
                        }
                        item { Spacer(Modifier.height(100.dp)) }
                    }
                }

            }

        }
    }

}