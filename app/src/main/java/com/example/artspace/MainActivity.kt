package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}


@Composable
fun ArtSpaceLayout(
    artworkTitle: Int,
    artwork: Int,
    contentDescriptionId: Int,
    artworkInfo: Int,
    backButton: () -> Unit,
    nextButton: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = stringResource(artworkTitle),
            fontSize = 30.sp,
            color = Color.DarkGray,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = painterResource(artwork),
            contentDescription = stringResource(contentDescriptionId),
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp)
                .border(15.dp, Color.White)
        )
//        Spacer(modifier = Modifier.height(32.dp))
        Text(text = stringResource(artworkInfo), color = Color.DarkGray)
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = { backButton() },
                colors = ButtonDefaults.buttonColors(Color(66, 178, 138)),
                shape = RoundedCornerShape(48.dp)
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = { nextButton() },
                colors = ButtonDefaults.buttonColors(Color(66, 178, 138)),
                shape = RoundedCornerShape(48.dp)
            ) {
                Text(text = "Next")
            }

        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFD3D3D3)
@Composable
fun ArtSpaceScreen() {
    var currentStep by remember { mutableStateOf(0) }

    val artworkTitle = when (currentStep) {
        0 -> R.string.title_1
        else -> R.string.title_2
    }

    val artwork = when (currentStep) {
        0 -> R.drawable.artwork_1
        else -> R.drawable.artwork_2
    }

    val contentDescriptionId = when (currentStep) {
        0 -> R.string.description_1
        else -> R.string.description_2
    }

    val artworkInfo = when (currentStep) {
        0 -> R.string.info_1
        else -> R.string.info_2
    }

    ArtSpaceLayout(
        artworkTitle = artworkTitle,
        artwork = artwork,
        contentDescriptionId = contentDescriptionId,
        artworkInfo = artworkInfo,
        backButton = { currentStep = (currentStep + 1) },
        nextButton = { currentStep = (currentStep - 1) }
    )
}
