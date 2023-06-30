package com.canerture.pickersheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextPickerSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    isOpen: Boolean = false,
    list: List<String>,
    title: String? = null,
    selectedItem: String? = null,
    isDragIconEnabled: Boolean = true,
    dividerEnabled: Boolean = false,
    fontFamily: FontFamily = FontFamily.Default,
    titleConfigure: TitleConfigure = TitleConfigure(),
    itemConfigure: ItemConfigure = ItemConfigure(),
    sheetColors: PickerSheetColors = PickerSheetColors(),
    selectedIconConfigure: SelectedIconConfigure = SelectedIconConfigure(),
    onItemClick: ((String) -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {

    var selectedItem by remember { mutableStateOf(selectedItem) }

    if (isOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            scrimColor = sheetColors.scrimColor,
            containerColor = sheetColors.backgroundColor,
            dragHandle = {
                if (isDragIconEnabled) BottomSheetDefaults.DragHandle()
            },
            onDismissRequest = { onDismiss?.invoke() }
        ) {
            Column(
                modifier = modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
            ) {
                title?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(top = if (isDragIconEnabled) 0.dp else 12.dp),
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = titleConfigure.size,
                        fontFamily = fontFamily,
                        color = titleConfigure.color,
                        textAlign = titleConfigure.align
                    )
                }
                LazyColumn {
                    itemsIndexed(list) { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickableWithoutRipple {
                                    selectedItem = item
                                    onItemClick?.invoke(item)
                                }
                                .padding(top = if (title != null && index == 0) itemConfigure.padding else 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = itemConfigure.padding)
                                    .weight(1f),
                                text = item,
                                fontSize = itemConfigure.size,
                                fontFamily = fontFamily,
                                color = itemConfigure.color
                            )
                            if (item == selectedItem) Icon(
                                modifier = Modifier.padding(end = 8.dp).size(selectedIconConfigure.size),
                                painter = painterResource(id = selectedIconConfigure.icon),
                                tint = selectedIconConfigure.color,
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