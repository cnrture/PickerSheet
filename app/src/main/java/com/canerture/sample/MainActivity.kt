@file:OptIn(ExperimentalMaterial3Api::class)

package com.canerture.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canerture.pickersheet.DividerConfiguration
import com.canerture.pickersheet.ItemConfiguration
import com.canerture.pickersheet.PickerSheetColors
import com.canerture.pickersheet.SelectedIconConfiguration
import com.canerture.pickersheet.TitleConfiguration
import com.canerture.pickersheet.sheets.CheckBoxPickerSheet
import com.canerture.pickersheet.sheets.RadioButtonPickerSheet
import com.canerture.pickersheet.sheets.TextPickerSheet
import com.canerture.sample.ui.theme.SampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                        rippleEffectEnabled = false,
                        fontFamily = FontFamily.Default,
                        dividerConfiguration = DividerConfiguration(
                            isEnabled = false,
                            color = Color.Black,
                            thickness = 1.dp
                        ),
                        titleConfiguration = TitleConfiguration(
                            color = Color.Black,
                            size = 16.sp,
                            align = TextAlign.Start
                        ),
                        itemConfiguration = ItemConfiguration(
                            color = Color.Black,
                            size = 14.sp,
                            padding = 16.dp
                        ),
                        sheetColors = PickerSheetColors(
                            scrimColor = Color.Gray.copy(alpha = 0.6f),
                            backgroundColor = Color.White
                        ),
                        selectedIconConfiguration = SelectedIconConfiguration(
                            icon = com.canerture.pickersheet.R.drawable.ic_check,
                            color = Color.Black,
                            size = 16.dp
                        ),
                        onDismiss = { it: String ->
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
                        rippleEffectEnabled = false,
                        fontFamily = FontFamily.Default,
                        dividerConfiguration = DividerConfiguration(
                            isEnabled = false,
                            color = Color.Black,
                            thickness = 1.dp
                        ),
                        titleConfiguration = TitleConfiguration(
                            color = Color.Black,
                            size = 16.sp,
                            align = TextAlign.Start
                        ),
                        itemConfiguration = ItemConfiguration(
                            color = Color.Black,
                            size = 14.sp,
                            padding = 16.dp
                        ),
                        sheetColors = PickerSheetColors(
                            scrimColor = Color.Gray.copy(alpha = 0.6f),
                            backgroundColor = Color.White
                        ),
                        checkboxColors = CheckboxDefaults.colors(),
                        onDismiss = { it: List<String> ->
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
                        rippleEffectEnabled = false,
                        fontFamily = FontFamily.Default,
                        dividerConfiguration = DividerConfiguration(
                            isEnabled = false,
                            color = Color.Black,
                            thickness = 1.dp
                        ),
                        titleConfiguration = TitleConfiguration(
                            color = Color.Black,
                            size = 16.sp,
                            align = TextAlign.Start
                        ),
                        itemConfiguration = ItemConfiguration(
                            color = Color.Black,
                            size = 14.sp,
                            padding = 16.dp
                        ),
                        sheetColors = PickerSheetColors(
                            scrimColor = Color.Gray.copy(alpha = 0.6f),
                            backgroundColor = Color.White
                        ),
                        radioButtonColors = RadioButtonDefaults.colors(),
                        onDismiss = { it: String ->
                            radioButtonPickerShowState = false
                        }
                    )
                }
            }
        }
    }
}