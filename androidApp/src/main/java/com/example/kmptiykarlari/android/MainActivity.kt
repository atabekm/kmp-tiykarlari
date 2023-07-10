package com.example.kmptiykarlari.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptiykarlari.GithubSearcher
import com.example.kmptiykarlari.Greeting
import com.example.kmptiykarlari.UserRepo
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scope = rememberCoroutineScope()
                    var query by remember { mutableStateOf("") }
                    var repos by remember { mutableStateOf(listOf<UserRepo>()) }

                    Column(modifier = Modifier.padding(16.dp)) {
                        GreetingView(Greeting().greet())
                        Spacer(Modifier.height(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedTextField(
                                value = query,
                                onValueChange = {
                                    query = it
                                },
                                label = { Text("Github username") },
                                modifier = Modifier
                                    .width(250.dp)
                                    .padding(end = 16.dp)
                            )
                            Button(onClick = {
                                scope.launch {
                                    repos = GithubSearcher().getUserRepos(query)
                                }
                            }) {
                                Text(
                                    text = "Search repos",
                                )
                            }
                        }
                        RepoList(repos)
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Composable
fun RepoList(repos: List<UserRepo>) {
    LazyColumn {
        items(repos) { repo ->
            Text(
                text = repo.name,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
            repo.description?.let {
                Text(text = it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
