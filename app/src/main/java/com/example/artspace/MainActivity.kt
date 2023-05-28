package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
//@Composable
//fun ArtSpaceApp() {
//
//    Scaffold(topBar = {
//        CenterAlignedTopAppBar(
//            title = {
//                Text(
//                    text = stringResource(id = R.string.app_name),
//                    fontSize = 30.sp,
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold
//                )
//            },
//            colors = TopAppBarDefaults.mediumTopAppBarColors(
//                containerColor = Color(66, 178, 138)
//            ),
//        )
//    }, content = ArtSpaceScreen()
//    )
//}

@Composable
fun ArtSpaceLayout(
    artworkTitle: Int,
    artwork: Int,
    contentDescriptionId: Int,
    artworkInfo: Int,
    onBtnClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(artworkTitle))
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(artwork),
            contentDescription = stringResource(contentDescriptionId),
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = stringResource(artworkInfo))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = { onBtnClick() },
                colors = ButtonDefaults.buttonColors(Color.Cyan),
                shape = RoundedCornerShape(48.dp)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = { onBtnClick() },
                colors = ButtonDefaults.buttonColors(Color.Cyan),
                shape = RoundedCornerShape(48.dp)
            ) {
                Text(text = "Next")
            }

        }

    }
}

@Preview (showBackground = true)
@Composable
fun ArtSpaceScreen() {
    var currentStep by remember { mutableStateOf(0) }

    val artworkTitle = when (currentStep){
        0 -> R.string.title_1
        else -> R.string.title_2
    }

    val artwork = when (currentStep){
        0 -> R.drawable.artwork_1
        else -> R.drawable.artwork_2
    }

    val contentDescriptionId = when (currentStep){
        0 -> R.string.description_1
        else -> R.string.description_2
    }

    val artworkInfo = when (currentStep){
        0 -> R.string.info_1
        else -> R.string.info_2
    }

    ArtSpaceLayout(
        artworkTitle = artworkTitle,
        artwork = artwork,
        contentDescriptionId = contentDescriptionId,
        artworkInfo = artworkInfo,
        onBtnClick = {currentStep = (currentStep+1).rem(1)}
    )
}
