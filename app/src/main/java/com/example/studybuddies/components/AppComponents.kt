package com.example.studybuddies.components

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.studybuddies.R
import com.example.studybuddies.data.NavigationItem
import com.example.studybuddies.ui.theme.BgColor
import com.example.studybuddies.ui.theme.GrayColor
import com.example.studybuddies.ui.theme.Purple40
import com.example.studybuddies.ui.theme.Secondary
import com.example.studybuddies.ui.theme.TextColor
import com.example.studybuddies.ui.theme.WhiteColor
import com.example.studybuddies.ui.theme.componentShapes
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = Purple40,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(Purple40),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = WhiteColor,
        textAlign = TextAlign.Center
    )
}
@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    initialValue: String,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val textValue = remember { mutableStateOf(initialValue) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Background color
            .clip(RoundedCornerShape(8.dp)), // Rounded corners
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple40, // Border color when focused
            focusedLabelColor = Purple40, // Label color when focused
            cursorColor = Purple40 // Cursor color
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus // Invert error status to match OutlinedTextField
    )
}
//@Composable
//fun MyTextFieldComponent(
//    labelValue: String, painterResource: Painter,
//    onTextChanged: (String) -> Unit,
//
//    errorStatus: Boolean = false
//){
//
//    val textValue = remember {
//        mutableStateOf("")
//    }
//    val localFocusManager = LocalFocusManager.current
//
//    OutlinedTextField(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(BgColor)
//            .clip(componentShapes.small),
//        label = { Text(text = labelValue) },
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedBorderColor = Purple40,
//            focusedLabelColor = Purple40,
//            cursorColor = Purple40,
//        ),
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//        singleLine = true,
//        maxLines = 1,
//        value = textValue.value,
//        onValueChange = {
//            textValue.value = it
//            onTextChanged(it)
//        },
//        leadingIcon = {
//            Icon(painter = painterResource, contentDescription = "")
//        },
//        isError = !errorStatus
//    )
//}
@Composable
fun MyTextAreaFieldComponent(
    labelValue: String, painterResource: Painter,
    initialValue: String,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
){

    val textValue = remember {
        mutableStateOf(initialValue)
    }
    val localFocusManager = LocalFocusManager.current
//    val text = rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgColor)
            .height(100.dp)
            .clip(componentShapes.small),
//            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
            label = { Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple40,
                focusedLabelColor = Purple40,
                cursorColor = Purple40,
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//        singleLine = true,
//        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
//        trailingIcon = {
//            Icon(painter = painterResource, contentDescription = "")
//        },
        isError = !errorStatus
    )
}
@Composable
fun PasswordTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgColor)
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple40,
            focusedLabelColor = Purple40,
            cursorColor = Purple40,
