package presentation.ui.views.chat

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import utils.Avatars
import utils.Colors
import utils.Fonts
import utils.ImagePath
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
@Preview
fun MainChatView(navController: NavController) {
//    val connectedUsersCount by ViewModelProvider.ChatViewModel.connectedUsersCount.collectAsState(initial = 0)
//    val chatList by ViewModelProvider.ChatViewModel.chatsList.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            Column(
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {

            }

            Column(
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {

            }
        }
    }
}


@Composable
@Preview
fun MainChatViewPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
                    .fillMaxHeight()
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.border(
                                (0.5).dp, Color.Black
                            ),
                            elevation = 0.dp,
                            contentColor = Color.Transparent,
                            backgroundColor = Color.Transparent
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Room Name", style = TextStyle(
                                        fontFamily = Fonts.EloquiaFontFamily,
                                        fontSize = 30.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    },
                    bottomBar = {
                        MessageInputField()
                    }
                ) { it ->

                    Column(
                        modifier = Modifier.padding(it.calculateTopPadding())
                            .fillMaxWidth()
                    ) {
                        AnotherSenderView("Hello Guys!!!", LocalDateTime.now())
                        ReceiverView("Dave", Avatars.avatarList[0], "Hi Guys", LocalDateTime.now())
                        AnotherSenderView("Hello Guys!!!", LocalDateTime.now())
                        ReceiverView("Dave", Avatars.avatarList[0], "Hi Guys", LocalDateTime.now())
                        AnotherSenderView("Hello Guys!!!", LocalDateTime.now())
                        ReceiverView("Dave", Avatars.avatarList[0], "Hi Guys", LocalDateTime.now())
                        AnotherSenderView(
                            "I am fine guys what about and and what's " +
                                    "happing around I am fine guys what about and " +
                                    "and what's happing around ys what about and hello",
                            LocalDateTime.now()
                        )

                    }

                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(0.2f)
                    .fillMaxHeight()
            ) {

            }
        }
    }
}

//@Preview
//@Composable
//fun SenderView(
//    message: String, time: LocalDateTime
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentWidth(Alignment.End),
//        backgroundColor = Colors.SenderMessage,
//        shape = RoundedCornerShape(
//            topStart = 20.dp,
//            topEnd = 0.dp,
//            bottomStart = 20.dp,
//            bottomEnd = 20.dp
//        )
//    ) {
//        Box(
//            modifier = Modifier
//                .wrapContentHeight()
//                .widthIn(max = 490.dp)
//                .heightIn(min = 60.dp)
//        ) {
//            Row(
//                modifier = Modifier.wrapContentHeight()
//                    .wrapContentWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                Text(
//                    modifier = Modifier.padding(10.dp),
//                    text = message,
//                    style = TextStyle(
//                        fontFamily = Fonts.InterFontFamily,
//                        fontSize = 20.sp,
//                        color = Color.White,
//                        fontWeight = FontWeight.Medium
//                    )
//                )
//
//                Text(
//                    modifier = Modifier
////                       .wrapContentWidth(Alignment.End)
//                        .align(Alignment.Bottom)
//                        .padding(
//                            end = 5.dp, top = 10.dp,
//                        ),
//                    text = formatLocalDateTime(time),
//                    style = TextStyle(
//                        fontFamily = Fonts.InterFontFamily,
//                        fontSize = 10.sp,
//                        color = Color.White,
//                        fontWeight = FontWeight.Light,
//                        textAlign = TextAlign.End
//                    )
//                )
//            }
//        }
//    }
//}

@Composable
fun AnotherSenderView(
    message: String, time: LocalDateTime
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier
                .background(
                    Colors.SenderMessage,
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 0.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .widthIn(max = 490.dp)
                .heightIn(min = 60.dp)
                .padding(10.dp)
        ) {
            Text(
                text = message,
                style = TextStyle(
                    fontFamily = Fonts.InterFontFamily,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = formatLocalDateTime(time),
                style = TextStyle(
                    fontFamily = Fonts.InterFontFamily,
                    fontSize = 10.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview
@Composable
fun ReceiverView(
    senderName: String,
    senderAvatar: String,
    message: String,
    time: LocalDateTime
) {
    Row(
        modifier = Modifier.wrapContentWidth(Alignment.Start)
    ) {

        Image(
            painter = BitmapPainter(
                loadImageBitmap(
                    File(senderAvatar).inputStream()
                )
            ),
            contentDescription = "Avatar",
            modifier = Modifier.size(50.dp)
                .padding(start = 10.dp, end = 5.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .background(
                        Colors.ReceiverMessage,
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .widthIn(max = 490.dp, min = 250.dp)
                    .heightIn(min = 60.dp)
                    .padding(10.dp)
            )
            {
                Text(
                    text = senderName,
                    style = TextStyle(
                        fontFamily = Fonts.InterFontFamily,
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message,
                    style = TextStyle(
                        fontFamily = Fonts.InterFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = formatLocalDateTime(time),
                    style = TextStyle(
                        fontFamily = Fonts.InterFontFamily,
                        fontSize = 10.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.End
                    )
                )
            }
        }
    }
}

fun formatLocalDateTime(localDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return localDateTime.format(formatter)
}