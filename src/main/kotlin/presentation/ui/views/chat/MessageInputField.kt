//package presentation.ui.views.chat
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.BitmapPainter
//import androidx.compose.ui.res.loadImageBitmap
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import utils.Colors
//import utils.Fonts
//import utils.ImagePath
//import java.io.File
//
//@Composable
//fun MessageInputField() {
//    var message by remember {
//        mutableStateOf("")
//    }
//
//    BottomAppBar(
//        modifier = Modifier.border(0.5.dp, Color.Black),
//        backgroundColor = Color.White
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(min = 75.dp)
//                .padding(5.dp)
//                .border(0.5.dp, Colors.SenderMessage, RoundedCornerShape(10.dp))
//                .background(Colors.SendMessageTextField, RoundedCornerShape(10.dp)),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                modifier = Modifier
//                    .padding(start = 5.dp)
//                    .size(24.dp),
//                painter = BitmapPainter(
//                    loadImageBitmap(
//                        File(ImagePath.EMOJI_SELECTOR).inputStream()
//                    )
//                ),
//                contentDescription = "EMOJI_SELECTOR",
//            )
//
//            Spacer(modifier = Modifier.width(10.dp))
//
//            OutlinedTextField(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(horizontal = 10.dp),
//                value = message,
//                onValueChange = { message = it },
//                placeholder = {
//                    Text(
//                        text = "Enter Message...",
//                        style = TextStyle(
//                            fontFamily = Fonts.EloquiaFontFamily,
//                            fontSize = 15.sp,
//                            color = Color.Gray,
//                            fontWeight = FontWeight.Light
//                        )
//                    )
//                },
//                textStyle = TextStyle(
//                    fontFamily = Fonts.EloquiaFontFamily,
//                    fontSize = 20.sp,
//                    color = Color.Black,
//                    fontWeight = FontWeight.Medium,
//                    textAlign = TextAlign.Start
//                ),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    backgroundColor = Color.Transparent,
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                    disabledBorderColor = Color.Transparent
//                ),
//                singleLine = false,
//                maxLines = Int.MAX_VALUE // Allows unlimited lines
//            )
//
//            Spacer(modifier = Modifier.width(10.dp))
//
//            Image(
//                modifier = Modifier
//                    .padding(end = 5.dp)
//                    .size(24.dp),
//                painter = BitmapPainter(
//                    loadImageBitmap(
//                        File(ImagePath.SEND_MESSAGE).inputStream()
//                    )
//                ),
//                contentDescription = "SEND_MESSAGE",
//            )
//        }
//    }
//}


package presentation.ui.views.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import di.MyKoinComponent
import utils.Colors
import utils.Fonts
import utils.ImagePath
import java.io.File

@Composable
fun MessageInputField(
    koinComponent: MyKoinComponent
) {
    var message by remember {
        mutableStateOf("")
    }

    BottomAppBar(
        modifier = Modifier.border(0.5.dp, Color.Black),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 75.dp)
                .padding(5.dp)
                .border(0.5.dp, Colors.SenderMessage, RoundedCornerShape(10.dp))
                .background(Colors.SendMessageTextField, RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(24.dp),
                painter = BitmapPainter(
                    loadImageBitmap(
                        File(ImagePath.EMOJI_SELECTOR).inputStream()
                    )
                ),
                contentDescription = "EMOJI_SELECTOR",
            )

            Spacer(modifier = Modifier.width(10.dp))

            BasicTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .background(Color.Transparent)
                    .onKeyEvent {
                        if (it.key == Key.Enter) {
                            koinComponent.chatViewModel.sendMessage(message)
                            message=""
                            return@onKeyEvent true
                        }
                        false
                    },
                textStyle = TextStyle(
                    fontFamily = Fonts.EloquiaFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        if (message.isEmpty()) {
                            Text(
                                text = "Enter Message...",
                                style = TextStyle(
                                    fontFamily = Fonts.EloquiaFontFamily,
                                    fontSize = 20.sp,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Light
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Image(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .size(24.dp),
                painter = BitmapPainter(
                    loadImageBitmap(
                        File(ImagePath.SEND_MESSAGE).inputStream()
                    )
                ),
                contentDescription = "SEND_MESSAGE",
            )
        }
    }
}