package com.example.pruebadelaapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class PantallaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            perfil()

        }
    }
    
    val CAMERA_REQUEST_CODE =-1

    @Composable
    fun perfil() {
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { topBarPerfil() },
        ) {

            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)) {
                Spacer(modifier = Modifier.padding(14.dp))
                nombreRegistro(Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(8.dp))
                email(Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(8.dp))
                nacionalidad(Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(8.dp))
                tomarFoto(Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(10.dp))
            fotoPerfil(Modifier.align(Alignment.CenterHorizontally))

                Spacer(modifier = Modifier.padding(40.dp))
                botonGuardarCambios(Modifier.align(Alignment.CenterHorizontally))
            }
        }

    }

    @Composable
    fun nombreRegistro(modifier: Modifier) {
        var nombreRegistro by remember {
            mutableStateOf("")
        }
        TextField(value = nombreRegistro, onValueChange = {
            nombreRegistro = it
        },
            modifier = modifier.width(300.dp),
            placeholder = { Text(text = "Nombre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(textColor = Color(0xFF0F0F0F),
                backgroundColor = Color(0xFF939599))
        )
    }

    @Composable
    fun email(modifier: Modifier) {
        var Email by remember {
            mutableStateOf("")
        }
        TextField(value = Email, onValueChange = {
            Email = it
        },
            modifier = modifier.width(300.dp),
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(textColor = Color(0xFF0F0F0F),
                backgroundColor = Color(0xFF939599))
        )
    }

    @Composable
    fun nacionalidad(modifier: Modifier) {
        var nacionalidad by remember {
            mutableStateOf("")
        }
        TextField(value = nacionalidad, onValueChange = {
            nacionalidad = it
        },
            modifier = modifier.width(300.dp),
            placeholder = { Text(text = "Nacionalidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(textColor = Color(0xFF0F0F0F),
                backgroundColor = Color(0xFF939599))
        )
    }


    @Composable
    fun tomarFoto(modifier: Modifier) {
        Box(
            modifier = modifier
                .width(300.dp)
                .height(55.dp)
                .background(color = Color(0xFF939599))
                .padding(vertical = 15.dp, horizontal = 10.dp),
            ) {
            Row(modifier = modifier) {
                Text(text = "Tomar Foto", fontSize = 16.sp, color = Color.DarkGray)
                Spacer(modifier = modifier.padding(horizontal = 80.dp))
              }
        }
    }



    @Composable
    fun fotoPerfil(modifier: Modifier){
        var isfoto  by remember {mutableStateOf(false) }

            val imagen = if (isfoto) R.drawable.avatar  else R.drawable.iconoavatar
                   Image(painterResource(id = imagen ),
                       contentDescription = "",
                       modifier = modifier
                           .width(150.dp)
                           .clickable {
                               mostrarCamareDeFoto()
                           })
    }
    fun mostrarCamareDeFoto(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(intent, CAMERA_REQUEST_CODE)

        } catch (e: ActivityNotFoundException) {
            // Mostrar error, no hay apps que tomen fotos
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Recibo el resultado
        if (requestCode== CAMERA_REQUEST_CODE && requestCode == Activity.RESULT_OK) {
            // Por default la cámara de Android guarda la imagen en "data"
            val fotoo = data?.extras?.get("data") as Bitmap

        }
       // Es otro request code o hubo error

    }

    @Composable
    fun botonGuardarCambios(modifier: Modifier) {
        Button(modifier = modifier
            .height(50.dp)
            .width(200.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF396AE9)),
            onClick = { /*TODO*/ }) {
            Text(text = "Guardar Cambios")
        }

    }

    @Composable
    fun topBarPerfil(
    ) {
        var showMenu by remember {
            mutableStateOf(false)
        }
        TopAppBar(
            title = { Text(text = "Mi Perfil", modifier = Modifier, Color.White) },
            backgroundColor = Color.Black,
            actions = {
                IconButton(onClick = {   startActivity(Intent(this@PantallaPerfil,
                    MainActivity::class.java))
                finish()}) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = "icono menu")
                }
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false },
                    modifier = Modifier
                        .width(110.dp)
                        .background(color = Color(0xFF335ABD)),)
                {
                }
            }
        )
    }
}










