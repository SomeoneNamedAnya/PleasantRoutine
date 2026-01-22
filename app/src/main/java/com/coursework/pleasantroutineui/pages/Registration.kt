package com.coursework.pleasantroutineui.pages
import android.os.Bundle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coursework.pleasantroutineui.App
import com.coursework.pleasantroutineui.domain.Destinations
import com.coursework.pleasantroutineui.ui.theme.PleasantRoutineUiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PleasantRoutineUiTheme(darkTheme = false) {
                App()
            }
        }

    }
}

class RegistrationViewModel: ViewModel() {
    var login by mutableStateOf("")
    var password by mutableStateOf("")
    var visible by mutableStateOf(false)

    fun onLoginChange(value: String) {
        login = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onVisibleChange() {
        visible = !visible
        println(visible)
    }

}

@Composable
fun RegistrationScreen(nController: NavController, vm: RegistrationViewModel = viewModel(),

) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ){
        Text("Регистрация",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge)

        RegisterField("Логин", vm.login, vm::onLoginChange)

        RegisterField("Пароль", vm.password, vm::onPasswordChange,
            if (vm.visible) VisualTransformation.None else PasswordVisualTransformation(),
            {
                IconButton(onClick = vm::onVisibleChange) {
                    Icon(
                        imageVector = if (vm.visible) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            })

        Button(onClick = {
            vm.onLoginChange("")
            vm.onPasswordChange("")
            nController.navigate(Destinations.USER_ACCOUNT_PAGE.title)

        }, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )) {
            Text("Регистрация")
        }
    }
}

@Composable
fun RegisterField(textField: String, field: String, onFieldChanged: (String) -> Unit,
                  visualTransformation: VisualTransformation = VisualTransformation.None,
                  trailingIcon: @Composable (() -> Unit)? = null)  {

    OutlinedTextField(
        value = field,
        onValueChange = onFieldChanged,
        label = { Text(textField) },

        placeholder = { Text("Введите ${textField.lowercase()}") },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            focusedBorderColor = MaterialTheme.colorScheme.surface,
            unfocusedBorderColor = MaterialTheme.colorScheme.surface,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon

    )

}


