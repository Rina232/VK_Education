package com.example.vk_hw_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.vk_hw_1.ui.theme.Vk_hw_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vk_hw_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CardScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun CardScreen(modifier: Modifier = Modifier) {
    var squaresCount:Int by rememberSaveable { mutableIntStateOf(0) }
    Column(
        modifier = modifier.padding(top = dimensionResource(R.dimen.column_top_padding))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(integerResource(R.integer.columns_number)),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing)),
        ) {
            items (squaresCount) { i ->
                Card(Modifier, i)
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(dimensionResource(R.dimen.add_button_padding)),
            onClick = {
                squaresCount++
            },
        ) {
            Text(
                text = stringResource(R.string.add_button_text),
                fontSize = integerResource(R.integer.button_text_size).sp,
            )
        }
    }
}

//@Preview
@Composable
fun Card(modifier: Modifier = Modifier, index: Int = 0){
    val color = if (index % 2 == 0) colorResource(R.color.red) else colorResource(R.color.blue)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dimensionResource(R.dimen.card_corner)))
            .background(color)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center,
    ) {
        Text (
            text = index.toString(),
            fontSize = integerResource(R.integer.card_text_size).sp,
        )
    }
}