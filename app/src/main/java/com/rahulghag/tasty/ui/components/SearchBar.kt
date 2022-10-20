package com.rahulghag.tasty.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rahulghag.tasty.R
import com.rahulghag.tasty.ui.theme.TastyTheme
import com.rahulghag.tasty.ui.theme.searchBarBackgroundColor

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    hintText: String,
    onSearchBarClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .clip(RoundedCornerShape(64.dp))
            .clickable {
                onSearchBarClick()
            },
        color = MaterialTheme.colors.searchBarBackgroundColor
    ) {
        Row(
            modifier = childModifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface
            )
            Text(
                text = hintText,
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    TastyTheme {
        Surface {
            SearchBar(
                hintText = stringResource(id = R.string.hint_search_for_recipes),
                onSearchBarClick = {}
            )
        }
    }
}