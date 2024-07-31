package com.example.presentation.baseviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.resourses.R
import com.example.resourses.theme.SearchHint
import com.example.resourses.theme.TenDp
import com.example.resourses.theme.colors

@Preview(showBackground = true)
@Composable
private fun BaseSearchTextFieldPreview() {
    BaseSearchTextField()
}

@Composable
fun BaseSearchTextField(
    modifier: Modifier = Modifier,
    hint: String = SearchHint,
    searchRequest: (String) -> Unit = {}
) {
    var inputText by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = inputText,
        onValueChange = {
            inputText = it
            searchRequest(inputText)
        },
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = hint) },
        leadingIcon = {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_search_24
                ),
                contentDescription = null
            )
        },
        trailingIcon = {
            if (inputText.isNotBlank())
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_clear_24
                    ),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        inputText = ""
                        searchRequest(inputText)
                    }
                )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go
        ),
        shape = RoundedCornerShape(TenDp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colors.white,
            focusedContainerColor = MaterialTheme.colors.white,
            focusedTextColor = MaterialTheme.colors.black,
            focusedBorderColor = MaterialTheme.colors.color43464F,
        )
    )
}