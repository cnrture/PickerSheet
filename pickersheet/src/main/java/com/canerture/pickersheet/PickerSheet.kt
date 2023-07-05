package com.canerture.pickersheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PickerSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    list: List<String>,
    title: String? = null,
    fontFamily: FontFamily = FontFamily.Default,
    titleConfiguration: TitleConfiguration = TitleConfiguration(),
    dividerConfiguration: DividerConfiguration = DividerConfiguration(),
    sheetColors: PickerSheetColors = PickerSheetColors(),
    isDragIconEnabled: Boolean,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.(Int, String) -> Unit,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        scrimColor = sheetColors.scrimColor,
        containerColor = sheetColors.backgroundColor,
        dragHandle = {
            if (isDragIconEnabled) BottomSheetDefaults.DragHandle()
        },
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = modifier
                .navigationBarsPadding()
                .fillMaxWidth(),
        ) {
            PickerSheetTitle(title, isDragIconEnabled, titleConfiguration, fontFamily)
            title?.let {
                Spacer(modifier = Modifier.height(16.dp))
            }
            LazyColumn {
                itemsIndexed(list) { index, item ->
                    if (dividerConfiguration.isEnabled) {
                        Divider(
                            color = dividerConfiguration.color,
                            thickness = dividerConfiguration.thickness
                        )
                    }
                    content(this@Column, index, item)
                    if (dividerConfiguration.isEnabled && index == list.lastIndex) {
                        Divider(
                            color = dividerConfiguration.color,
                            thickness = dividerConfiguration.thickness
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PickerSheetTitle(
    title: String?,
    isDragIconEnabled: Boolean,
    titleConfiguration: TitleConfiguration = TitleConfiguration(),
    fontFamily: FontFamily = FontFamily.Default
) {
    title?.let {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = if (isDragIconEnabled) 0.dp else 12.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = titleConfiguration.size,
            fontFamily = fontFamily,
            color = titleConfiguration.color,
            textAlign = titleConfiguration.align
        )
    }
}