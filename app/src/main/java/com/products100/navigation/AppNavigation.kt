package com.products100.navigation

import android.content.ClipData
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.products100.model.BookModel
import com.products100.screens.BooksScreen
import com.products100.screens.FirstMenu
import com.products100.screens.InsertScreen
import com.products100.screens.UpdateScreen

import com.products100.screens.UsersScreen
import java.lang.reflect.Type


@Composable
fun AppNavigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavStartScreen ){

        composable<NavStartScreen>{
            FirstMenu(
                { navController.navigate(NavUserScreen) },
                { navController.navigate(NavBooksScreen) }
            )
        }

        composable<NavUserScreen>{
            UsersScreen ({ navController.navigate(NavStartScreen) })
        }

        composable<NavBooksScreen> {
            BooksScreen(
                { navController.navigate(NavStartScreen) },
                { navController.navigate(NavInsertScreen) },
                { navController.navigate(NavUpdateScreen) }
            )
        }

        composable<NavInsertScreen> {
            InsertScreen({ navController.navigate(NavBooksScreen) })
        }

        composable(
            "update/{bookToUpdate}"
         )
        {   backStackEntry ->
            val json = backStackEntry.arguments?.getString("bookToUpdate")
            val item:BookModel = Gson().fromJson(json, BookModel::class.java)
            UpdateScreen(item, { navController.navigate(NavBooksScreen) })
        }
    }
}