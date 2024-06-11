package presentation.ui.views.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import presentation.ui.nav.Screens
import presentation.viewmodel.ChatViewModel
import utils.Colors
import utils.CurrentUser
import utils.Fonts
import utils.ViewModelProvider

@Composable
fun JoinRoomView(
    navController: NavController,
    onStartRoomClick: () -> Unit
) {
    var roomID by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 25.dp),
            text = "Join Room", style = TextStyle(
                fontFamily = Fonts.EloquiaFontFamily,
                fontSize = 100.sp,
                color = Colors.SapphireBlue,
                fontWeight = FontWeight.Bold
            )
        )

        Card(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(650.dp)
                .height(100.dp)
                // Move the shadow slightly to the right and down
//                .border(5.dp, Colors.ReceiverMessage, RoundedCornerShape(35.dp))
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(35.dp),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
        ) {
            OutlinedTextField(
                value = roomID,
                onValueChange = {
                    roomID = it

                },
                placeholder = {
                    Text(
                        "enter room id", style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Gray
                        )
                    )
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(35.dp),
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        backgroundColor = Color.Transparent,
                        unfocusedLabelColor = Color(0xFFB1B1B1),
                        focusedLabelColor = Color.Black,
                        focusedBorderColor = Colors.SenderMessage
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
                    ),
                modifier = Modifier
                    .fillMaxSize()
            )

        }


        Button(
            modifier = Modifier
                .padding(bottom = 15.dp),
            onClick = {
                CurrentUser.name="Aarsh"
                CurrentUser.roomName="Room"
                coroutineScope.launch(Dispatchers.IO) {
                    ViewModelProvider.ChatViewModel = KoinJavaComponent.get(ChatViewModel::class.java)
                    ViewModelProvider.ChatViewModel.setUpViewModel("localhost", 8080)

                    navController.navigate(Screens.Chat.route)
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Colors.DarkBlue
            ),
            shape = RoundedCornerShape(23.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 5.dp, bottom = 5.dp),
                text = "Join", style = TextStyle(
                    fontFamily = Fonts.InterFontFamily,
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(150.dp).height(3.dp).background(Colors.Gray))

            Text(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                text = "or", style = TextStyle(
                    fontFamily = Fonts.InterFontFamily,
                    fontSize = 23.sp,
                    color = Colors.DarkBlue,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.width(150.dp).height(3.dp).background(Colors.Gray))
        }


        Button(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .border(1.dp, Colors.DarkBlue, RoundedCornerShape(23.dp)),
            onClick = {
                onStartRoomClick()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            shape = RoundedCornerShape(23.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 5.dp, bottom = 5.dp),
                text = "Create Room", style = TextStyle(
                    fontFamily = Fonts.InterFontFamily,
                    fontSize = 30.sp,
                    color = Colors.DarkBlue,
                    fontWeight = FontWeight.Bold
                )
            )
        }


        Text(
            modifier = Modifier.padding(15.dp),
            text = "Note: All other devices must be connected to the same network as this device to join the room.",
            style = TextStyle(
                fontFamily = Fonts.InterFontFamily,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            )
        )

    }
}