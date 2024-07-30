package com.example.presentation.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.presentation.baseviews.BaseTextButton
import com.example.resourses.R
import com.example.resourses.theme.AcceptCaption
import com.example.resourses.theme.CancelCaption
import com.example.resourses.theme.ConfirmationQuestion
import com.example.resourses.theme.NoCaption
import com.example.resourses.theme.ProductAmount
import com.example.resourses.theme.ProductDeletion
import com.example.resourses.theme.SixteenDp
import com.example.resourses.theme.SixteenSp
import com.example.resourses.theme.TwelveDp
import com.example.resourses.theme.TwentyDp
import com.example.resourses.theme.TwentyTwoSp
import com.example.resourses.theme.YesCaption
import com.example.resourses.theme.colors

@Preview(showBackground = true)
@Composable
private fun EditDialogPreview() {
    EditDialog()
}

@Preview(showBackground = true)
@Composable
private fun DeleteDialogPreview() {
    DeleteDialog()
}

@Composable
fun EditDialog(
    currentAmount: Int = 0,
    onDismissRequest: () -> Unit = {},
    saveProduct: (Int) -> Unit = {}
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TwelveDp),
            shape = RoundedCornerShape(TwentyDp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colors.white
            )
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(
                        horizontal = SixteenDp,
                        vertical = TwentyDp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var inputAmount by remember {
                    mutableIntStateOf(currentAmount)
                }

                /**
                 * Edit dialog icon
                 */
                Image(
                    painter = painterResource(id = R.drawable.ic_edit_dialog_24),
                    contentDescription = null
                )

                /**
                 * Product amount the text
                 */
                Text(
                    text = ProductAmount,
                    fontSize = TwentyTwoSp,
                    modifier = Modifier
                        .padding(vertical = TwelveDp)
                )

                /**
                 * Editable product amount
                 */
                TextField(
                    value = inputAmount.toString(),
                    onValueChange = {},
                    textStyle = TextStyle(
                        fontSize = SixteenSp,
                        textAlign = TextAlign.Center,
                    ),
                    leadingIcon = {
                        /**
                         * DECREASE product amount
                         */
                            Image(
                                painter = painterResource(id = R.drawable.ic_remove_item_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        inputAmount.let {
                                            if (it > 0)
                                                inputAmount -= 1
                                        }
                                    }
                            )
                    },
                    trailingIcon = {
                        /**
                         * INCREASE product amount
                         */
                        Image(
                            painter = painterResource(id = R.drawable.ic_add_item_24),
                            contentDescription = null,
                            modifier = Modifier.clickable { inputAmount += 1 }
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colors.black,
                        unfocusedTextColor = MaterialTheme.colors.black,
                        focusedContainerColor = MaterialTheme.colors.transparent,
                        unfocusedContainerColor = MaterialTheme.colors.transparent,
                        focusedIndicatorColor = MaterialTheme.colors.transparent,
                        unfocusedIndicatorColor = MaterialTheme.colors.transparent,
                    )
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        /**
                         * Cancel button
                         */
                        BaseTextButton(
                            text = CancelCaption,
                            onClick = onDismissRequest
                        )

                        /**
                         * Accept button
                         */
                        BaseTextButton(
                            text = AcceptCaption,
                            onClick = { saveProduct(inputAmount) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteDialog(
    onDismissRequest: () -> Unit = {},
    deleteProduct: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TwelveDp),
            shape = RoundedCornerShape(TwentyDp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colors.white
            )
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(
                        horizontal = SixteenDp,
                        vertical = TwentyDp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Delete dialog icon
                 */
                Image(
                    painter = painterResource(id = R.drawable.ic_delete_dialog_24),
                    contentDescription = null
                )

                /**
                 * Product deletion the text
                 */
                Text(
                    text = ProductDeletion,
                    fontSize = TwentyTwoSp,
                    modifier = Modifier
                        .padding(vertical = TwelveDp)
                )

                /**
                 * Confirmation question
                 */
                Text(
                    text = ConfirmationQuestion,
                    modifier = Modifier
                        .padding(vertical = TwelveDp)
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        /**
                         * No button
                         */
                        BaseTextButton(
                            text = NoCaption,
                            onClick = onDismissRequest
                        )

                        /**
                         * Yes button
                         */
                        BaseTextButton(
                            text = YesCaption,
                            onClick = deleteProduct
                        )
                    }
                }
            }
        }
    }
}