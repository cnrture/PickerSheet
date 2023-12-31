package com.canerture.pickersheet.sheets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import com.canerture.pickersheet.DividerConfiguration
import com.canerture.pickersheet.ItemConfiguration
import com.canerture.pickersheet.NoRippleInteractionSource
import com.canerture.pickersheet.PickerSheet
import com.canerture.pickersheet.PickerSheetColors
import com.canerture.pickersheet.TitleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButtonPickerSheet(
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
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(),
    onDismiss: ((String) -> Unit)? = null
) {

    var selectedItemTemp by rememberSaveable { mutableStateOf(selectedItem.orEmpty()) }

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
                RadioButtonItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = item,
                    isSelected = selectedItemTemp == item,
                    rippleEffectEnabled = rippleEffectEnabled,
                    itemConfiguration = itemConfiguration,
                    fontFamily = fontFamily,
                    radioButtonColors = radioButtonColors,
                    onItemClick = { selectedItemTemp = it }
                )
            }
        )
    }
}

@Composable
private fun RadioButtonItem(
    modifier: Modifier = Modifier,
    item: String,
    isSelected: Boolean,
    rippleEffectEnabled: Boolean,
    itemConfiguration: ItemConfiguration = ItemConfiguration(),
    fontFamily: FontFamily = FontFamily.Default,
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(),
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onItemClick(item) },
            colors = radioButtonColors,
            interactionSource = remember {
                if (rippleEffectEnabled) MutableInteractionSource()
                else NoRippleInteractionSource()
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
}