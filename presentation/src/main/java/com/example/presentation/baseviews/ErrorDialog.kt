package com.example.presentation.baseviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.resourses.R
import com.example.resourses.theme.DevSalesTheme
import com.example.resourses.theme.MeanIt
import com.example.resourses.theme.SixteenDp
import com.example.resourses.theme.SomethingGoingWrong
import com.example.resourses.theme.TenDp
import com.example.resourses.theme.TwelveSp
import com.example.resourses.theme.TwentyDp

@Preview(showBackground = true)
@Composable
private fun ErrorDialogPreview() {
    DevSalesTheme { ErrorDialog() }
}

@Composable
fun ErrorDialog(
    onDismissRequest: () -> Unit = {},
    message: String? = null
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .wrapContentSize()
            ,
            shape = RoundedCornerShape(TwentyDp),
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = SixteenDp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Error image
                 */
                Image(
                    modifier = Modifier.wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_error_24),
                    contentDescription = null,
                )

                /**
                 * Message text
                 */
                Text(
                    text = SomethingGoingWrong,
                    modifier = Modifier.padding(top = TenDp),
                    textAlign = TextAlign.Center
                )

                /**
                 * Detail information
                 */
                message?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(top = TenDp),
                        fontSize = TwelveSp,
                        textAlign = TextAlign.Center
                    )
                }

                /**
                 * Confirm button
                 */
                BaseTextButton(
                    text = MeanIt,
                    onClick = onDismissRequest
                )
            }
        }
    }
}