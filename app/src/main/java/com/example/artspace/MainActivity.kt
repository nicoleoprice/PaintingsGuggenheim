package com.example.artspace

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceLayout() {
    var current by remember { mutableStateOf(1)}

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Paintings at Guggenheim",
                        fontFamily = FontFamily.Cursive,
                        fontSize = 35.sp,
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                    )
                }
            )},
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                ButtonPanel(
                    onClickNext = {
                        if(current < 5) current++
                    },
                    onClickPrevious = {
                        if(current > 1) current --}
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            when(current) {
                1 -> DisplayArt(
                    painterId = R.drawable.fernande_with_a_black_mantilla_pablo_picasso,
                    contentDescriptionId = R.string.fernande_black_mantilla_content_description,
                    titleId = R.string.fernande_black_mantilla,
                    artistId = R.string.pablo_picasso,
                    yearId = R.string.fernande_black_mantilla_year
                )
                
                2 -> DisplayArt(
                    painterId = R.drawable.woman_ironing_pablo_picasso,
                    contentDescriptionId = R.string.woman_ironing_content_description,
                    titleId = R.string.woman_ironing,
                    artistId = R.string.pablo_picasso,
                    yearId = R.string.woman_ironing_year
                )

                3 -> DisplayArt(
                    painterId = R.drawable.the_palazzo_ducale_claude_monet,
                    contentDescriptionId = R.string.the_palazzo_ducale_content_description,
                    titleId = R.string.the_palazzo_ducale,
                    artistId = R.string.claude_monet,
                    yearId = R.string.the_palazzo_ducale_year
                )

                4 -> DisplayArt(
                    painterId = R.drawable.the_football_players_henri_rousseau,
                    contentDescriptionId = R.string.the_football_players_content_description,
                    titleId = R.string.the_football_players,
                    artistId = R.string.henri_rousseau,
                    yearId = R.string.the_football_players_year
                )

                5 -> DisplayArt(
                    painterId = R.drawable.place_vintimille_edouard_vuillard,
                    contentDescriptionId = R.string.place_vintimille_content_description,
                    titleId = R.string.place_vintimille,
                    artistId = R.string.edouard_vuillard,
                    yearId = R.string.place_vintimille_year
                )
            }
        }

    }
}
@Composable
fun DisplayArt(
    painterId: Int,
    contentDescriptionId: Int,
    titleId: Int,
    artistId: Int,
    yearId: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        DisplayArtImage(painterId = painterId, contentDescriptionId = contentDescriptionId)
        Spacer(modifier = Modifier.padding(top = 20.dp))
        DisplayArtDescription(titleId = titleId, artistId = artistId, yearId = yearId)
    }
}

@Composable
fun DisplayArtImage(
    painterId: Int,
    contentDescriptionId: Int
) {
    Image(
        painter = painterResource(id = painterId),
        contentDescription = stringResource(contentDescriptionId),
        modifier = Modifier
            .border(
                10.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = RectangleShape
            )

    )
}

@Composable
fun DisplayArtDescription(
    titleId: Int,
    artistId: Int,
    yearId: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f)
            )
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(id = titleId),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Row {
            Text(
                text = stringResource(id = artistId),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(yearId)
            )
        }
    }
}

@Composable
fun ButtonPanel(
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Button(
            onClick = onClickPrevious,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "previous")

        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onClickNext
        ) {
            Text(text = "next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}