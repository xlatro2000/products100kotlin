package com.products100.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.products100.R
import com.products100.constants.APPTitlesAndDesc
import com.products100.model.BookModel
import com.products100.navigation.AppNavigation

import com.products100.viewmodel.ViewModelGetBooks




@Composable
fun BooksScreen(
    NavToBack: () -> Unit,
    NavToInsert: () -> Unit,
    NavToUpdate: () -> Unit,
    viewModel: ViewModelGetBooks = hiltViewModel(),
)
{

    val listBooks by viewModel.listBooks.collectAsState(emptyList())
    val itemsBook by remember { mutableStateOf(listBooks) }

    //Log.d("CANTIDAD ITEMS", "${listBooks.size}" )
    Column (
        modifier = Modifier
                        .fillMaxSize()
                        .background( color = MaterialTheme.colorScheme.secondary)
    ){
        Row {
            IconButton(
                modifier = Modifier.background( color = Color(0xFFFFE587)),
                onClick = {NavToBack()}
            ) {
                Icon(
                    painter = painterResource(R.drawable.goback),
                    contentDescription = APPTitlesAndDesc.TITLE_GOBACK
                )
            }
            Spacer(Modifier.padding(start = 50.dp))
            IconButton(
                modifier = Modifier.background( color = Color(0xFF99FF87)),
                onClick = { NavToInsert() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.addnew),
                    contentDescription = APPTitlesAndDesc.TITLE_INSERT
                )
            }
        }
        LazyColumn() {
            items(
                items =  listBooks,
                key = { item -> item.bookid?.toInt() ?: 0 }
            ){ book->
                BookCard({NavToUpdate()}, book, viewModel)
            }
        }
    }
}


@Composable
fun BookCard(NavToUpdate: () -> Unit, book: BookModel, viewModel: ViewModelGetBooks) {

    val context = LocalContext.current

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
                    model = book.bookimage,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(CircleShape)
                )
                Spacer(Modifier.padding(start = 50.dp))
                Column{
                    Text(
                        book.bookname,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.padding(all = 2.dp))
                    Row{
                        IconButton (
                            modifier = Modifier.background(Color(0xFF87E1FF)),
                            onClick = {
                                NavToUpdate()
                            }
                        ){
                            Icon(
                                painter = painterResource(R.drawable.edit),
                                contentDescription = APPTitlesAndDesc.TITLE_UPDATE
                            )
                        }
                        Spacer(Modifier.padding(all = 5.dp))
                        IconButton (
                            modifier = Modifier.background( color = Color(0xFFFF9E87)),
                            onClick = {
                                val result = viewModel.deleteBook(book.bookid)
                                val toast = Toast.makeText(context, APPTitlesAndDesc.MSG_DELETED, Toast.LENGTH_SHORT)
                                toast.setGravity(android.view.Gravity.CENTER,0,0)
                                toast.show()
                                //Log.d("ID BOOK", result.toString())


                            }
                        ){
                            Icon(
                                painter = painterResource(R.drawable.delete),
                                contentDescription = APPTitlesAndDesc.TITLE_DELETE
                            )
                        }
                    }

                }
            }
        }
    }
}