package com.edbinns.starwarsapp.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailText(dataType: String, data: String) {

    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = Color.LightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            ) {
                append("${dataType}: ")
            }

            withStyle(
                style = SpanStyle(
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            ) {
                append(data)
            }
        }
    }, modifier = Modifier.padding(2.dp))
}


@ExperimentalComposeUiApi
@Preview
@Composable
fun SearchBar(
    title: String = "",
    onTextChange: (String) -> Unit = {},
    onImeAction: () -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        elevation = 4.dp,
        border = BorderStroke(width = 1.2.dp, Color.LightGray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row() {

            Icon(
                Icons.Default.Search,
                "",
                tint = Color.Blue,
                modifier = Modifier
                    .padding(15.dp)
            )
            
            TextField(
                value = title,
                onValueChange = onTextChange,
                label = { Text(text = "Search") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    onImeAction()
                    keyboardController?.hide()
                }),
                modifier = Modifier.padding(4.dp)
            )
        }

    }
}

