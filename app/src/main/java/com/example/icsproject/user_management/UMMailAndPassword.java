package com.example.icsproject.user_management;

import android.app.Activity;

import com.example.icsproject.events.SignInFailedEvent;
import com.example.icsproject.events.SignInSuccessEvent;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

import static com.example.icsproject.user_management.UMGlobalManagement.firebaseLinkWithCredential;
import static com.example.icsproject.user_management.UMGlobalManagement.umToast;

public class UMMailAndPassword {

    private static final String TAG = UMMailAndPassword.class.getSimpleName();
    private final String mEmail;
    private final String mPassword;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public UMMailAndPassword(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public void signUp(Activity activity) {
        if (mAuth.getCurrentUser() != null){
            firebaseLinkWithCredential(activity, EmailAuthProvider.getCredential(mEmail, mPassword));
            return;
        }
        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        EventBus.getDefault().post(new SignInSuccessEvent("createUserWithEmail:success"));
                    }
                }).addOnFailureListener(activity, e -> {
            if (e instanceof FirebaseAuthUserCollisionException) {
                Timber.tag(TAG).w("createUserWithEmail:FirebaseAuthUserCollisionException, will try to signIn");
                signIn(activity);
            } else {
                EventBus.getDefault().post(new SignInFailedEvent(e.getMessage()));
            }
        });
    }

    public void signIn(Activity activity) {
        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        EventBus.getDefault().post(new SignInSuccessEvent("signInWithEmail:success"));
                    }
                }).addOnFailureListener(e -> {
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                firebaseLinkWithCredential(activity, EmailAuthProvider.getCredential(mEmail, mPassword));
            } else {
                EventBus.getDefault().post(new SignInFailedEvent(e.getMessage()));
            }
        });
    }

}
