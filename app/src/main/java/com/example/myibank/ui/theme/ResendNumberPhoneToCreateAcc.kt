package com.example.myibank.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

class ResendNumberPhoneToCreateAcc : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                NavHost(
                    navController = navController,
                    startDestination = "resend_screen"
                ) {
                    composable("resend_screen") {
                        ResendNumberPhoneToCreateAccScreen(navController)
                    }
                    composable("previous_phone") {
                        PreviousPhoneScreen(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun PreviousPhoneScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("This is the previous screen")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResendNumberPhoneToCreateAccScreen(navController: NavController) {
    var code: String by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") },
                navigationIcon = {
                        //val context = LocalContext.current

                    IconButton(onClick = {
                        navController.navigate("previous_screen") // hoặc navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                                 },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    )
                )
            },
            containerColor = Color.White
        ) { innerPadding ->

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
            ) {
                val screenWidth = maxWidth
                val cardWidth = when {
                    screenWidth < 360.dp -> screenWidth * 0.95f
                    screenWidth < 600.dp -> screenWidth * 0.85f
                    else -> 400.dp
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.width(cardWidth),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            // Label
                            Text(
                                text = "Type a code",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // TextField + Resend Button in same row
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = code,
                                    onValueChange = { code = it },
                                    placeholder = { Text("Code") },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Button(
                                    onClick = { /* Resend logic */ },
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .fillMaxHeight(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF81D4FA),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Resend")
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Info Text
                            Text(
                                text = "We texted you a code to verify your phone number (+84) 0398829xxx",
                                fontSize = 14.sp,
                                color = Color(0xFF444444)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "This code will expire 10 minutes after this message. If you don't get a message.",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Create Account button
                            Button(
                                onClick = { /* Create Account action */ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF81D4FA),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "Create New Account")
                            }
                        }
                    }
                }
            }
        }
    }