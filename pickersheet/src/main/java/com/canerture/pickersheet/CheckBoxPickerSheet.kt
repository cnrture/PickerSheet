package com.canerture.pickersheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckBoxPickerSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    showState: Boolean = false,
    list: List<String>,
    title: String? = null,
    selectedItems: List<String>? = null,
    isDragIconEnabled: Boolean = true,
    fontFamily: FontFamily = FontFamily.Default,
    dividerConfiguration: DividerConfiguration = DividerConfiguration(),
    titleConfiguration: TitleConfiguration = TitleConfiguration(),
    itemConfiguration: ItemConfiguration = ItemConfiguration(),
    sheetColors: PickerSheetColors = PickerSheetColors(),
    onDismiss: ((List<String>) -> Unit)? = null
) {

    var selectedItemsTemp = remember { mutableStateListOf("") }
    selectedItems?.let { selectedItemsTemp.addAll(it) }

    if (showState) {
        ModalBottomSheet(
            sheetState = sheetState,
            scrimColor = sheetColors.scrimColor,
            containerColor = sheetColors.backgroundColor,
            dragHandle = {
                if (isDragIconEnabled) BottomSheetDefaults.DragHandle()
            },
            onDismissRequest = { onDismiss?.invoke(selectedItemsTemp) }
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
                        fontSize = titleConfiguration.size,
                        fontFamily = fontFamily,
                        color = titleConfiguration.color,
                        textAlign = titleConfiguration.align
                    )
                }
                LazyColumn {
                    itemsIndexed(list) { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = if (title != null && index == 0) itemConfiguration.padding else 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedItemsTemp.contains(item),
                                onCheckedChange = {
                                    if (it) selectedItemsTemp.add(item)
                                    else selectedItemsTemp.remove(item)
                                }
                            )
                            Text(
                                modifier = Modifier
                                    .padding(vertical = itemConfiguration.padding)
                                    .weight(1f),
                                text = item,
                                fontSize = itemConfiguration.size,
                                fontFamily = fontFamily,
                                color = itemConfiguration.color
                            )
                        }
                        if (dividerConfiguration.isEnabled && index != list.size - 1) {
                            Divider(color = dividerConfiguration.color)
                        }
                    }
                }
            }
        }
    }
}