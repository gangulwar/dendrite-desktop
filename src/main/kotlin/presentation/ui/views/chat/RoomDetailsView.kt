package presentation.ui.views.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import di.MyKoinComponent
import presentation.ui.nav.Screens
import presentation.viewmodel.User
import utils.*
import java.io.File

@Composable
fun RoomDetailsView(

) {

    val koinComponent = remember { MyKoinComponent() }

    val connectedUsersCount by koinComponent.chatViewModel.connectedUsersCount.collectAsState(initial = 0)

    val connectedUserList by koinComponent.chatViewModel.connectedUsers.collectAsState()

    var currentIndex by remember { mutableStateOf(0) }
    val maxUsersToShow = 5

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Image(
            painter = BitmapPainter(
                loadImageBitmap(
                    File(Avatars.avatarList[CurrentUser.avatar.toInt()]).inputStream()
                )
            ),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(150.dp),
        )

        Text(
            text = CurrentUser.name,
            style = TextStyle(
                fontFamily = Fonts.InterFontFamily,
                fontSize = 23.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        )

        Divider(modifier = Modifier.padding(10.dp).fillMaxWidth().background(Color.Black))
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            text = "Room ID: 231204",
            style = TextStyle(
                fontFamily = Fonts.InterFontFamily,
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = BitmapPainter(
                    loadImageBitmap(
                        File(ImagePath.MEMBERS).inputStream()
                    )
                ),
                contentDescription = "Group Icon",
                modifier = Modifier
                    .size(30.dp),
            )

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = connectedUsersCount.toString(),
                style = TextStyle(
                    fontFamily = Fonts.InterFontFamily,
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            )

        }

    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            connectedUserList.subList(
                currentIndex,
                minOf(currentIndex + maxUsersToShow, connectedUserList.size)
            )
        ) { user ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = BitmapPainter(
                        loadImageBitmap(
                            File(user.avatar).inputStream()
                        )
                    ),
                    contentDescription = "Member Avatar",
                    modifier = Modifier
                        .size(50.dp),
                )

                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = user.name,
                    style = TextStyle(
                        fontFamily = Fonts.EloquiaFontFamily,
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        Image(
            painter = BitmapPainter(
                loadImageBitmap(
                    File(ImagePath.LEFT_ARROW).inputStream()
                )
            ),
            contentDescription = "Left Arrow",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    if (currentIndex >= maxUsersToShow) {
                        currentIndex -= maxUsersToShow
                    }
                }
        )

        Image(
            painter = BitmapPainter(
                loadImageBitmap(
                    File(ImagePath.RIGHT_ARROW).inputStream()
                )
            ),
            contentDescription = "Right Arrow",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    if (currentIndex + maxUsersToShow < connectedUserList.size) {
                        currentIndex += maxUsersToShow
                    }
                }
        )

    }

    Button(
        modifier = Modifier
            .padding(bottom = 5.dp),
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.DarkBlue
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
            text = "Leave Room", style = TextStyle(
                fontFamily = Fonts.InterFontFamily,
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
    }
}


val userList = listOf(
    User(
        "User 1",
        Avatars.avatarList[0]
    ),

    User(
        "User 2",
        Avatars.avatarList[1]
    ),

    User(
        "User 3",
        Avatars.avatarList[2]
    ),
    User(
        "User 4",
        Avatars.avatarList[3]
    ),

    User(
        "User 5",
        Avatars.avatarList[4]
    ),

    User(
        "User 6",
        Avatars.avatarList[5]
    ),
    User(
        "User 7",
        Avatars.avatarList[6]
    ),

    User(
        "User 8",
        Avatars.avatarList[7]
    ),

    User(
        "User 9",
        Avatars.avatarList[8]
    ),

    User(
        "User 10",
        Avatars.avatarList[0]
    ),

    User(
        "User 11",
        Avatars.avatarList[1]
    ),

    User(
        "User 12",
        Avatars.avatarList[2]
    ),
    User(
        "User 13",
        Avatars.avatarList[3]
    ),

    User(
        "User 14",
        Avatars.avatarList[4]
    ),

    User(
        "User 15",
        Avatars.avatarList[5]
    ),
    User(
        "User 16",
        Avatars.avatarList[6]
    ),

    User(
        "User 17",
        Avatars.avatarList[7]
    ),

    User(
        "User 18",
        Avatars.avatarList[8]
    ),
)