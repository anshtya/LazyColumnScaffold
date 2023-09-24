package com.anshtya.lazycolumnscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.anshtya.lazycolumnscaffold.ui.theme.LazyColumnScaffoldTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnScaffoldTheme {
                val mainViewModel: MainViewModel by viewModels()
                val lazyListState = rememberLazyListState()
                val shouldScrollUp by mainViewModel.shouldScrollUp.collectAsState()
                mainViewModel.updateScrollPosition(lazyListState.firstVisibleItemIndex)
                val topAppBarPosition by animateFloatAsState(if (shouldScrollUp) -200f else 0f, label = "")
                val bottomBarPosition by animateFloatAsState(if (shouldScrollUp) +200f else 0f, label = "")
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Home") },
                            modifier = Modifier.graphicsLayer {
                                translationY = topAppBarPosition
                            }
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier.graphicsLayer {
                                translationY = bottomBarPosition
                            }
                        ) {
                            NavigationBarItem(
                                selected = false,
                                onClick = {},
                                icon = {
                                    Image(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = null
                                    )
                                }
                            )

                            NavigationBarItem(
                                selected = false,
                                onClick = {},
                                icon = {
                                    Image(
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {}
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier.padding(15.dp)
                            ) {
                                Image(
                                    imageVector = Icons.Default.Create,
                                    contentDescription = null
                                )
                                Text("Some Text")
                            }
                        }
                    }
                ) {
                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        items((1..20).toList()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            ) {
                                Text(it.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}