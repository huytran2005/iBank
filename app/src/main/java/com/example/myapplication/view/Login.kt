package com.example.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Neutral1
import com.example.myapplication.ui.theme.Neutral4
import com.example.myapplication.ui.theme.Neutral6
import com.example.myapplication.ui.theme.Primary1
import com.example.myapplication.ui.theme.Primary4


@SuppressLint("ContextCastToActivity")
@Composable
fun LoginScreen() {
    var numberphone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
Scaffold { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Primary1)
    ) {
        Text(
            text = "Sign In",
            color = Neutral6,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(start = 60.dp)
                .padding(vertical = 25.dp) // Thêm padding phía dưới header
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 50.dp
                    )
                )
                .background(Neutral6),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome Back",Modifier.fillMaxWidth().padding(top = 24.dp, start = 24.dp),
                fontWeight = FontWeight.Bold,
                color = Primary1,
                fontSize = 24.sp)
            Image(painter = painterResource(R.drawable.lg_login),
                contentDescription = "Background of Login",
                Modifier.size(150.dp))
            Spacer(Modifier.padding(12.dp))
            //Number Phone
            OutlinedTextField(
                value = numberphone,
                onValueChange = {numberphone=it},
                Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                placeholder = { Text("Your Number Phone", color = Neutral4) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Neutral1,
                    disabledBorderColor = Neutral4,
                    focusedTextColor = Neutral1,
                    unfocusedTextColor = if(numberphone.isEmpty()) Neutral4 else Neutral1,
                )
            )
            Spacer(Modifier.padding(12.dp))
            //Password
            OutlinedTextField(
                value = password,
                onValueChange = {password=it},
                Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                placeholder = { Text("Your Password", color = Neutral4) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Neutral1,
                    disabledBorderColor = Neutral4,
                    focusedTextColor = Neutral1,
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Text("Forgot your password ?",Modifier.fillMaxWidth().padding(bottom =40.dp, end = 24.dp), textAlign = TextAlign.End)
            Button(onClick = {},
                Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    if(numberphone.isEmpty()||password.isEmpty()) Primary4 else Primary1

                )
            ) {
                Text("Sign In")
            }
            Spacer(Modifier.padding(10.dp))
            Image(painter = painterResource(R.drawable.ic_fingerprint),
                contentDescription = "Fingerprint",
                modifier = Modifier.clickable{(authenticateWithFingerprint(context))}.size(50.dp))
            Row (Modifier.padding(vertical = 10.dp)){
                Text("Don't have an account? ")
                Text("Sign Up ", color = Primary1, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.weight(0.1f))


        }
    }
}
}

fun authenticateWithFingerprint(context: Context) {
    val executor = ContextCompat.getMainExecutor(context)

    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,  // ✔️ sửa ComponentActivity → FragmentActivity
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(context, "Xác thực thành công!", Toast.LENGTH_SHORT).show()
                // TODO: chuyển màn hình
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(context, "Lỗi: $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(context, "Vân tay không đúng!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Xác thực vân tay")
        .setSubtitle("Chạm vào cảm biến để đăng nhập")
        .setNegativeButtonText("Huỷ")
        .build()

    biometricPrompt.authenticate(promptInfo)
}


@Preview(showBackground = true)
@Composable
fun Preview(){
    LoginScreen()
}