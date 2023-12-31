# PickerSheet [![](https://jitpack.io/v/cnrture/PickerSheet.svg)](https://jitpack.io/#cnrture/PickerSheet)

## Implementation
```kotlin
allprojects { 
    repositories {
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    implementation 'com.github.cnrture:PickerSheet:Tag'
}
```

## Default Preview
| Text | CheckBox | RadioButton |
| ------- | -------------------- | -------------------- |
|<img src="https://github.com/cnrture/PickerSheet/assets/29903779/36bd1dd3-22e1-4b5f-bae3-e447c1328c22" width="280" height="540"/>|<img src="https://github.com/cnrture/PickerSheet/assets/29903779/603e43a3-4f87-4f71-a070-c762b7113dd5" width="280" height="540"/>|<img src="https://github.com/cnrture/PickerSheet/assets/29903779/996e7b76-e1d4-4843-a09d-965f644ffef5" width="280" height="540"/>|

## Usage

### TextPickerSheet

```kotlin
Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
) {
    val sheetState = rememberModalBottomSheetState()

    var textPickerShowState by rememberSaveable { mutableStateOf(false) }

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
    }

    TextPickerSheet(
        modifier = Modifier.padding(16.dp),
        sheetState = sheetState,
        showState = textPickerShowState,
        list = listOf("First", "Second", "Third", "Fourth"),
        title = "Title",
        selectedItem = "Third",
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
}
```

### CheckBoxPickerSheet
```kotlin
Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
) {
    val sheetState = rememberModalBottomSheetState()

    var checkBoxPickerShowState by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onClick = { checkBoxPickerShowState = true }
        ) {
            Text(text = "Show CheckBox Picker Sheet")
        }
    }

    CheckBoxPickerSheet(
        modifier = Modifier.padding(16.dp),
        sheetState = sheetState,
        showState = checkBoxPickerShowState,
        list = listOf("First", "Second", "Third", "Fourth"),
        title = "Title",
        selectedItems = listOf("Third"),
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
}
```

### RadioButtonPickerSheet
```kotlin
Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
) {
    val sheetState = rememberModalBottomSheetState()

    var radioButtonPickerShowState by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onClick = { radioButtonPickerShowState = true }
        ) {
            Text(text = "Show RadioButton Picker Sheet")
        }
    }

    RadioButtonPickerSheet(
        modifier = Modifier.padding(16.dp),
        sheetState = sheetState,
        showState = radioButtonPickerShowState,
        list = listOf("First", "Second", "Third", "Fourth"),
        title = "Title",
        selectedItem = "Third",
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
```

## License

```
Copyright 2023 Caner Türe

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