//            backgroundColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            })

        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Purple40)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Purple40)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }

    })
}
@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Purple40)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun LeftAlignButtonComponent(value: String, onButtonClicked: () -> Unit) {

    Button(
        modifier = Modifier
            .width(140.dp)
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
//        shape = RoundedCornerShape(50.dp),
        enabled = true
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(38.dp)
//                .align(Alignment.End)
                .background(
                    brush = Brush.horizontalGradient(listOf(Purple40, Purple40)),
//                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            thickness = 1.dp,
            color = GrayColor
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = TextColor
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            thickness = 1.dp,
            color = GrayColor
        )
    }
}
@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Purple40)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        },
    )
}
@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.colorGray),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )

}
@Composable
fun UploadImageComponent(painterResource:Painter) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(460.dp)
                .clip(shape = CircleShape),

            painter = painterResource,
            contentDescription = "Buddies",
            contentScale = ContentScale.Crop,
        )
    }

}
@Composable
fun UniversityDDComponent(itemList: List<String>, selectedIndex: Int, modifier: Modifier, onItemClick: (Int) -> Unit) {

    var showDropdown by rememberSaveable { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        // button
        Box(
            modifier = modifier
                .background(Color.Red)
                .clickable { showDropdown = true },
//            .clickable { showDropdown = !showDropdown },
            contentAlignment = Alignment.Center
        ) {
            Text(text = itemList[selectedIndex], modifier = Modifier.padding(3.dp))
        }

        // dropdown list
        Box() {
            if (showDropdown) {
                Popup(
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties(
                        excludeFromSystemGesture = true,
                    ),
                    // to dismiss on click outside
                    onDismissRequest = { showDropdown = false }
                ) {

                    Column(
                        modifier = modifier
                            .heightIn(max = 90.dp)
                            .verticalScroll(state = scrollState)
                            .border(width = 1.dp, color = Color.Gray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        itemList.onEachIndexed { index, item ->
                            if (index != 0) {
                                Divider(thickness = 1.dp, color = Color.LightGray)
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Green)
                                    .fillMaxWidth()
                                    .clickable {
                                        onItemClick(index)
                                        showDropdown = !showDropdown
                                    },
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = item,)
                            }
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxComponent(
    itemList: List<String>, label:String,
    initialValue: String,
    onTextChanged: (String) -> Unit,) {
    val context = LocalContext.current
//    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    val selectedText = remember { mutableStateOf(initialValue) }

        ExposedDropdownMenuBox(
            onExpandedChange = {
//                if(disable)expanded=false else expanded=true
                expanded = !expanded
            },
            expanded = expanded

            ) {
            TextField(
                label = {Text(text = label)},
                value = selectedText.value,
                onValueChange = {},
                readOnly = true,
                enabled = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(BgColor)
                    .clip(componentShapes.small),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Purple40,
                    focusedLabelColor = Purple40,
                    cursorColor = Purple40,
                ),
//                onClick = {
//                    if(disable!=false)expanded=false else expanded=true
//                }

            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false}
            ) {
                itemList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText.value = item
                            onTextChanged(item)
//                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }

// Layout Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    toolbarTitle: String, logoutButtonClicked: () -> Unit,
    navigationIconClicked: () -> Unit
) {

    TopAppBar(
        modifier = Modifier.background(Purple40),
        colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Purple40,
                        titleContentColor = WhiteColor,
                        navigationIconContentColor = WhiteColor
                    ),
        title = {
            Text(
                text = toolbarTitle, color = WhiteColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu),
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                logoutButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.logout),
                )
            }
        }
    )
}
@Composable
fun NavigationDrawerHeader(value: String?) {
    Box(
        modifier = Modifier
            .background(
                Brush.horizontalGradient(
                    listOf(Purple40, Purple40)
                )
            )
            .fillMaxWidth()
            .height(180.dp)
            .padding(32.dp)
    ) {
        NavigationDrawerText(
            title = value?:stringResource(R.string.navigation_header), 18.sp , WhiteColor
        )
    }
}
@Composable
fun NavigationDrawerBody(navigationDrawerItems: List<NavigationItem>,
                         onNavigationItemClicked:(NavigationItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {

        items(navigationDrawerItems) {
            NavigationItemRow(item = it,onNavigationItemClicked)
        }
    }
}
@Composable
fun NavigationItemRow(item: NavigationItem,
                      onNavigationItemClicked:(NavigationItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigationItemClicked.invoke(item)
            }

            .padding(all = 16.dp)
    ) {
//        Outline.Rectangle()
        Icon(
            imageVector = item.icon,
            contentDescription = item.description,
        )

        Spacer(modifier = Modifier.width(18.dp))

        NavigationDrawerText(title = item.title, 18.sp, Purple40)
    }
}
@Composable
fun NavigationDrawerText(title: String, textUnit: TextUnit,color: Color) {

    val shadowOffset = Offset(4f, 6f)
    Text(
        text = title, style = TextStyle(
            color = color,
            fontSize = textUnit,
            fontStyle = FontStyle.Normal,
//            shadow = Shadow(
//                color = Primary,
//                offset = shadowOffset, 2f
//            )
        )
    )
}
class DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertMillisToLocalDate(millis: Long) : LocalDate {
        return Instant
            .ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertMillisToLocalDateWithFormatter(date: LocalDate, dateTimeFormatter: DateTimeFormatter) : LocalDate {
        //Convert the date to a long in millis using a dateformmater
        val dateInMillis = LocalDate.parse(date.format(dateTimeFormatter), dateTimeFormatter)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        //Convert the millis to a localDate object
        return Instant
            .ofEpochMilli(dateInMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateToString(date: LocalDate): String {
        val dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.getDefault())
        val dateInMillis = convertMillisToLocalDateWithFormatter(date, dateFormatter)
        return dateFormatter.format(dateInMillis)
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker() {
    val dateState = rememberDatePickerState()
    val millisToLocalDate = dateState.selectedDateMillis?.let {
        DateUtils().convertMillisToLocalDate(it)
    }
    val dateToString = millisToLocalDate?.let {
        DateUtils().dateToString(millisToLocalDate)
    } ?: ""
    Column {
        DatePicker(
//            dateFormatter = DatePickerFormatter(
//                selectedDateSkeleton = "EE, dd MMM, yyyy",
//            ),
            title = {
                Text(text = "Manufactured Date")
            },
            state = dateState,
            showModeToggle = true
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = dateToString
        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    modifier: Modifier = Modifier
) {
    val dateState = rememberDatePickerState()
    val millisToLocalDate = dateState.selectedDateMillis?.let {
        DateUtils().convertMillisToLocalDate(it)
    }
    val dateToString = millisToLocalDate?.let {
        DateUtils().dateToString(millisToLocalDate)
    } ?: "Choose Date"
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    showDialog = true
                }),
            text = dateToString,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = dateState,
                    showModeToggle = true
                )
            }
        }
    }
}




