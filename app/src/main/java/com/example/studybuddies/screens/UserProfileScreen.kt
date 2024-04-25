
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studybuddies.R.drawable
import com.example.studybuddies.R.string
import com.example.studybuddies.components.ButtonComponent
import com.example.studybuddies.components.ExposedDropdownMenuBoxComponent
import com.example.studybuddies.components.HeadingTextComponent
import com.example.studybuddies.components.LeftAlignButtonComponent
import com.example.studybuddies.components.MyTextAreaFieldComponent
import com.example.studybuddies.components.MyTextFieldComponent
import com.example.studybuddies.data.profile.ProfileUIEvent
import com.example.studybuddies.data.profile.UserProfileViewModel
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.example.studybuddies.navigation.SystemBackButtonHandler

@Composable
fun UserProfileScreen(profileViewModel: UserProfileViewModel = viewModel()) {
    var userProfileUIState = profileViewModel.profileUIState.value
    LaunchedEffect(Unit) {
        profileViewModel.loadProfile(userProfileUIState)
    }
    val profileUpdateInProgress = profileViewModel.profileUpdateInProgress
    var isComponentVisible by remember { mutableStateOf(false) }
    var isEnabled by remember { mutableStateOf(true) }

    if(!profileViewModel.setModelInProgress) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(28.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HeadingTextComponent(value = stringResource(id = string.user_profile))
//                NormalTextComponent(value = stringResource(id = string.study_buddies))
                    Spacer(modifier = Modifier.height(30.dp))
                    // Edit Button
                    LeftAlignButtonComponent(
                        value = stringResource(id = string.edit),
                        onButtonClicked = {
                            isComponentVisible = !isComponentVisible
                        }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    // Personal Information Section
                    // first name Field
                    MyTextFieldComponent(
                        labelValue = stringResource(id = string.first_name),
                        painterResource = painterResource(id = drawable.profile),
                        initialValue = userProfileUIState.firstName,
                        onTextChanged =
                        {
                            profileViewModel.onEvent(ProfileUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = userProfileUIState.firstNameError
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    // last name Field
                    MyTextFieldComponent(
                        labelValue = stringResource(id = string.last_name),
                        painterResource = painterResource(id = drawable.profile),
                        initialValue = userProfileUIState.lastName,
                        onTextChanged =
                        {
                            profileViewModel.onEvent(ProfileUIEvent.LastNameChanged(it))
                        },
                        errorStatus = userProfileUIState.lastNameError
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    // Email Field
                    MyTextFieldComponent(
                        labelValue = stringResource(id = string.phone),
                        painterResource = painterResource(id = drawable.password),
                        initialValue = userProfileUIState.phone,
                        onTextChanged =
                        {
                            profileViewModel.onEvent(ProfileUIEvent.PhoneChanged(it))
                        },
                        errorStatus = true
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    // Email Field
                    MyTextFieldComponent(
                        labelValue = stringResource(id = string.email),
                        painterResource = painterResource(id = drawable.message),
                        initialValue = userProfileUIState.email,
                        onTextChanged =
                        {
                            profileViewModel.onEvent(ProfileUIEvent.EmailChanged(it))
                        },
                        errorStatus = userProfileUIState.emailError
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    // Bio Section
                    MyTextAreaFieldComponent(
                        labelValue = stringResource(id = string.bio),
                        painterResource = painterResource(id = drawable.profile),
                        initialValue = userProfileUIState.bio,
                        onTextChanged =
                        {
                            profileViewModel.onEvent(ProfileUIEvent.BioChanged(it))
                        },
                        errorStatus = userProfileUIState.bioError
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ExposedDropdownMenuBoxComponent(itemList = profileViewModel.universitiesItemList,
                        label = stringResource(id = string.universities),
                        initialValue = userProfileUIState.university,
                        onTextChanged =
                        {
                            profileViewModel.onEvent(ProfileUIEvent.UniversityChanged(it))
                        })
                    Spacer(modifier = Modifier.height(20.dp))
                    ExposedDropdownMenuBoxComponent(itemList = profileViewModel.coursesItemList,
                        label = stringResource(id = string.courses),
                        initialValue = userProfileUIState.course,
                        onTextChanged =
                        {
                            profileViewModel.onEvent(ProfileUIEvent.CourseChanged(it))
                        })
                    Spacer(modifier = Modifier.height(40.dp))

                    if (isComponentVisible) {
                        ButtonComponent(
                            value = stringResource(id = string.save),
                            onButtonClicked = {
                                profileViewModel.onEvent(ProfileUIEvent.SaveButtonClicked)
                            },
                            isEnabled = profileViewModel.allValidationsPassed.value
                        )
                    }
                }
            }
        }
    }
    SystemBackButtonHandler {
        StudyBuddiesAppRouter.navigateTo(Screen.SignUpScreen)
    }
}


@Preview
@Composable
fun DefaultPreview(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        UserProfileScreen()
    }
}