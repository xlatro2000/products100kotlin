package com.products100.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.products100.viewmodel.ViewModelUsers
import androidx.compose.runtime.getValue
import com.products100.model.UserResult
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.room.util.getColumnIndex
import coil3.compose.AsyncImage
import com.products100.constants.APPTitlesAndDesc


@Composable
fun UsersScreen(
    NavToBack: () -> Unit,
    viewModel:ViewModelUsers = hiltViewModel(),
)
{
    val listUsers by viewModel.listUsers.collectAsState(emptyList())
    Log.d("CANTIDAD ITEMS", "${listUsers.size}" )

    Column {
        Button(
            onClick = {NavToBack()}
        ) {
            Text("${APPTitlesAndDesc.TITLE_GOBACK}")
        }
        LazyColumn() {
            items(listUsers){ user->
                UsersCard(user)
            }
        }
    }

}


@Composable
fun UsersCard(user: UserResult){

    Spacer(Modifier.padding(all = 10.dp))

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {

        Column {
            Row {
                AsyncImage(
                    model = "${user.picture.large}",
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(CircleShape)
                )
                Spacer(Modifier.padding(all = 5.dp))
                Text("${user.name.title + " " + user.name.first}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
//    Column {
//        Row (modifier = Modifier
//            .background(MaterialTheme.colorScheme.primary)
//            .fillMaxWidth()
//            .height(100.dp)){
//
//            Spacer(Modifier.padding(all = 5.dp))
//            ElevatedCard (
//                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//            ){
//                Text(text = "Hello, world!")
//            }
//        }

//    }

}