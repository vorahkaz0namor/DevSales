package com.example.presentation.baseviews

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.resourses.R
import com.example.resourses.theme.TenDp

@Preview(showBackground = true)
@Composable
private fun BaseImageButtonPreview() {
    BaseImageButton(image = R.drawable.ic_mode_edit_24)
}

@Composable
fun BaseImageButton(
    modifier: Modifier = Modifier,
    @DrawableRes
    image: Int,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = modifier
            .padding(horizontal = TenDp)
            .clickable { onClick() },
    )
}