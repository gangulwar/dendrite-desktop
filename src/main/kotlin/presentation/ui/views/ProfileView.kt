package presentation.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xml.sax.InputSource
import utils.Avatars
import utils.Colors
import utils.Fonts
import utils.ImagePath
import java.io.File
import java.io.IOException
import java.net.URL

@Composable
fun ProfileView(
    navController: NavController
) {

    var name by remember {
        mutableStateOf("")
    }

    val avatars = Avatars.getAvatarList()

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
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(bottom = 25.dp),
                text = "Profile", style = TextStyle(
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
                    value = name,
                    onValueChange = {
                        name = it

                    },
                    placeholder = {
                        Text(
                            "enter your name", style = TextStyle(
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

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 25.dp),
                    horizontalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    avatars.take(5).forEach {
                        AvatarImage(it)
                    }
                }
                Row(
                    modifier = Modifier.padding(bottom = 25.dp),
                    horizontalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    avatars.takeLast(5).forEach {
                        AvatarImage(it)
                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(bottom = 25.dp),
                text = "enter your name and select a avatar", style = TextStyle(
                    fontFamily = Fonts.InterFontFamily,
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Thin
                )
            )

            Button(
                modifier = Modifier
                    .padding(bottom = 15.dp),
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Colors.DarkBlue
                ),
                shape = RoundedCornerShape(23.dp)
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 50.dp,
                        end = 50.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                    text = "Join", style = TextStyle(
                        fontFamily = Fonts.InterFontFamily,
                        fontSize = 30.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
                .padding(start = 15.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.CenterStart
        ) {

            Image(
                modifier = Modifier.align(Alignment.CenterStart)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) {
                        navController.popBackStack()
                    },
                painter = BitmapPainter(
                    loadImageBitmap(
                        File(ImagePath.BACK_ARROW).inputStream()
                    )
                ),
                contentDescription = null
            )

        }

    }
}


@Composable
fun AvatarImage(resourcePath: String) {

    Image(
        painter = BitmapPainter(
            loadImageBitmap(
                File(resourcePath).inputStream()
            )
        ),
        contentDescription = "Avatar",
        modifier = Modifier.size(100.dp)
            .background(Color.Transparent)
            .shadow(0.dp, RoundedCornerShape(25.dp))
    )

}
