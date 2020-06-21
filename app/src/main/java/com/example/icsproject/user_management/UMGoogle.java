package com.example.icsproject.user_management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.icsproject.R;
import com.example.icsproject.events.SignInFailedEvent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GoogleAuthProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import timber.log.Timber;

import static com.example.icsproject.user_management.UMGlobalManagement.firebaseAuthWithCredential;


public class UMGoogle {

    private static String TAG = UMGoogle.class.getSimpleName();
    public static final int RC_SIGN_IN = 19;
    private final GoogleSignInClient mGoogleSignInClient;

    public UMGoogle(Context context){
        mGoogleSignInClient = GoogleSignIn.getClient(context, getGoogleSignInOptions(context));
}

    public GoogleSignInClient getGoogleSignInClient(){
        return mGoogleSignInClient;
    }

    private GoogleSignInOptions getGoogleSignInOptions(Context context){
        // Configure Google Sign In
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    public static void onGoogleActivityResult(Activity activity, Intent data) {
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Timber.d("firebaseAuthWithGoogle:%s", Objects.requireNonNull(account).getId());
            firebaseAuthWithCredential(activity, GoogleAuthProvider.getCredential(account.getIdToken(), null));
        } catch (ApiException e) {
            // Google Sign In failed, update UI appropriately
            EventBus.getDefault().post(new SignInFailedEvent(e.getMessage()));
        }
    }

}
