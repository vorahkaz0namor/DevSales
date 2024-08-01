package com.example.presentation.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.dto.Product
import com.example.presentation.baseviews.BaseImageButton
import com.example.presentation.baseviews.BaseSearchTextField
import com.example.presentation.utils.timeInHumanRepresentation
import com.example.resourses.R
import com.example.resourses.theme.AppendingDate
import com.example.resourses.theme.DevSalesTheme
import com.example.resourses.theme.EightDp
import com.example.resourses.theme.EightDpNegative
import com.example.resourses.theme.EmptyResultBySearchRequest
import com.example.resourses.theme.InStorehouse
import com.example.resourses.theme.OutOfStock
import com.example.resourses.theme.ProductsList
import com.example.resourses.theme.SixteenDp
import com.example.resourses.theme.TenDp
import com.example.resourses.theme.TwelveDp
import com.example.resourses.theme.TwentyTwoSp
import com.example.resourses.theme.colors

@Preview(showBackground = true)
@Composable
private fun StoreMainScreenPreview() {
    DevSalesTheme {
        StoreLauncherScreen(
            products = Product.initialProductList
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    DevSalesTheme {
        ItemCard(
            item = Product.initialProductList.find {
                it.id == 8
            }!!
        )
    }
}

@Composable
internal fun StoreLauncherRoute(
    storeViewModel: StoreLauncherVM,
) {
    StoreLauncherScreen(
        state = storeViewModel.storeState.collectAsStateWithLifecycle().value,
        products = storeViewModel.products.collectAsStateWithLifecycle().value,
        openEditDialog = storeViewModel.openEditDialog.collectAsStateWithLifecycle().value,
        openDeleteDialog = storeViewModel.openDeleteDialog.collectAsStateWithLifecycle().value,
        currentAmount = storeViewModel.activeProduct.collectAsStateWithLifecycle().value
            ?.amount ?: 0,
        callbackState = CallbackState(
            openEditDialog = { storeViewModel.openEditDialog(it) },
            openDeleteDialog = { storeViewModel.openDeleteDialog(it) },
            closeEditDialog = { storeViewModel.closeEditDialog() },
            closeDeleteDialog = { storeViewModel.closeDeleteDialog() },
            saveProduct = { storeViewModel.saveProduct(it) },
            deleteProduct = { storeViewModel.deleteProduct() },
            searchProducts = { storeViewModel.searchProducts(it) },
        ),
    )
}

@Composable
private fun StoreLauncherScreen(
    state: StoreState = StoreState.Success,
    products: List<Product> = emptyList(),
    openEditDialog: Boolean = false,
    openDeleteDialog: Boolean = false,
    currentAmount: Int = 0,
    callbackState: CallbackState = CallbackState()
) {
    when {
        openEditDialog -> {
            EditDialog(
                currentAmount = currentAmount,
                onDismissRequest = callbackState.closeEditDialog,
                saveProduct = callbackState.saveProduct
            )
        }
        openDeleteDialog -> {
            DeleteDialog(
                onDismissRequest = callbackState.closeDeleteDialog,
                deleteProduct = callbackState.deleteProduct
            )
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.colorF3F2F3)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /**
         * Screen header
         */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.colorB0DAFA),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = ProductsList,
                modifier = Modifier
                    .padding(vertical = SixteenDp)
            )
        }

        /**
         * Search line
         */
        BaseSearchTextField(
            modifier = Modifier.padding(all = TwelveDp),
            searchRequest = callbackState.searchProducts
        )

        state.StateHandler {
            if (products.isNotEmpty()) {
                /**
                 * Products cards
                 */
                LazyColumn(
                    modifier = Modifier.padding(horizontal = TwelveDp),
                    verticalArrangement = Arrangement.spacedBy(TenDp),
                    contentPadding = PaddingValues(
                        vertical = TenDp
                    )
                ) {
                    items(items = products) { item ->
                        ItemCard(
                            item = item,
                            onEditClick = { callbackState.openEditDialog(item) },
                            onDeleteClick = { callbackState.openDeleteDialog(item) }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.aligned { _, space ->
                        (0.25 * space).toInt()
                    },
                ) {
                    Text(
                        // TODO: There should be empty stock handling
                        text = EmptyResultBySearchRequest,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    item: Product,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colors.white
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(TwelveDp)
        ) {
            val (name, edit, del, chips, params) = createRefs()

            /**
             * Product name
             */
            Column(
                modifier = Modifier
                    .constrainAs(name) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(edit.start)
                        width = Dimension.fillToConstraints
                    },
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.name,
                    fontSize = TwentyTwoSp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            /**
             * Edit button
             */
            BaseImageButton(
                modifier = Modifier
                    .constrainAs(edit) {
                        top.linkTo(del.top)
                        end.linkTo(del.start)
                    },
                image = R.drawable.ic_mode_edit_24,
                onClick = onEditClick
            )

            /**
             * Delete button
             */
            BaseImageButton(
                modifier = Modifier
                    .constrainAs(del) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                image = R.drawable.ic_delete_24,
                onClick = onDeleteClick
            )

            /**
             * Product chips
             */
            FlowRow(
                modifier = Modifier
                    .constrainAs(chips) {
                        top.linkTo(name.bottom)
                        bottom.linkTo(params.top)
                        start.linkTo(parent.start)
                    },
                verticalArrangement = Arrangement.spacedBy(EightDpNegative)
            ) {
                item.tags.map {
                    SuggestionChip(
                        onClick = {},
                        label = {
                            Text(
                                text = it,
                                color = MaterialTheme.colors.black
                            )
                        },
                        modifier = Modifier
                            .padding(end = EightDp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(params) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                /**
                 * Is in storehouse
                 */
                Column(
                    modifier = Modifier.weight(1.0f)
                ) {
                    Text(
                        text = InStorehouse,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text =
                        item.amount.let {
                            if (it != 0)
                                it.toString()
                            else
                                OutOfStock
                        }
                    )
                }

                /**
                 * Appending date
                 */
                Column(
                    modifier = Modifier.weight(1.0f)
                ) {
                    Text(
                        text = AppendingDate,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text =
                            timeInHumanRepresentation(item.time)
                    )
                }
            }
        }
    }
}