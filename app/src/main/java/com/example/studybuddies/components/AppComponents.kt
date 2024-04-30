package com.example.studybuddies.components

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.studybuddies.R
import com.example.studybuddies.data.NavigationItem
import com.example.studybuddies.ui.theme.BgColor
import com.example.studybuddies.ui.theme.GrayColor
import com.example.studybuddies.ui.theme.Purple40
import com.example.studybuddies.ui.theme.Secondary
import com.example.studybuddies.ui.theme.TextColor
import com.example.studybuddies.ui.theme.WhiteColor
import com.example.studybuddies.ui.theme.componentShapes
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
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

// Image Picker
@Composable
fun UploadProfilePicBtnComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
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
//            contentAlignment = Alignment.Center

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

fun checkPermissionFor(context: Context, permission: String): Boolean{
    return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
}
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ProfileImage():Boolean {
    getUserData()
    val context = LocalContext.current
    val img: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_camera)
    var bitmap = remember { mutableStateOf(img) }
    var isUploading = remember { mutableStateOf(false) }
    var isUploadingOnLoad by remember { mutableStateOf(false) }
    var downloadedBitmapState = remember { mutableStateOf<Bitmap?>(null) }


    val cameraPermission = android.Manifest.permission.CAMERA
    var isCameraPermissionGranted by remember { mutableStateOf(false) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
//        onResult = { isGranted ->
//            if(isGranted){
////                ActivityResultContracts.TakePicturePreview()
//            }
//        }
    ) {
        if (it != null) {
            bitmap.value = it
        }
    }

    val launchImage = rememberLauncherForActivityResult(
        contract =ActivityResultContracts.GetContent()
    ) {
        if(Build.VERSION.SDK_INT < 20){
            bitmap.value =  MediaStore.Images.Media.getBitmap(context.contentResolver,it)
        }
        else{
            val source = it?.let {it1 ->
                ImageDecoder.createSource(context.contentResolver,it1)
            }
            bitmap.value = source?.let {it1 ->
                ImageDecoder.decodeBitmap(it1)
            }!!
        }
    }
    var showDialog by remember { mutableStateOf(false) }
    Box(){
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp)
                .padding(10.dp)
        ){
            Image(
                bitmap.value.asImageBitmap(),
//            Image(
//                bitmap.value.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .background(Purple40)
                    .border(
                        width = 1.dp,
                        color = WhiteColor,
                        shape = CircleShape
                    )
            )

        }
    }
    // Fetch the image from Firebase Storage
    LaunchedEffect(emailId.value) {
        val imageName = FirebaseStorage.getInstance().reference.child("image/${emailId.value}/")
        imageName.listAll()
            .addOnSuccessListener { listResult ->
                if (listResult.items.isNotEmpty()) {
                    val sortedItems = listResult.items
                    val storageRef = sortedItems[0]
//                    val storageRef = listResult.items[0]
                    // Download the image directly as a byte array
                    storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
                        // Convert the byte array to a bitmap
                        val downloadedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        // Update the state with the downloaded bitmap
                        bitmap.value = downloadedBitmap
                        isUploadingOnLoad = true
                    }.addOnFailureListener {
                        // Handle failure
                    }
                } else {
                    // Handle the case when the list is empty
                }
            }
    }
    Box(
    ){
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 130.dp)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_camera),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black)
                    .size(30.dp)
