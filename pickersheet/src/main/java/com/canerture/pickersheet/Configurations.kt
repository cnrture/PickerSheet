package com.canerture.pickersheet

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ItemConfiguration(
    val color: Color = Color.Black,
    val size: TextUnit = 14.sp,
    val padding: Dp = 16.dp
)

data class TitleConfiguration(
    val color: Color = Color.Black,
    val size: TextUnit = 16.sp,
    val align: TextAlign = TextAlign.Start
)

data class PickerSheetColors(
    val scrimColor: Color = Color.Gray.copy(alpha = 0.6f),
    val backgroundColor: Color = Color.White
)

data class SelectedIconConfiguration(
    val icon: Int = R.drawable.ic_check,
    val color: Color = Color.Black,
    val size: Dp = 16.dp
)

data class DividerConfiguration(
    val isEnabled: Boolean = false,
    val color: Color = Color.Black
)