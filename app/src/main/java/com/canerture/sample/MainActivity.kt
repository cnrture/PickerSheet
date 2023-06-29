@file:OptIn(ExperimentalMaterial3Api::class)

package com.canerture.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.canerture.pickersheet.CheckBoxPickerSheet
import com.canerture.pickersheet.RadioButtonPickerSheet
import com.canerture.pickersheet.TextPickerSheet
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

                    var isOpenTextPicker by rememberSaveable { mutableStateOf(false) }
                    var isOpenCheckBoxPicker by rememberSaveable { mutableStateOf(false) }
                    var isOpenRadioButtonPicker by rememberSaveable { mutableStateOf(false) }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { isOpenTextPicker = true }) {
                            Text(text = "Show Text Picker Sheet")
                        }
                        Button(onClick = { isOpenCheckBoxPicker = true }) {
                            Text(text = "Show CheckBox Picker Sheet")
                        }
                        Button(onClick = { isOpenRadioButtonPicker = true }) {
                            Text(text = "Show RadioButton Picker Sheet")
                        }
                    }

                    TextPickerSheet(
                        sheetState = sheetState,
                        isOpen = isOpenTextPicker,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        title = "Sırala",
                        list = listOf(
                            "First",
                            "Second",
                            "Third",
                            "Fourth"
                        ),
                        dividerEnabled = true,
                        selectedItem = "Second",
                        onItemClick = {
                            //isOpenTextPicker = false
                        },
                        onDismiss = {
                            isOpenTextPicker = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val sheetState = rememberModalBottomSheetState()

        var isOpenTextPicker by rememberSaveable { mutableStateOf(false) }
        var isOpenCheckBoxPicker by rememberSaveable { mutableStateOf(false) }
        var isOpenRadioButtonPicker by rememberSaveable { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { isOpenTextPicker = true }) {
                Text(text = "Show Text Picker Sheet")
            }
            Button(onClick = { isOpenCheckBoxPicker = true }) {
                Text(text = "Show CheckBox Picker Sheet")
            }
            Button(onClick = { isOpenRadioButtonPicker = true }) {
                Text(text = "Show RadioButton Picker Sheet")
            }
        }

        TextPickerSheet(
            sheetState = sheetState,
            isOpen = isOpenTextPicker,
            modifier = Modifier.padding(16.dp),
            list = listOf(
                "First",
                "Second",
                "Third",
                "Fourth"
            ),
            title = "Title",
            dividerEnabled = true,
            selectedItem = "Second",
            onItemClick = {
                //isOpenTextPicker = false
            },
            onDismiss = {
                isOpenTextPicker = false
            }
        )

        CheckBoxPickerSheet(
            sheetState = sheetState,
            isOpen = isOpenCheckBoxPicker,
            modifier = Modifier.padding(16.dp),
            list = listOf(
                "First",
                "Second",
                "Third",
                "Fourth"
            ),
            selectedItems = listOf("First", "Second"),
            title = "Title",
            dividerEnabled = true,
            onDismiss = {
                isOpenCheckBoxPicker = false
            }
        )

        RadioButtonPickerSheet(
            sheetState = sheetState,
            isOpen = isOpenRadioButtonPicker,
            modifier = Modifier.padding(16.dp),
            list = listOf(
                "First",
                "Second",
                "Third",
                "Fourth"
            ),
            selectedItem = "Second",
            title = "Title",
            dividerEnabled = true,
            onDismiss = {
                isOpenRadioButtonPicker = false
            }
        )
    }
}