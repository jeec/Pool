@file:OptIn(ExperimentalMaterial3Api::class)

package com.jerry.mycompose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val TAG = "Scaffold"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSample() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Home")
            }, navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        Log.i(TAG, "ScaffoldSample: click back arrow icon!")
                    }
                )
            }, actions = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "More")
            })
        },
        bottomBar = {
            SootheBottomNavigation()
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "extended fab") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                },
                onClick = {

                }, shape = FloatingActionButtonDefaults.extendedFabShape
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        Text(text = "scaffold body!")
    }
}

@Preview
@Composable
fun ScaffoldSamplePreview() {
    ScaffoldSample()
}


@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            },
            label = {
                Text("HOME")
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text("SETTING")
            },
            selected = false,
            onClick = {}
        )
    }
}