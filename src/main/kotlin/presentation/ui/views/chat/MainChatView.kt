package presentation.ui.views.chat

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import di.MyKoinComponent
import presentation.viewmodel.ChatMessageType
import presentation.viewmodel.User
import utils.*
import utils.Colors
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
@Preview
fun MainChatView(navController: NavController) {

    val koinComponent = remember { MyKoinComponent() }

    val chatList by koinComponent.chatViewModel.chatsList.collectAsState()

    val roomName by koinComponent.chatViewModel.roomName.collectAsState()

    val scrollState = rememberLazyListState()

    LaunchedEffect(chatList.size) {
        // Scroll to the bottom whenever the chatList changes
        if (chatList.isNotEmpty()) {
            scrollState.animateScrollToItem(chatList.lastIndex)
        }
    }

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
                                    text = roomName, style = TextStyle(
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
                        MessageInputField(koinComponent)
                    }
                ) { it ->

                    LazyColumn(
                        modifier = Modifier.padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding()
                        )
                            .fillMaxWidth()
                            .drawRightBorder(0.5.dp, Color.Black, LocalDensity.current),
                        state = scrollState
                    ) {
                        items(chatList) {
                            when (it.second) {
                                ChatMessageType.SENT -> {
                                    AnotherSenderView(
                                        it.first.message,
                                        LocalDateTime.now()
                                    )
                                }

                                ChatMessageType.RECEIVED -> {
                                    ReceiverView(
                                        it.first.sender.name,
                                        it.first.sender.avatar,
                                        it.first.message,
                                        it.first.time,
                                    )
                                }

                                ChatMessageType.JOINED -> TODO()
                                ChatMessageType.LEAVED -> TODO()
                            }
                        }
                    }
                    /*
//                    Column(
//                        modifier = Modifier.padding(it.calculateTopPadding())
//                            .fillMaxWidth()
//                            .drawRightBorder(0.5.dp, Color.Black, LocalDensity.current)
//                    ) {
//                        AnotherSenderView("Hello Guys!!!", LocalDateTime.now())
//                        ReceiverView("Dave", Avatars.avatarList[0], "Hi Guys", LocalDateTime.now())
//                        AnotherSenderView("Hello Guys!!!", LocalDateTime.now())
//                        ReceiverView("Dave", Avatars.avatarList[0], "Hi Guys", LocalDateTime.now())
//                        AnotherSenderView("Hello Guys!!!", LocalDateTime.now())
//                        ReceiverView("Dave", Avatars.avatarList[0], "Hi Guys", LocalDateTime.now())
//                        AnotherSenderView(
//                            "I am fine guys what about and and what's " +
//                                    "happing around I am fine guys what about and " +
//                                    "and what's happing around ys what about and hello",
//                            LocalDateTime.now()
//                        )
//
//                    }
*/

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                RoomDetailsView()
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
                        MessageInputField(MyKoinComponent())
                    }
                ) { it ->

                    Column(
                        modifier = Modifier.padding(it.calculateTopPadding())
                            .fillMaxWidth()
                            .drawRightBorder(0.5.dp, Color.Black, LocalDensity.current)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                RoomDetailsView()
            }
        }
    }
}


@Composable
fun Modifier.drawRightBorder(width: Dp, color: Color, density: Density) = this.then(
    Modifier.drawBehind {
        val strokeWidth = with(density) { width.toPx() }
        val strokeColor = color.toArgb()
        drawLine(
            color = color,
            start = Offset(size.width - strokeWidth / 2, 0f),
            end = Offset(size.width - strokeWidth / 2, size.height),
            strokeWidth = strokeWidth
        )
    }
)


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