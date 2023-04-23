package com.example.tpmobiltres

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpmobiltres.ui.theme.TpMobilTresTheme


class PantallaPrincipal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pantallaInicio()
        }
    }


    @Composable
    fun pantallaInicio() {

        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { topBar() },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            )
            {
                imagenLogo()
                Spacer(modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.padding(20.dp))
                partidaSolitaria(Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(20.dp))
                partidaVersus(Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }

    @Composable
    fun topBar() {
        var showMenu by remember {
            mutableStateOf(false)
        }
        TopAppBar(
            title = { Text(text = "", modifier = Modifier, Color.White) },
            backgroundColor = Color.Black,
            actions = {
                IconButton(onClick = { showMenu = !showMenu }) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
                        contentDescription = "icono menu")
                }
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false },
                    modifier = Modifier.width(105.dp).background(color = Color(0xFF335ABD)),

                    )
                {

                    DropdownMenuItem(onClick = {
                        startActivity(Intent(this@PantallaPrincipal,
                            PantallaPerfil::class.java))
                        finish()
                    }) {
                        Icon(imageVector = Icons.Filled.Person,
                            contentDescription = "Perfil")
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Perfil",
                            color = Color(0xFF070707))
                    }
                    DropdownMenuItem(onClick = {finish()}) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "volver")
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Salir",
                            color = Color(0xFF070707))
                    }
                }
            }

        )
    }


    @Composable
    fun partidaSolitaria(modifier: Modifier) {
        Button(
            modifier = modifier
                .height(50.dp)
                .width(180.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF396AE9)),

            onClick = { /*aca cambiaria a la pantalla del juego solitario*/ }) {
            Text(text = "Solitario")

        }
    }

    @Composable
    fun partidaVersus(modifier: Modifier) {
        Button(
            modifier = modifier
                .height(50.dp)
                .width(180.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF396AE9)),


            onClick = { /*aca cambiaria a la pantalla de mostrar el qr y escanear*/ }) {
            Text(text = "Versus")

        }
    }

    @Composable
    fun imagenLogo() {
        Image(
            painter = painterResource(id = R.drawable.fondonuevo),
            contentDescription = "imagen logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
        )
    }

}

