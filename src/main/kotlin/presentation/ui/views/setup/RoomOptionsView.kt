package presentation.ui.views.setup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import utils.Colors
import utils.Fonts

@Composable
fun RoomOptionsView(
    navController: NavController
) {

    var showCreateRoomView by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Dendrite", style = TextStyle(
                        fontFamily = Fonts.EloquiaFontFamily,
                        fontSize = 35.sp,
                        color = Colors.SapphireBlue,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    ) {
        var createRoomView by remember {
            mutableStateOf(true)
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(createRoomView) {
                CreateRoomView(navController) {
                    createRoomView = false
                }
            }

            AnimatedVisibility(!createRoomView) {
                JoinRoomView(navController) {
                    createRoomView = true
                }
            }
        }
    }
}