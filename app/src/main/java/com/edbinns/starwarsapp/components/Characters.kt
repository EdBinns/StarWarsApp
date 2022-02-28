package com.edbinns.starwarsapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.edbinns.starwarsapp.Navigation.StarWarsScreens
import com.edbinns.starwarsapp.models.CharacterDetail
import com.edbinns.starwarsapp.viewmodel.CharactersViewModel
import com.edbinns.starwarsapp.widgets.DetailText
import com.edbinns.starwarsapp.widgets.SearchBar
import com.google.gson.Gson


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun Characters(viewModel: CharactersViewModel, navController: NavController) {

    val characters by remember {
        viewModel.charactersList
    }
    val endReached by remember {
        viewModel.endReached
    }
    val loading by remember {
        viewModel.isLoading
    }

    var title by remember {
        mutableStateOf("")
    }



    Column {

        SearchBar(
            title = title,
            onTextChange = {
                title = it
            },
            onImeAction = {
                viewModel.searchCharacter(nameCharacter = title)
                navController.navigate(route = StarWarsScreens.DetailScreen.name)
            }
        )
        LazyVerticalGrid(
            contentPadding = PaddingValues(4.dp),
            cells = GridCells.Adaptive(150.dp),
            modifier = Modifier
        ) {
            items(characters) { item ->

                if (item == characters[characters.size - 1] && !endReached) {
                    viewModel.getAllCharacters()
                }
                CharacterItem(detail = item, modifier = Modifier) { character ->
                    viewModel.characterItem.value = character
                    navController.navigate(route = StarWarsScreens.DetailScreen.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun CharacterItem(
    detail: CharacterDetail? = null,
    modifier: Modifier = Modifier,
    onItemClick: (CharacterDetail) -> Unit = {}
) {

    Column(
        modifier = modifier
            .padding(2.dp)
            .clickable { onItemClick(detail!!) },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box() {
            Surface(
                modifier = modifier
                    .size(200.dp)
                    .background(color = Color.Blue),
                shape = RectangleShape,
                border = BorderStroke(
                    width = 1.dp,
                    brush = Brush.linearGradient(listOf(Color.LightGray, Color.LightGray))
                )
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = detail?.image,
                        builder = {
                            crossfade(true)
                        }),
                    contentDescription = "Star Wars character"
                )
            }

            Column(
                modifier
                    .height(30.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color(0x5799CDF6)
                    ),

                ) {}

            Text(
                text = detail?.character?.name!!,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(5.dp),
                color = Color.White
            )
        }
    }
}


@Composable
fun DetailCharacter(
    characterDetail: CharacterDetail,
    homeworldList: MutableList<String>,
    films: MutableList<String>,
    vehicles: MutableList<String>,
    starships: MutableList<String>
) {

    val list = listOf(homeworldList,films,vehicles,starships)
    Column(
        modifier = Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CharacterItem(detail = characterDetail, modifier = Modifier.fillMaxWidth())

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        listOf(Color.LightGray, Color.LightGray)
                    ),
                    shape = RoundedCornerShape(2.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.padding(3.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Column {
                    DetailText(dataType = "Birth year", data = characterDetail.character.birth_year)


                    DetailText(dataType = "Eye color", data = characterDetail.character.eye_color)

                    DetailText(dataType = "Mass", data = characterDetail.character.mass)

                    DetailText(dataType = "Gender", data = characterDetail.character.gender)

                    DetailText(dataType = "Skin Color", data = characterDetail.character.skin_color)
                }


                Spacer(modifier = Modifier.width(50.dp))

                Column {
                    val createdSplit = characterDetail.character.created.split("T")
                    DetailText(dataType = "Created Day", data = createdSplit[0])

                    DetailText(dataType = "Hair color", data = characterDetail.character.hair_color)

                    val editedSplit = characterDetail.character.edited.split("T")
                    DetailText(dataType = "Edited", data = editedSplit[0])

                    DetailText(dataType = "Height", data = characterDetail.character.height)

                }

            }

        }

        val scrollState = rememberScrollState()
        // Column is a composable that places its children in a vertical sequence.
        Column(
            modifier = Modifier.verticalScroll(
                state = scrollState,
                enabled = true
            )
        ) {
            ShowImages(imagesList = homeworldList, dateName = "Homeworld")
            ShowImages(imagesList = films, dateName = "Films")
            ShowImages(imagesList = vehicles, dateName = "Vehicles")
            ShowImages(imagesList = starships, dateName = "Starships")

        }
    }
}


@Composable
fun ShowImages(
    imagesList: List<String> = emptyList(),
    dateName: String = ""
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = dateName)

        LazyRow {
            items(imagesList) { item ->
                Card(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(240.dp),
                    elevation = 5.dp
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = item,
                            builder = {
                                crossfade(true)
                            }),
                        contentDescription = "a detail image"
                    )
                }
            }
        }

    }

}





