package com.products100.screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.products100.constants.APPTitlesAndDesc.TITLE_INSERT

//imports para el mutablestateof "value"
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.products100.R
import com.products100.constants.APPTitlesAndDesc
import com.products100.model.BookModel
import com.products100.viewmodel.ViewModelInsertBook


@Composable
fun InsertScreen (
    NavToBack: () -> Unit,
    viewModel: ViewModelInsertBook = hiltViewModel()
){
    val context = LocalContext.current
    val bookItem by viewModel.bookItem.collectAsState(BookModel(bookname = "", bookauthor = "", bookimage = ""))

    var textNameBook by remember { mutableStateOf("") }
    var textDescBook by remember { mutableStateOf("") }
    var textImageBook by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        IconButton(
            modifier = Modifier.background( color = Color(0xFFFFE587)),
            onClick = {NavToBack()}
        ) {
            Icon(
                painter = painterResource(R.drawable.goback),
                contentDescription = APPTitlesAndDesc.TITLE_GOBACK
            )
        }
        Spacer(Modifier.padding(all = 10.dp))
        Text("${TITLE_INSERT}")
        Card(
            modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(20.dp)
                            .align(Alignment.CenterHorizontally),

        ) {
            OutlinedTextField(
                value = textNameBook,
                onValueChange = { newText -> textNameBook = newText },
                label = { Text("${APPTitlesAndDesc.TITLE_TEXTNAMEBOOK}")},
                singleLine = true,
                modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)

            )

            OutlinedTextField(
                value = textDescBook,
                onValueChange = { newText -> textDescBook = newText },
                label = { Text("${APPTitlesAndDesc.TITLE_TEXTDESCBOOK}")},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)

            )

            OutlinedTextField(
                value = textImageBook,
                onValueChange = { newText -> textImageBook = newText },
                label = { Text("${APPTitlesAndDesc.TITLE_TEXTIMAGEBOOK}")},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)

            )

        }
        Spacer(Modifier.padding(all = 10.dp))
        Button(
            onClick = {
                val book = BookModel(
                    bookname = textNameBook,
                    bookauthor = textDescBook,
                    bookimage = textImageBook)
                //Log.d("INICIO INSERCION", "Se inicia la insercion")

                viewModel.insertBook(book)

                //Log.d("FIN INSERCION", "Se Finaliza la insercion")
                Toast.makeText(context, book.toString(), Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary)
        ) {
            Text("${APPTitlesAndDesc.TITLE_INSERT}")
        }
    }
}