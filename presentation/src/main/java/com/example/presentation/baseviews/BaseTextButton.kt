package com.example.presentation.baseviews

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.resourses.theme.SixteenSp
import com.example.resourses.theme.TenDp
import com.example.resourses.theme.colors

@Preview(showBackground = true)
@Composable
private fun BaseTextButtonPreview() {
    BaseTextButton()
}

@Composable
fun BaseTextButton(
    text: String = "Click me",
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colors.transparent,
        ),
        contentPadding = PaddingValues(horizontal = TenDp),
        shape = RoundedCornerShape(TenDp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = SixteenSp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.color664FA4,
            )
        )
    }
}