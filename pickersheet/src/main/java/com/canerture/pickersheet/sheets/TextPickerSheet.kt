package com.canerture.pickersheet.sheets

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.canerture.pickersheet.DividerConfiguration
import com.canerture.pickersheet.ItemConfiguration
import com.canerture.pickersheet.NoRippleInteractionSource
import com.canerture.pickersheet.PickerSheet
import com.canerture.pickersheet.PickerSheetColors
import com.canerture.pickersheet.SelectedIconConfiguration
import com.canerture.pickersheet.TitleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextPickerSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    showState: Boolean = false,
    list: List<String>,
    title: String? = null,
    selectedItem: String? = null,
    isDragIconEnabled: Boolean = true,
    rippleEffectEnabled: Boolean = false,
    fontFamily: FontFamily = FontFamily.Default,
    dividerConfiguration: DividerConfiguration = DividerConfiguration(),
    titleConfiguration: TitleConfiguration = TitleConfiguration(),
    itemConfiguration: ItemConfiguration = ItemConfiguration(),
    sheetColors: PickerSheetColors = PickerSheetColors(),
    selectedIconConfiguration: SelectedIconConfiguration = SelectedIconConfiguration(),
    onDismiss: ((String) -> Unit)? = null
) {

    var selectedItemTemp by remember { mutableStateOf(selectedItem.orEmpty()) }

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
            onDismiss = { onDismiss?.invoke(selectedItemTemp) },
            content = { item ->
                TextItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = item,
                    selectedItemTemp = selectedItemTemp,
                    rippleEffectEnabled = rippleEffectEnabled,
                    itemConfiguration = itemConfiguration,
                    selectedIconConfiguration = selectedIconConfiguration,
                    fontFamily = fontFamily,
                    onItemClick = { selectedItemTemp = it }
                )
            }
        )
    }
}

@Composable
private fun TextItem(
    modifier: Modifier = Modifier,
    item: String,
    selectedItemTemp: String,
    rippleEffectEnabled: Boolean,
    itemConfiguration: ItemConfiguration = ItemConfiguration(),
    selectedIconConfiguration: SelectedIconConfiguration = SelectedIconConfiguration(),
    fontFamily: FontFamily = FontFamily.Default,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember {
                    if (rippleEffectEnabled) MutableInteractionSource()
                    else NoRippleInteractionSource()
                },
                indication = if (rippleEffectEnabled) LocalIndication.current else null,
                onClick = { onItemClick(item) }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = itemConfiguration.padding)
                .weight(1f),
            text = item,
            fontSize = itemConfiguration.size,
            fontFamily = fontFamily,
            color = itemConfiguration.color
        )
        if (selectedItemTemp == item) Icon(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(selectedIconConfiguration.size),
            painter = painterResource(id = selectedIconConfiguration.icon),
            tint = selectedIconConfiguration.color,
            contentDescription = null
        )
    }
}
