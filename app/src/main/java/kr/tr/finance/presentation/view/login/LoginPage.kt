package kr.tr.finance.presentation.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import kr.tr.finance.common.item.NavigationItem
import kr.tr.finance.presentation.model.MainViewModel
import kr.tr.finance.ui.theme.FacebookButtonColor

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 11:18
 */
@Composable
fun LoginPage(paddingValues: PaddingValues, navController: NavHostController, viewModel: MainViewModel) {


    val textId = remember {
        mutableStateOf("")
    }
    val textPwd = remember {
        mutableStateOf("")
    }

    val nameCheck = remember {
        mutableStateOf(false)
    }

    val coroutine = rememberCoroutineScope()



    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .background(Color.White)
    ) {
        //Redacted Content
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Sample Logo",
                modifier = Modifier.padding(vertical = 24.dp)
            )
            
            LoginField(value = textId.value , onChange = {
                textId.value = it
            })
            PasswordField(value = textPwd.value , onChange = {
                textPwd.value = it
            }, submit = {

            })

            Button(onClick = {
                coroutine.launch {
                    val response = viewModel.getUser(textId.value, textPwd.value)
                    navController.navigate(NavigationItem.home.route)
                }
            }, modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FacebookButtonColor
                )
            ) {
                Text(text = "입 력")
            }

        }
    }
}



@Composable
fun LoginField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Login",
    placeholder: String = "Enter your Login"
) {

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = FacebookButtonColor
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedPlaceholderColor = Color.DarkGray.copy(0.4f),
            focusedContainerColor = Color.White,

        ),

        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password"
) {

    val isPasswordVisible = remember {
        mutableStateOf(false)
    }


    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Lock,
            contentDescription = "",
            tint = FacebookButtonColor
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible.value = isPasswordVisible.value.not() }) {
            Icon(
                if (isPasswordVisible.value) Icons.Default.Close else Icons.Default.Search,
                contentDescription = "",
                tint = FacebookButtonColor
            )
        }
    }


    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedPlaceholderColor = Color.DarkGray.copy(0.4f),
            focusedContainerColor = Color.White,

            ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}