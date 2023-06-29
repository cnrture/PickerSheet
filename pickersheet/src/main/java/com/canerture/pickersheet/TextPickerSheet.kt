package com.canerture.pickersheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextPickerSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    isOpen: Boolean = false,
    list: List<String>,
    title: String? = null,
    selectedItem: String? = null,
    selectedIcon: Int = R.drawable.ic_check,
    dividerEnabled: Boolean = false,
    fontFamily: FontFamily = FontFamily.Default,
    titleFontColor: Color = Color.Black,
    titleFontSize: TextUnit = 16.sp,
    itemFontColor: Color = Color.Black,
    itemFontSize: TextUnit = 14.sp,
    itemPadding: Dp = 16.dp,
    iconSize: Dp = 16.dp,
    onItemClick: ((String) -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {

    var selectedItem by remember { mutableStateOf(selectedItem) }

    if (isOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onDismiss?.invoke() }
        ) {
            Column(
                modifier = modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
            ) {
                title?.let {
                    Text(
                        modifier = Modifier,
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = titleFontSize,
                        fontFamily = fontFamily,
                        color = titleFontColor
                    )
                }
                LazyColumn {
                    itemsIndexed(list) { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedItem = item
                                    onItemClick?.invoke(item)
                                }
                                .padding(top = if (title != null && index == 0) itemPadding else 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = itemPadding)
                                    .weight(1f),
                                text = item,
                                fontSize = itemFontSize,
                                fontFamily = fontFamily,
                                color = itemFontColor
                            )
                            if (item == selectedItem) Icon(
                                modifier = Modifier.size(iconSize),
                                painter = painterResource(id = selectedIcon),
                                contentDescription = null
                            )
                        }
                        if (dividerEnabled && index != list.size - 1) {
                            Divider(color = colorResource(id = R.color.black))
                        }
                    }
                }
            }
        }
    }
}