//                    .padding(50.dp)
                    .clickable { showDialog = true }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 155.dp)
            .padding(20.dp)
    ){
        Button(
            onClick = {
            isUploading.value = true
            bitmap.value.let { bitmap ->
                uploadImageToFirebase(bitmap, context as ComponentActivity){success ->
                    isUploading.value = false
                    if(success){
                        Toast.makeText(context,"Uploaded Successfully",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context,"Failed to Upload",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
            colors = ButtonDefaults.buttonColors(
                Purple40
            )
        ){
            Text(
                text="Upload",
                fontWeight = FontWeight.Bold,
                color = WhiteColor,

            )
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ){
        if(showDialog){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Purple40)
            ){
                Column(modifier = Modifier.padding(start = 60.dp)){
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                launcher.launch()
                                showDialog = false
                            }
                    )
                    Text(
                        text = "Camera",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Column {
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                launchImage.launch("image/*")
                                showDialog = false
                            }
                    )
                    Text(
                        text = "Gallery",
                        color = WhiteColor
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 50.dp,bottom =80.dp)
                )
                {
                    Text(
                        text = "X",
                        color = Color.White,
                        modifier = Modifier.clickable { showDialog = false }
                    )
                }
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth()
        ){
        if(isUploading.value){
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp),
                color = WhiteColor
            )
        }
    }
    return isUploadingOnLoad
}
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun postImage(onImageChanged: (Bitmap) -> Unit):Boolean {
    getUserData()
    val context = LocalContext.current
    val img: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_gallery)
    val bitmap = remember { mutableStateOf(img) }
    val isUploading = remember { mutableStateOf(false) }
    var isUploadingOnLoad by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
    ) {
        if (it != null) {
            bitmap.value = it
        }
    }

    val launchImage = rememberLauncherForActivityResult(
        contract =ActivityResultContracts.GetContent()
    ) {
        if(Build.VERSION.SDK_INT < 20){
            bitmap.value =  MediaStore.Images.Media.getBitmap(context.contentResolver,it)
        }
        else{
            val source = it?.let {it1 ->
                ImageDecoder.createSource(context.contentResolver,it1)
            }
            bitmap.value = source?.let {it1 ->
                ImageDecoder.decodeBitmap(it1)
            }!!
        }
    }

    var showDialog by remember { mutableStateOf(false) }
    Box(){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(30.dp)
        ){
            Image(
                bitmap.value.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
//                    .clip(CircleShape)
                    .size(300.dp)
                    .background(Purple40)
                    .border(
                        width = 1.dp,
                        color = WhiteColor,
                        shape = CircleShape
                    )
            )
        }
    }

    Box(
    ){
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 200.dp)
                .padding(30.dp)
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_add),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black)
                    .size(60.dp)
//                    .padding(50.dp)
                    .clickable { showDialog = true }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
//    Column(
//        horizontalAlignment = Alignment.Start,
//        verticalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 155.dp)
//            .padding(20.dp)
//    ){
//        Button(
//            onClick = {
//            isUploading.value = true
//            bitmap.value.let { bitmap ->
//                uploadImageToFirebase(bitmap, context as ComponentActivity){success ->
//                    isUploading.value = false
//                    if(success){
//                        Toast.makeText(context,"Uploaded Successfully",Toast.LENGTH_SHORT).show()
//                    }
//                    else{
//                        Toast.makeText(context,"Failed to Upload",Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        },
//            colors = ButtonDefaults.buttonColors(
//                Purple40
//            )
//        ){
//            Text(
//                text="Upload",
//                fontWeight = FontWeight.Bold,
//                color = WhiteColor,
//
//            )
//        }
//    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ){
        if(showDialog){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Secondary)
            ){
                Column(modifier = Modifier.padding(start = 60.dp)){
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                launcher.launch()
                                showDialog = false
                            }
                    )
                    Text(
                        text = "Camera",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Column {
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                launchImage.launch("image/*")
                                showDialog = false
                            }
                    )
                    Text(
                        text = "Gallery",
                        color = WhiteColor
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 50.dp,bottom =80.dp)
                )
                {
                    Text(
                        text = "X",
                        color = Color.White,
                        modifier = Modifier.clickable { showDialog = false }
                    )
                }
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth()
        ){
        if(isUploading.value){
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp),
                color = WhiteColor
            )
        }
    }
    onImageChanged(bitmap.value)
    return isUploadingOnLoad
}
private val emailId: MutableLiveData<String> = MutableLiveData()

