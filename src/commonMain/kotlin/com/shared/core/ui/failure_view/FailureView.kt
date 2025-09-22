package com.shared.core.ui.failure_view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun FailureView(
    modifier: Modifier = Modifier,
    errText: String = " Failed to load content",
    tapText: StringResource,
    icon: DrawableResource,
    color: Color = Color.Transparent,
    onTapToRefresh: () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = color,
        contentColor = MaterialTheme.colorScheme.primary,
        onClick = onTapToRefresh
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(),
        ) {
            Icon(
                painter = painterResource(resource = icon),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = errText, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(resource = tapText),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}