package com.example.icsproject.user_management;

import android.app.Activity;
import android.widget.Toast;

import com.example.icsproject.events.SignInFailedEvent;
import com.example.icsproject.events.SignInSuccessEvent;
import com.example.icsproject.events.UserUpdateFailedEvent;
import com.example.icsproject.events.UserUpdateSuccessEvent;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

public class UMGlobalManagement {

    private static String TAG = UMGlobalManagement.class.getSimpleName();
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static void umToast(Activity activity, String msg) {
        Timber.tag(TAG).w(msg);
        Toast.makeText(activity, msg,
                Toast.LENGTH_SHORT).show();
    }

    static void firebaseAuthWithCredential(Activity activity, AuthCredential credential) {
        if (mAuth.getCurrentUser() != null) {
            firebaseLinkWithCredential(activity, credential);
            return;
        }
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        EventBus.getDefault().post(new SignInSuccessEvent("signInWithCredential:success"));
                    }
                }).addOnFailureListener(e -> {
            if (e instanceof FirebaseAuthUserCollisionException) {
                // Sign in success, update UI with the signed-in user's information
//             EventBus.getDefault().post(new SignInSuccessEvent("signInWithCredential: FirebaseAuthUserCollisionException already signedIn"));
                firebaseLinkWithCredential(activity, credential);
            } else {
                // If sign in fails, display a message to the user.
                EventBus.getDefault().post(new SignInFailedEvent(e.getMessage()));
            }
        });
    }

    static void firebaseLinkWithCredential(Activity activity, AuthCredential credential) {
        if (mAuth.getCurrentUser() == null) {
            // If sign in fails, display a message to the user.
            EventBus.getDefault().post(new SignInFailedEvent("sorry this email address is already use by another account, please use social signIn button or signUp with another email address"));
            return;
        }
        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        EventBus.getDefault().post(new SignInSuccessEvent("signInWithCredential:success"));
                    }
                }).addOnFailureListener(e -> {
            if (e instanceof FirebaseAuthUserCollisionException) {
                // Sign in success, update UI with the signed-in user's information
                EventBus.getDefault().post(new SignInFailedEvent(String.format("sorry this email address is already use by another account, please signIn with %s or signUp with another email address", mAuth.getCurrentUser().getProviderId())));
            } else {
                // If sign in fails, display a message to the user.
                EventBus.getDefault().post(new SignInFailedEvent(e.getMessage()));
            }
        });
    }

    public static void signInAnonymously(Activity activity) {
        mAuth.signInAnonymously()
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        EventBus.getDefault().post(new SignInSuccessEvent("signInAnonymously:success"));
                    }
                }).addOnFailureListener(e -> {
            // If sign in fails, display a message to the user.
            EventBus.getDefault().post(new SignInFailedEvent(e.getMessage()));
        });
    }


    public static void updateFirebaseUser(UserProfileChangeRequest profileUpdates) {
        if (mAuth.getCurrentUser() == null) {
            EventBus.getDefault().post(new UserUpdateFailedEvent("Error, you must to signIn before"));
        }
        mAuth.getCurrentUser().updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        EventBus.getDefault().post(new UserUpdateSuccessEvent("User profile updated."));
                    }
                }).addOnFailureListener(task -> {
            EventBus.getDefault().post(new UserUpdateFailedEvent(task.getMessage()));
        });
    }

}
