package com.rahulghag.tasty.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahulghag.tasty.R
import com.rahulghag.tasty.ui.theme.TastyTheme
import com.rahulghag.tasty.utils.UiMessage

@Composable
fun ErrorScreen(
    errorMessage: UiMessage,
    onTryAgain: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage.asString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = {
                onTryAgain()
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Refresh,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.label_try_again),
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorScreenPreview() {
    TastyTheme {
        Surface {
            ErrorScreen(
                errorMessage = UiMessage.StringResource(R.string.error_something_went_wrong),
                onTryAgain = {}
            )
        }
    }
}