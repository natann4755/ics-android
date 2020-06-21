package com.example.icsproject.controller.usermanagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icsproject.R;
import com.example.icsproject.controller.main.MainActivity;
import com.example.icsproject.controller.main.ui.home.VerificationCodeFragment;
import com.example.icsproject.controller.main.ui.home.VerificationSuccessfulFragment;
import com.example.icsproject.events.SignInFailedEvent;
import com.example.icsproject.events.SignInSuccessEvent;
import com.example.icsproject.user_management.UMFacebook;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

import timber.log.Timber;

import static com.example.icsproject.user_management.UMGlobalManagement.umToast;
import static com.example.icsproject.user_management.UMGoogle.onGoogleActivityResult;

public class UserManagementActivity extends AppCompatActivity {

    private static final String TAG = UserManagementActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_login);

        // Check if user is signed in (non-null) and update UI accordingly.
        if (FirebaseAuth.getInstance().getCurrentUser() == null
                || FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {

            getSupportFragmentManager().beginTransaction().
//                    add(R.id.AL_container, YourCommunities.newInstance(), YourCommunities.TAG)
//                    add(R.id.AL_container, ChooseCommunityFragment.newInstance(), ChooseCommunityFragment.TAG)
//        add(R.id.AL_container,SignUpFragment.newInstance(), UMChoiceModeFragment.TAG)
//        add(R.id.AL_container, VerificationSuccessfulFragment.newInstance(), UMChoiceModeFragment.TAG)
        add(R.id.AL_container, VerificationPhoneNumberFragment.newInstance(), UMChoiceModeFragment.TAG)
                    .addToBackStack(UMChoiceModeFragment.TAG).commit();
        } else {
            startMainActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //        if (requestCode == RC_SIGN_IN) {//todo why is it wrong code must to be fix
        //         todo (check on real ICS firebase project because it should be fix with new firebase tokens)
        onGoogleActivityResult(this, data);
//    }
        // Pass the activity result back to the Facebook SDK
        UMFacebook.getInstance().getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe
    public void onSignInSuccess(SignInSuccessEvent event) {
        Timber.tag(TAG).w("uuid = %s", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        umToast(this, event.getMessage());
        startMainActivity();
    }

    @Subscribe
    public void onSignInFailed(SignInFailedEvent event) {
        Timber.tag(TAG).w("signIn Failed");
        umToast(this, event.getMessage());
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