private fun getUserData() {
    FirebaseAuth.getInstance().currentUser?.also {
        it.email?.also { email ->
            emailId.value = email
        }
    }
}
//@Composable
//fun PostCards(title: String,description:String,imageURI: String) {
////    getUserData()
//    val img: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_camera)
//    val bitmap = remember { mutableStateOf(img) }
//    var isUploadingOnLoad by remember { mutableStateOf(false) }
//
//    LaunchedEffect(imageURI) {
//        val imageName = FirebaseStorage.getInstance().reference.child("postImages/${imageURI}/")
//        imageName.getBytes(Long.MAX_VALUE)
//            .addOnSuccessListener { bytes ->
//                Log.d("ImageDownload", "Image bytes received: ${bytes.size}")
//                // Convert the byte array to a bitmap
//                val downloadedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//                if (downloadedBitmap != null) {
//                    // Update the state with the downloaded bitmap
//                    bitmap.value = downloadedBitmap
//                    Log.d("bitmap value", "${bitmap.value}")
//                    isUploadingOnLoad = true
//                } else {
//                    Log.e("ImageDownload", "Failed to decode byte array into bitmap")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("ImageDownload", "Image download failed", exception)
//                // Handle failure
//            }
////            }
//    }
//    if(isUploadingOnLoad){
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.TopStart
//    ) {
//
//        Card(
//            modifier = Modifier
//                .width(350.dp)
//                .height(250.dp),
//            // shape = CutCornerShape(20.dp)
//            elevation = CardDefaults.cardElevation(10.dp),
//            //border = BorderStroke(3.dp,Color.Gray)
//            colors = CardDefaults.cardColors(
//                containerColor = Color.White
//            )
//        ) {
//            Column(modifier = Modifier.fillMaxSize()) {
//                Image(
//                    bitmap.value.asImageBitmap(),
//                    modifier= Modifier.height(10.dp),
////                    painter = rememberAsyncImagePainter(model = bitmap),
////                    painter = painterResource(id = R.drawable.persepolis),
//                    contentDescription = "null"
//                )
////                Image(
////                    painterResource(id = R.drawable),
////                    painter = painterResource(id = android.R.drawable.ic_menu_camera),
////                    contentDescription = null,
////                    modifier = Modifier
////                        .clip(CircleShape)
////                        .background(Color.Black)
////                        .size(30.dp)
//////                    .padding(50.dp)
////                        .clickable { showDialog = true }
////                )
//                Text(
//                    text = title,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    modifier = Modifier.padding(10.dp)
//                )
//                Text(
//                    text = description,
//                    fontSize = 13.sp,
//                    modifier = Modifier.padding(6.dp),
//                    maxLines = 3,
//                    overflow = TextOverflow.Ellipsis,
//                    color = Color.Gray
//                )
//            }
//
//        }
//    }
//
//}
//    isUploadingOnLoad=false
//}
@Composable
fun PostCards(title: String, description: String,author: String, imageURI: String) {
    val img: Bitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources,
        android.R.drawable.ic_menu_camera
    )
    val bitmap = remember { mutableStateOf(img) }
    var isUploadingOnLoad by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        fetchImageAndDisplay(
            imageURI = imageURI,
            onImageLoaded = { loadedBitmap ->
                bitmap.value = loadedBitmap
                isUploadingOnLoad = true
            },
            onFailure = {
                // Handle failure
            }
        )
    }

    if (isUploadingOnLoad) {

                    Card(
                        modifier = Modifier
                            .width(350.dp)
                            .height(320.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        Image(
                            bitmap.value.asImageBitmap(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp), // Half of the card's height
                            contentDescription = "null"
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "$title by $author",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(10.dp).padding(start=10.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
//
                        Text(
                            text = description,
                            fontSize = 13.sp,
                            modifier = Modifier.fillMaxSize().padding(6.dp).padding(start=10.dp),
                            maxLines = 30,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

//                }
//            }
//            }
        }
    }


suspend fun fetchImageAndDisplay(
    imageURI: String,
    onImageLoaded: (Bitmap) -> Unit,
    onFailure: () -> Unit
) {
    try {
        val imageName = FirebaseStorage.getInstance().reference.child("postImages/$imageURI/")
        val bytes = withContext(Dispatchers.IO) {
            imageName.getBytes(Long.MAX_VALUE).await()
        }
        val downloadedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        if (downloadedBitmap != null) {
            onImageLoaded(downloadedBitmap)
        } else {
            onFailure()
        }
    } catch (e: Exception) {
        onFailure()
    }
}


fun uploadImageToFirebase(bitmap: Bitmap,context:ComponentActivity, callback:(Boolean)-> Unit) {
    getUserData()
    val folderRef = FirebaseStorage.getInstance().reference.child("image/${emailId.value}/")

    folderRef.listAll()
        .addOnSuccessListener { listResult ->
            val deleteTasks = listResult.items.map { it.delete() }
            Tasks.whenAllComplete(deleteTasks)
                .addOnSuccessListener {
                    // All files successfully deleted
                }
                .addOnFailureListener { exception ->
                    // Handle failure
                }
        }
        .addOnFailureListener { exception ->
            // Handle failure
        }
    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("image/${emailId.value}/${bitmap}")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
    val imageData = baos.toByteArray()

    imageRef.putBytes(imageData).addOnSuccessListener {
        callback(true)
    }.addOnFailureListener{
        callback(true)
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




