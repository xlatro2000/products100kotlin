package com.products100.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.products100.constants.APPTitlesAndDesc


@Composable
fun FirstMenu(NavToUser:()->Unit, NavToDAO:()->Unit){


    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        CardButtonMenu(
            NavToUser,
            APPTitlesAndDesc.TITLE_CARD_API,
            APPTitlesAndDesc.DESCRIPTION_CARD_API,
            APPTitlesAndDesc.TITLE_BTN_API
        )

        CardButtonMenu(
            NavToDAO,
            APPTitlesAndDesc.TITLE_CARD_DAO,
            APPTitlesAndDesc.DESCRIPTION_CARD_DAO,
            APPTitlesAndDesc.TITLE_BTN_DAO
        )

    }


}


@Composable
fun CardButtonMenu(NavToSite:()->Unit, title:String, description:String, textBtn:String) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .height(150.dp)
            .background(Color.Gray),
        shape = CardDefaults.elevatedShape,
    ) {
        Text(
            title,
            modifier = Modifier.padding(all = 5.dp)
        )
        Text(
            description,
            modifier = Modifier.padding(all = 5.dp)
        )
        ElevatedButton(onClick = {NavToSite()}) {
            Text("${textBtn}")
        }

    }
    Spacer(Modifier.padding(all = 20.dp))
}