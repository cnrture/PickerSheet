package com.canerture.pickersheet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
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
    checkboxColors: CheckboxColors = CheckboxDefaults.colors(),
    onDismiss: ((List<String>) -> Unit)? = null
) {

    val selectedItemsTemp = remember { mutableStateListOf<String>() }
    selectedItems?.let {
        selectedItemsTemp.addAll(it)
    }

    if (showState) {
        PickerSheet(
            modifier = modifier,
            sheetState = sheetState,
            list = list,
            title = title,
            fontFamily = fontFamily,
            titleConfiguration = titleConfiguration,
            dividerConfiguration = dividerConfiguration,
            sheetColors = sheetColors,
            isDragIconEnabled = isDragIconEnabled,
            onDismiss = { onDismiss?.invoke(selectedItemsTemp.toList()) }
        ) { index, item ->
            CheckBoxItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (title != null && index == 0) itemConfiguration.padding else 0.dp),
                item = item,
                isChecked = selectedItemsTemp.contains(item),
                itemConfiguration = itemConfiguration,
                checkboxColors = checkboxColors,
                fontFamily = fontFamily,
                onCheckedChange = { isChecked ->
                    if (isChecked) selectedItemsTemp.add(item)
                    else selectedItemsTemp.remove(item)
                }
            )
        }
    }
}

@Composable
private fun CheckBoxItem(
    modifier: Modifier = Modifier,
    item: String,
    isChecked: Boolean,
    itemConfiguration: ItemConfiguration = ItemConfiguration(),
    checkboxColors: CheckboxColors = CheckboxDefaults.colors(),
    fontFamily: FontFamily = FontFamily.Default,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            colors = checkboxColors
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
}