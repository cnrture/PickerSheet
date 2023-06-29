package com.canerture.pickersheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.composed


fun androidx.compose.ui.Modifier.clickableWithoutRipple(onClick: () -> Unit) = composed(
    factory = {
        this.then(
            clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick() }
            )
        )
    }
)