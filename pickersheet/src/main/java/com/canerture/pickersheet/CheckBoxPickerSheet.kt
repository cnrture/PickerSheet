package com.canerture.pickersheet

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
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
    rippleEffectEnabled: Boolean = true,
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
            onDismiss = { onDismiss?.invoke(selectedItemsTemp.toList()) },
            content = { index, item ->
                CheckBoxItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = if (title != null && index == 0) itemConfiguration.padding else 0.dp),
                    item = item,
                    isChecked = selectedItemsTemp.contains(item),
                    rippleEffectEnabled = rippleEffectEnabled,
                    itemConfiguration = itemConfiguration,
                    checkboxColors = checkboxColors,
                    fontFamily = fontFamily,
                    onCheckedChange = { isChecked ->
                        if (isChecked) selectedItemsTemp.add(item)
                        else selectedItemsTemp.remove(item)
                    }
                )
            }
        )
    }
}

@Composable
private fun CheckBoxItem(
    modifier: Modifier = Modifier,
    item: String,
    isChecked: Boolean,
    rippleEffectEnabled: Boolean,
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
            colors = checkboxColors,
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun asdas() {
    val sheetState = rememberModalBottomSheetState()

    var textPickerShowState by rememberSaveable { mutableStateOf(false) }
    var checkBoxPickerShowState by rememberSaveable { mutableStateOf(false) }
    var radioButtonPickerShowState by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onClick = { textPickerShowState = true }
        ) {
            Text(text = "Show Text Picker Sheet")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onClick = { checkBoxPickerShowState = true }
        ) {
            Text(text = "Show CheckBox Picker Sheet")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onClick = { radioButtonPickerShowState = true }
        ) {
            Text(text = "Show RadioButton Picker Sheet")
        }
    }

    TextPickerSheet(
        modifier = Modifier.padding(16.dp),
        sheetState = sheetState,
        showState = textPickerShowState,
        list = listOf(
            "First",
            "Second",
            "Third",
            "Fourth"
        ),
        title = "Title",
        selectedItem = "Second",
        isDragIconEnabled = true,
        rippleEffectEnabled = true,
        fontFamily = FontFamily.Default,
        dividerConfiguration = DividerConfiguration(isEnabled = true),
        titleConfiguration = TitleConfiguration(),
        itemConfiguration = ItemConfiguration(),
        sheetColors = PickerSheetColors(),
        selectedIconConfiguration = SelectedIconConfiguration(),
        onDismiss = {
            textPickerShowState = false
        }
    )

    CheckBoxPickerSheet(
        modifier = Modifier.padding(16.dp),
        sheetState = sheetState,
        showState = checkBoxPickerShowState,
        list = listOf(
            "First",
            "Second",
            "Third",
            "Fourth"
        ),
        title = "Title",
        selectedItems = listOf(),
        isDragIconEnabled = true,
        rippleEffectEnabled = true,
        fontFamily = FontFamily.Default,
        dividerConfiguration = DividerConfiguration(),
        titleConfiguration = TitleConfiguration(),
        itemConfiguration = ItemConfiguration(),
        sheetColors = PickerSheetColors(),
        checkboxColors = CheckboxDefaults.colors(),
        onDismiss = {
            checkBoxPickerShowState = false
        }
    )

    RadioButtonPickerSheet(
        modifier = Modifier.padding(16.dp),
        sheetState = sheetState,
        showState = radioButtonPickerShowState,
        list = listOf(
            "First",
            "Second",
            "Third",
            "Fourth"
        ),
        title = "Title",
        selectedItem = "Second",
        isDragIconEnabled = true,
        rippleEffectEnabled = true,
        fontFamily = FontFamily.Default,
        dividerConfiguration = DividerConfiguration(),
        titleConfiguration = TitleConfiguration(),
        itemConfiguration = ItemConfiguration(),
        sheetColors = PickerSheetColors(),
        radioButtonColors = RadioButtonDefaults.colors(),
        onDismiss = {
            radioButtonPickerShowState = false
        }
    )
}