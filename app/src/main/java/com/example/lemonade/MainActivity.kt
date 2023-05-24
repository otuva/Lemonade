package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LemonImageAndText(modifier = Modifier)
        }
    }
}

@Composable
fun LemonImageAndText(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var squeeze by remember { mutableStateOf(5) }

    var currentImage = painterResource(id = R.drawable.lemon_tree)
    var currentDescription = stringResource(R.string.lemon_tree_desc)
    var currentText = stringResource(R.string.lemon_tree_text)

    when (result) {
        2 -> {
            squeeze = (5..12).random()
            currentImage = painterResource(id = R.drawable.lemon_squeeze)
            currentDescription = stringResource(R.string.lemon_squeeze_desc)
            currentText = stringResource(R.string.lemon_squeeze_text)
        }

        3 -> {
            currentImage = painterResource(id = R.drawable.lemon_drink)
            currentDescription = stringResource(R.string.lemon_drink_desc)
            currentText = stringResource(R.string.lemon_drink_text)
        }

        4 -> {
            currentImage = painterResource(id = R.drawable.lemon_restart)
            currentDescription = stringResource(R.string.lemon_restart_desc)
            currentText = stringResource(R.string.lemon_restart_text)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (result < 4 && result != 2) {
                    result++

                } else if (result == 2) {
                    squeeze--
                    if (squeeze <= 0) result++

                } else {
                    result = 1
                }
            },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(4.dp, MaterialTheme.colorScheme.primaryContainer)
        ) {
            Image(
                painter = currentImage,
                contentDescription = currentDescription
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = currentText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}