package com.selfproject.cordsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.cordsapp.R
import com.selfproject.cordsapp.ui.theme.CordsAppTheme

@Composable
fun CustomTitleBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClicked: () -> Unit
) {
    Box( modifier = modifier
        .height(45.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)) {
        Row(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            androidx.compose.material3.IconButton(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        color = Color.Transparent
                    )
                    .padding(8.dp,0.dp,0.dp,0.dp)
                    .size(35.dp),
                onClick = onBackClicked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Locate",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(0.dp)
                        .size(30.dp)
                )
            }

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LightModeCustomTitleBarPreview() {
    CordsAppTheme(darkTheme = false) {
        CustomTitleBar(
            title = "Add Point",
            onBackClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModeCustomTitleBarPreview() {
    CordsAppTheme(darkTheme = true) {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {


            CustomTitleBar(
                title = "Add Point",
                onBackClicked = {}
            )
        }
    }
}