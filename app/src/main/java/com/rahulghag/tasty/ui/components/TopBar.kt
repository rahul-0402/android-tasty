package com.rahulghag.tasty.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rahulghag.tasty.R
import com.rahulghag.tasty.ui.theme.TastyTheme
import com.rahulghag.tasty.ui.theme.topAppBarBackgroundColor
import com.rahulghag.tasty.ui.theme.topAppBarContentColor

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: ImageVector,
    onNavigationIconClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.topAppBarContentColor,
                )
            }
        },
        actions = actions,
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    TastyTheme {
        Surface {
            TopBar(title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                )
            },
                navigationIcon = Icons.Rounded.ArrowBack,
                onNavigationIconClick = {},
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.Share,
                            contentDescription = null,
                            tint = MaterialTheme.colors.topAppBarContentColor
                        )
                    }
                })
        }
    }
}