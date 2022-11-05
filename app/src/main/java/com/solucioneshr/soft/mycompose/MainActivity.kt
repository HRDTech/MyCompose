package com.solucioneshr.soft.mycompose

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucioneshr.soft.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Scaffold (
                        topBar = {
                            TopAppBar (
                                backgroundColor = Color.Blue,
                                  title = {
                                      // in the top bar we are specifying tile as a text
                                      Text(
                                          // on below line we are specifying
                                          // text to display in top app bar.
                                          text = "Ejemplo de uso de Compose",

                                          // on below line we are specifying
                                          // modifier to fill max width.
                                          modifier = Modifier.fillMaxSize(),

                                          // on below line we are specifying text alignment.
                                          textAlign = TextAlign.Center,

                                          // on below line we are specifying color for our text.
                                          color = Color.White
                                      )
                                  }
                            )
                        }
                    ){
                        gridView(LocalContext.current)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun gridView(context: Context) {
    // on below line we are creating and initializing our array list
    lateinit var courseList: List<GridModal>
    courseList = ArrayList()

    // on below line we are adding data to our list.
    courseList = courseList + GridModal(0,"Tomar Foto", R.drawable.ic_camera)
    courseList = courseList + GridModal(1,"RestAPI", R.drawable.ic_services)

    // on below line we are adding lazy
    // vertical grid for creating a grid view.
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp)
    ) {
        items(courseList.size) {
            // on below line we are creating a
            // card for each item of our grid view.
            Card(
                // inside our grid view on below line we are
                // adding on click for each item of our grid view.
                onClick = {
                    if (courseList[it].id == 0){
                        context.startActivity(Intent(context, CameraActivity::class.java))
                    } else if (courseList[it].id == 1){
                        context.startActivity(Intent(context, ServiceActivity::class.java))
                    }

                },

                // on below line we are adding padding from our all sides.
                modifier = Modifier.padding(8.dp),

                // on below line we are adding elevation for the card.
                elevation = 6.dp
            ) {
                // on below line we are creating a column on below sides.
                Column(
                    // on below line we are adding padding
                    // padding for our column and filling the max size.
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp),

                    // on below line we are adding
                    // horizontal alignment for our column
                    horizontalAlignment = Alignment.CenterHorizontally,

                    // on below line we are adding
                    // vertical arrangement for our column
                    verticalArrangement = Arrangement.Center
                ) {
                    // on below line we are creating image for our grid view item.
                    Image(
                        // on below line we are specifying the drawable image for our image.
                        painter = painterResource(id = courseList[it].languageImg),

                        // on below line we are specifying
                        // content description for our image
                        contentDescription = "Javascript",

                        // on below line we are setting height
                        // and width for our image.
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .padding(5.dp)
                    )

                    // on the below line we are adding a spacer.
                    Spacer(modifier = Modifier.height(9.dp))

                    // on below line we are creating
                    // a text for our grid view item
                    Text(
                        // inside the text on below line we are
                        // setting text as the language name
                        // from our modal class.
                        text = courseList[it].languageName,

                        // on below line we are adding padding
                        // for our text from all sides.
                        modifier = Modifier.padding(4.dp),

                        // on below line we are
                        // adding color for our text
                        color = Color.Black
                    )
                }
            }
        }
    }
}